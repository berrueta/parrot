package es.ctic.parrot.docmodel;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import es.ctic.parrot.reader.Input;

/**
 * 
 * A document is a group of detailed views that will be used by a generator.
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class Document {

	private Locale locale;
	private String title;
	private final Collection<Input> inputs = new HashSet<Input>();
	private final Set<OntologyDetailView> ontologyDetailViews = new HashSet<OntologyDetailView>();
	private final Set<OntologyClassDetailView> ontologyClassDetailViews = new HashSet<OntologyClassDetailView>();
	private final Set<OntologyPropertyDetailView> ontologyPropertyDetailViews = new HashSet<OntologyPropertyDetailView>();
    private final Set<OntologyIndividualDetailView> ontologyIndividualDetailViews = new HashSet<OntologyIndividualDetailView>();
	private final Set<RuleDetailView> ruleDetailViews = new HashSet<RuleDetailView>();
    private final Set<RuleSetDetailView> ruleSetDetailViews = new HashSet<RuleSetDetailView>();
    private final Set<VocabularyDetailView> vocabularyDetailViews = new HashSet<VocabularyDetailView>();
    private final Set<DatasetDetailView> datasetDetailViews = new HashSet<DatasetDetailView>();
    private final Set<CatalogDetailView> catalogDetailViews = new HashSet<CatalogDetailView>();

    private final Glossary glossary;
    private String prologueURL;
    private String appendixURL;
    private String reportURL;
    private Collection<String> languages;
    private String customizeCssURL;
    
	private static final org.apache.log4j.Logger logger = Logger.getLogger(Document.class);


    /**
     * Constructs a document using the given locale.
     * @param locale the locale.
     */
    public Document(Locale locale) {
    	this.setLocale(locale);
        this.glossary = new Glossary(locale);
    }
	
    /**
     * Returns the title of this document.
     * @return the title of this document.
     */
	public String getTitle() {
		if (this.title == null) {
			this.title = generateTitle();
		}
		
		return this.title;
	}

	/**
	 * Returns the sorted collection of ontology views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of ontology views for this document.
	 */
	public Collection<OntologyDetailView> getOntologyDetailViews() {
		List<OntologyDetailView> listOntologyDetailsViews = new LinkedList<OntologyDetailView>(this.ontologyDetailViews);
		Collections.sort(listOntologyDetailsViews, new DetailViewComparator());
		return listOntologyDetailsViews;
	}

	/**
	 * Returns the sorted collection of class views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of class views for this document.
	 */
	public Collection<OntologyClassDetailView> getOntologyClassDetailViews() {
		List<OntologyClassDetailView> listOntologyClassDetailViews = new LinkedList<OntologyClassDetailView>(this.ontologyClassDetailViews);
		Collections.sort(listOntologyClassDetailViews, new DetailViewComparator());
		return listOntologyClassDetailViews;
	}

	/**
	 * Returns the sorted collection of property views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of property views for this document.
	 */
	public Collection<OntologyPropertyDetailView> getOntologyPropertyDetailViews() {
		List<OntologyPropertyDetailView> listOntologyPropertyDetailViews = new LinkedList<OntologyPropertyDetailView>(this.ontologyPropertyDetailViews);
		Collections.sort(listOntologyPropertyDetailViews, new DetailViewComparator());
		return listOntologyPropertyDetailViews;
	}

	/**
	 * Returns the sorted collection of individual views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of individual views for this document.
	 */
    public Collection<OntologyIndividualDetailView> getOntologyIndividualDetailViews() {
		List<OntologyIndividualDetailView> listOntologyIndividualDetailViews = new LinkedList<OntologyIndividualDetailView>(this.ontologyIndividualDetailViews);
		Collections.sort(listOntologyIndividualDetailViews, new DetailViewComparator());
		return listOntologyIndividualDetailViews;
    }

	/**
	 * Returns the sorted collection of rule views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of rule views for this document.
	 */
    public Collection<RuleDetailView> getRuleDetailViews() {
		List<RuleDetailView> listRuleDetailViews = new LinkedList<RuleDetailView>(this.ruleDetailViews);
		Collections.sort(listRuleDetailViews, new DetailViewComparator());
		return listRuleDetailViews;
    }
    
	/**
	 * Returns the sorted collection of rule set views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of rule set views for this document.
	 */
    public Collection<RuleSetDetailView> getRuleSetDetailViews() {
		List<RuleSetDetailView> listRuleSetDetailViews = new LinkedList<RuleSetDetailView>(this.ruleSetDetailViews);
		Collections.sort(listRuleSetDetailViews, new DetailViewComparator());
		return listRuleSetDetailViews;
    }
    
	/**
	 * Returns the sorted collection of vocabulary views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of vocabulary views for this document.
	 */
	public Collection<VocabularyDetailView> getVocabularyDetailViews() {
		List<VocabularyDetailView> listVocabularyDetailsViews = new LinkedList<VocabularyDetailView>(this.vocabularyDetailViews);
		Collections.sort(listVocabularyDetailsViews, new DetailViewComparator());
		return listVocabularyDetailsViews;
	}

	/**
	 * Returns the sorted collection of dataset views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of dataset views for this document.
	 */
	public Collection<DatasetDetailView> getDatasetDetailViews() {
		List<DatasetDetailView> listDatasetDetailsViews = new LinkedList<DatasetDetailView>(this.datasetDetailViews);
		Collections.sort(listDatasetDetailsViews, new DetailViewComparator());
		return listDatasetDetailsViews;
	}

	/**
	 * Returns the sorted collection of catalog views for this document. Elements are sorted alphabetically by label.
	 * @return the sorted collection of catalog views for this document.
	 */
	public Collection<CatalogDetailView> getCatalogDetailViews() {
		List<CatalogDetailView> listCatalogDetailsViews = new LinkedList<CatalogDetailView>(this.catalogDetailViews);
		Collections.sort(listCatalogDetailsViews, new DetailViewComparator());
		return listCatalogDetailsViews;
	}
	
    /** 
     * Adds a given detailed ontology view into this document.
     * @param details the detailed ontology view.
     */
    public void addOntologyDetailView(OntologyDetailView details) {
        this.ontologyDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed class view into this document.
     * @param details the detailed class view.
     */
    public void addOntologyClassDetailView(OntologyClassDetailView details) {
        this.ontologyClassDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed property view into this document.
     * @param details the detailed property view.
     */
    public void addOntologyPropertyDetailView(OntologyPropertyDetailView details) {
        this.ontologyPropertyDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed individual view into this document.
     * @param details the detailed individual view.
     */
	public void addOntologyIndividualDetailView(OntologyIndividualDetailView details) {
		this.ontologyIndividualDetailViews.add(details);
	}
	
    /** 
     * Adds a given detailed rule view into this document.
     * @param details the detailed rule view.
     */
    public void addRuleDetailView(RuleDetailView details) {
        this.ruleDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed rule set view into this document.
     * @param details the detailed rule set view.
     */
    public void addRuleSetDetailView(RuleSetDetailView details) {
        this.ruleSetDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed vocabulary view into this document.
     * @param details the detailed vocabulary view.
     */
    public void addVocabularyDetailView(VocabularyDetailView details) {
        this.vocabularyDetailViews.add(details);
    }

    /** 
     * Adds a given detailed dataset view into this document.
     * @param details the detailed dataset view.
     */
    public void addDatasetDetailView(DatasetDetailView details) {
        this.datasetDetailViews.add(details);
    }
    
    /** 
     * Adds a given detailed catalog view into this document.
     * @param details the detailed catalog view.
     */
    public void addCatalogDetailView(CatalogDetailView details) {
        this.catalogDetailViews.add(details);
    }
    
	/**
	 * Returns the glossary.
	 * @return the glossary.
	 */
    public Glossary getGlossary() {
        return glossary;
    }

	/**
	 * Returns a collection of inputs.
	 * @return a collection of inputs.
	 */
	public Collection<Input> getInputs() {
		return inputs;
	}
	
	/**
	 * Sets a collection of inputs.
	 * @param inputs a collection of inputs to set.
	 */
	public void setInputs(Collection<Input> inputs){
		this.inputs.addAll(inputs);
	}

	/**
	 * Returns the URL where is the prologue.
	 * @return the URL where is the prologue.
	 */
	public String getPrologueURL() {
		return prologueURL;
	}

	/**
	 * Sets the URL where is the prologue.
	 * @param prologueURL the URL where is the prologue.
	 */
	public void setPrologueURL(String prologueURL) {
		this.prologueURL = prologueURL;
	}
	
	/**
	 * Returns the content of the prologue.
	 * @return the content of the prologue.
	 */
	public String getPrologue(){
		return getContentFromURL(getPrologueURL());
	}
	
	/**
	 * Sets the URL where is the appendix.
	 * @param appendixURL the URL where is the appendix.
	 */
	public void setAppendixURL(String appendixURL) {
		this.appendixURL = appendixURL;
	}

	/**
	 * Returns the URL where is the appendix.
	 * @return the URL where is the appendix.
	 */
	public String getAppendixURL() {
		return appendixURL;
	}
	
	/**
	 * Returns the content of the appendix.
	 * @return the content of the appendix.
	 */
	public String getAppendix(){
		return getContentFromURL(getAppendixURL());
	}
	
	/**
	 * Returns the XHTML content of a given URI (including the fragment part).
	 * @param URL the URL.
	 * @return the XHTML content of a given URI (including the fragment part).
	 */
	private String getContentFromURL(String URL){
		
	    try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true);

			DocumentBuilder builder = domFactory.newDocumentBuilder();
			
			builder.setEntityResolver(new EntityResolver() {
			    public InputSource resolveEntity(String publicId, String systemId)
			            throws SAXException, IOException {
			            return new InputSource(new StringReader(""));
			    }
			});

			String anchor = new URL(URL).getRef();

			org.w3c.dom.Document doc = builder.parse(URL);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile("//*[@id='"+anchor+"']");

			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;

			if (nodes.getLength() == 0){
				return null;
			}

			Element nodeId = (Element) nodes.item(0);
			
			nodeId.setAttribute("about", "#"+anchor);
			nodeId.setAttribute("typeof", "foaf:Document");
			//nodeId.setAttribute(name, value)
			//rel="parrot:hasPrologue"
			
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			StringWriter buffer = new StringWriter();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(new DOMSource(nodeId), new StreamResult(buffer));
			return buffer.toString();
		} catch (Exception e){
			return null; //FIXME ashamed code
		}
	}

	/**
	 * Sets the report URL.
	 * @param reportURL the report URL to set.
	 */
	public void setReportURL(String reportURL) {
		this.reportURL = reportURL;
	}

	/**
 	 * Returns the report URL.
	 * @return the report URL.
	 */
	public String getReportURL() {
		return reportURL;
	}

	/**
	 * Set the languages.
	 * @param languages the languages to set.
	 */
	public void setLanguages(Collection<String> languages) {
		this.languages = languages;
	}

	/**
	 * Returns the languages.
	 * @return the languages.
	 */
	public Collection<String> getLanguages() {
		return languages;
	}
	
	/**
	 * Returns <code>true</code> if all the inputs are persistent, otherwise <code>false</code>. For example, a <code>URL</code> or a <code>file</code> is a persistent input.
     * @return <code>true</code> if all the inputs are persistent, otherwise <code>false</code>. For example, a <code>URL</code> or a <code>file</code> is a persistent input.
	 */
	public boolean isReloadable(){
		for(Input input: getInputs()){
			if (input.isPersistent() == false){
				return false;
			}
		}
		return true;
	}

	/**
	 * Sets the locale.
	 * @param locale the locale to set.
	 */
	private void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * Returns the locale.
	 * @return the locale.
	 */
	public Locale getLocale() {
		return locale;
	}
	
	/**
	 * Returns the main language of the document.
	 * @return the main language of the document
	 */
	public String getMainLanguage() {
		return locale.getLanguage();
	}

	/**
	 * @param customizeCssURL the customize CSS URL to set
	 */
	public void setCustomizeCssURL(String customizeCssURL) {

		// TODO  validate URL ??
//		try {
//		    URL url = new URL("customizeCssURL");
//		    URLConnection conn = url.openConnection();
//		    conn.connect();
//		} catch (MalformedURLException e) {
//		    // the URL is not in a valid form
//		} catch (IOException e) {
//		    // the connection couldn't be established
//		}
		
		this.customizeCssURL = customizeCssURL;
	}

	/**
	 * Returns the customize CSS URL
	 * @return the customizeCssURL
	 */
	public String getCustomizeCssURL() {
		return customizeCssURL;
	}
	
	/**
	 * Return a generated title for the document
	 * @return the generated title.
	 */
	private String generateTitle() {
//		logger.debug("ontology="+ontologyDetailViews.size());
//		logger.debug("vocabulary="+vocabularyDetailViews.size());
//		logger.debug("ruleSet="+ruleSetDetailViews.size());
//		logger.debug("dataset="+datasetDetailViews.size());
		
		if ( ontologyDetailViews.size() == 1 && vocabularyDetailViews.isEmpty() && ruleSetDetailViews.isEmpty() &&  datasetDetailViews.isEmpty()){
			return ontologyDetailViews.iterator().next().getLabel();
		} else if  ( ontologyDetailViews.isEmpty() && vocabularyDetailViews.size() == 1 && ruleSetDetailViews.isEmpty() && datasetDetailViews.isEmpty()){
			return vocabularyDetailViews.iterator().next().getLabel();
		} else if ( ontologyDetailViews.isEmpty() && vocabularyDetailViews.isEmpty() && ruleSetDetailViews.size() == 1 && datasetDetailViews.isEmpty()){
			return ruleSetDetailViews.iterator().next().getLabel();
		} else if  ( ontologyDetailViews.isEmpty() && vocabularyDetailViews.isEmpty() && ruleSetDetailViews.isEmpty() && datasetDetailViews.size() == 1){
			return datasetDetailViews.iterator().next().getLabel();
		} else {
			return "Report for several artifacts";
		}
	}

}
/**
 * Compares alphabetically by label of each <code>DetailView</code> 
 *
 */
class DetailViewComparator implements Comparator<DetailView> {

	// This comparator is not consistent with equals()
	public int compare(DetailView arg0, DetailView arg1) {
		if (arg0.getLabel() != null && arg1.getLabel() != null) {
			return  arg0.getLabel().toLowerCase().compareTo(arg1.getLabel().toLowerCase());
		} else {
			return 0; // FIXME change comparable method
		}
	}
	
}

