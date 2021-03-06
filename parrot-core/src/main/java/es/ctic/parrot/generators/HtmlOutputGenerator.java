package es.ctic.parrot.generators;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.project.Profile;

/**
 * Generates <code>HTML</code> content from a documentary model.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class HtmlOutputGenerator implements OutputGenerator {

    private static final String PROFILE = "profile";
	private static final String DOCUMENT = "document";
	private static final String REPORTURL_NOLANG = "reportUrl_NoLang";
	private static final String URI_BASE = "uri_base";
	private static final String DEFAULT_URI_BASE = "";

	private final OutputStream out;
    private InputStream template;
    private String uriBase;
    
    
    
	public static class Builder {
		// Optional parameters - initialized to default values
		private OutputStream out = getDefaultOutputStream();
	    private InputStream template = getDefaultTemplate();
	    private String uriBase = DEFAULT_URI_BASE;		

		public Builder() {
			// Required parameters
		}

		public Builder out(OutputStream val){
			out = val;
			return this;
		}

		public Builder template(InputStream val){
			template = val;
			return this;
		}

		public Builder uriBase(String val){
			uriBase = val;
			return this;
		}
		
		public HtmlOutputGenerator build() {
			return new HtmlOutputGenerator(this);
		}
		
	    private InputStream getDefaultTemplate() {
			InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("html/template.vm");
			if (template == null) {
				throw new RuntimeException("Failed to load resource");
			}
			return template;
		}
	    
	    private OutputStream getDefaultOutputStream(){
	    	return new ByteArrayOutputStream();
	    }


	}

	private HtmlOutputGenerator(Builder builder) {
		out = builder.out;
		template = builder.template;
		uriBase = builder.uriBase;
	}
	

	/**
     * Generates an output.
     * @param document The source document.
     * @param profile The profile of the user that will read the report.
     */
    public void generateOutput(Document document, Profile profile) {
        VelocityContext ctx = new VelocityContext();
        ctx.put(DOCUMENT, document);
        ctx.put(PROFILE, profile);
        ctx.put(REPORTURL_NOLANG, getNoLangURL(document.getReportURL()));
        //ctx.put(URI_BASE, "");
        ctx.put(URI_BASE, uriBase);
        fillTemplate(out, ctx, template);
    }

    /**
     * Returns a URL without the <code>language</code> parameter (if exists).
     * @param url the URL to treat.
     * @return a URL without the <code>language</code> parameter (if exists).
     */
	private String getNoLangURL(String url) {
		if (url != null){
			if (url.matches(".*&language=.*?(?=[&]).*")){
	        	url = url.replaceFirst("&language=.*?(?=[&])", "");
	        } else if (url.matches(".*&language=.*$")) {
	        	url = url.replaceFirst("&language=.*$", "");
	        } else {
	        	//url = url ;
	        }
		}
		return url;
	}

    
    /**
     * Fills the template using the context given and writes the result into an output stream.
     * @param out Stream where the output will be written.
     * @param ctx The velocity context that contains the information to fill the template.
     * @param template The template for the output report.
     * 
     */
    private void fillTemplate(OutputStream out, VelocityContext ctx, InputStream template) {
        // FIXME: this code is not reentrant!
        try {
            Properties p = new Properties();
            p.setProperty("velocimacro.library", "html/macros.vm");
            p.setProperty("resource.loader", "class,file");
            p.setProperty("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            p.setProperty(Velocity.EVENTHANDLER_REFERENCEINSERTION, "org.apache.velocity.app.event.implement.EscapeHtmlReference");
            p.setProperty("eventhandler.escape.html.match", "/^(?!\\$unescapehtml_).*/");
            p.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
            Velocity.init(p);

            OutputStreamWriter osw = new OutputStreamWriter(out, (new InputStreamReader(template)).getEncoding());
            Velocity.evaluate(ctx, osw, "parrot", new BufferedReader(new InputStreamReader(template))); 
            osw.flush();
            osw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }		
    }

}
