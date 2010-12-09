package es.ctic.parrot.reader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * A web input document, anything that can be referenced by a URL.
 * 
 * @author CTIC
 *
 */

public class URLInput implements Input {

    private static final Logger logger = Logger.getLogger(URLInput.class);

    // application/rdf+xml, application/xml : http://www.w3.org/TR/owl-ref/#MIMEType
    // application/rif+xml : http://www.w3.org/TR/rif-core/#Appendix:_RIF_Media_Type_Registration
    // application/owl+xml : http://www.w3.org/TR/2009/REC-owl2-xml-serialization-20091027/#Appendix:_Internet_Media_Type.2C_File_Extension.2C_and_Macintosh_File_Type

    private static final String ACCEPT_HEADER_VALUES = "application/rdf+xml;application/xml;application/rif+xml;application/owl+xml"; // FIXME: add other mimetypes
    private static final Set<String> STRICT_MIMETYPES = new HashSet<String>(Arrays.asList("application/rdf+xml","application/xml","application/rif+xml","application/owl+xml"));

    //private static final Set<String> NOT_STRICT_MIMETYPES = new HashSet<String>(Arrays.asList("text/x-rif-ps"));

    
    private URL url;
    private String mimeType;

    public URLInput(URL url, String mimeType) {
        this.url = url;
        this.mimeType = mimeType;
        checkUrl();
    }
    
    /**
     * Checks if the URL is available and establish content negotiation 
     */
    private void checkUrl() {
        try {
            logger.debug("Detecting content type for URL " + this.url);
            HttpURLConnection connection = (HttpURLConnection) this.url.openConnection();
            connection.setRequestMethod("HEAD"); 
            
            if (this.mimeType == null){
                logger.debug("Accepting: " + ACCEPT_HEADER_VALUES);
                connection.addRequestProperty("Accept", ACCEPT_HEADER_VALUES);
            } else
            if (STRICT_MIMETYPES.contains(this.mimeType)) {
            	connection.addRequestProperty("Accept", this.mimeType);
            } else {
            	// Pass
            }
            
            connection.connect();
        	
            logger.debug("HTTP Status Response code: " + connection.getResponseCode() + " for URL " + this.url);
            
        	if (isValidResponseCode(connection.getResponseCode()) == false ){
        		logger.error("URI " + this.url + " not accesible. HTTP Status code: " + connection.getResponseCode());
            	throw new RuntimeException("URI not accesible. HTTP Status code: " + connection.getResponseCode());
            } 
            
            if (this.mimeType == null){
                this.mimeType = getCleanMimeType(connection.getContentType());
                if (STRICT_MIMETYPES.contains(this.mimeType)){ 
                	logger.info("Found content-type: " + this.mimeType + " for URL " + this.url);
                } else{
                	logger.error("mimeType not valid: " + this.mimeType + " for URL " + this.url);
                	throw new RuntimeException("mimeType not valid: " + this.mimeType);
                }
            }
        } catch (ClassCastException e) {
            throw new RuntimeException("Cannot open HttpURLConnection, probably " + this.url + " is not an HTTP URL", e);
        } catch (UnknownHostException e) {
        	logger.error("Probably " + this.url + " is not an HTTP URL");
        	throw new RuntimeException("Probably " + this.url + " is not an HTTP URL");
        } catch (java.net.MalformedURLException e){
        	logger.error("Probably " + this.url + " is a malformed URL");
        	throw new RuntimeException("Probably the URL is malformed");
        } catch (IOException e) {
        	logger.error("IOException in " + this.url);
        	throw new RuntimeException("Error in the connection to the URL");
		}
		
	}

	public URLInput(URL url) throws IOException {
        this.url = url;
        checkUrl();
    }
    
    public String getMimeType() {
        return mimeType;
    }

    public Reader openReader() throws IOException {
    	URLConnection conn = url.openConnection();
    	
    	logger.info("MimeType: " + this.getMimeType());
    	if (STRICT_MIMETYPES.contains(this.getMimeType())){
    		conn.setRequestProperty("Accept", this.getMimeType());	
    	}
    	
    	return new InputStreamReader(conn.getInputStream());
    }
    
    public String getCleanMimeType(String rawMimeType){
    	return rawMimeType.split(";")[0];
    }

    public boolean isValidResponseCode(int code){
    	if (code>=200 && code<400)
    		return true;
    	else
    		return false;
    }

}