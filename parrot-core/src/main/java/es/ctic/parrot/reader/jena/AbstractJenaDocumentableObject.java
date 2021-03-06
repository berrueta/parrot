package es.ctic.parrot.reader.jena;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

import es.ctic.parrot.de.AbstractVersionable;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.de.ExternalDocumentableObject;


/**
 * An implementation of the {@link es.ctic.parrot.de.DocumentableOntologicalObject} to serve as a basis for implementing various kinds of ontology documentable elements.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractJenaDocumentableObject extends
		AbstractVersionable implements DocumentableOntologicalObject {
	
	private static final Logger logger = Logger.getLogger(AbstractJenaDocumentableObject.class);
	
	private Collection<Rule> inverseRuleReferences = new HashSet<Rule>();

	/**
	 * Constructs an abstract Jena documentable element, setting the ontResource, the annotation strategy and the register.
	 * @param ontResource the ontResource.
	 * @param register the register.
	 * @param annotationStrategy the annotation strategy.
	 */
	public AbstractJenaDocumentableObject(OntResource ontResource, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super();
		this.setOntResource(ontResource);
		this.setAnnotationStrategy(annotationStrategy);
		this.setRegister(register);
		logger.debug("Created a documentable object for " + ontResource);
	}
	
	
	public void addInverseRuleReference(Rule rule) {
		inverseRuleReferences.add(rule);
	}
	
	public Collection<Rule> getInverseRuleReferences(){
		return Collections.unmodifiableCollection(inverseRuleReferences);
	}
	
	
	/**
	 * Returns the URI of this documentable element or <code>null</code> if it's a blank node.
	 * @return the URI of this documentable element or <code>null</code> if it's a blank node.
	 */
	public String getURI() {
		return getOntResource().getURI();
	}

	public String getUriFragment() {
		try {
			if (getURI() != null) {
				return new URI(getURI()).getFragment();
			} else {
				return null;
			}
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	/**
	 * Returns the identifier.
	 * @return the identifier.
	 */
	public Identifier getIdentifier() {
		if (getOntResource().isAnon() == true){
			return new JenaAnonymousIdentifier(getOntResource().getId());
		} else{
			return new URIIdentifier(getOntResource().getURI());
		}
	}

	/**
	 * Returns the compact URI (for instance <code>foaf:name</code>) or the URI if it cannot be compact. 
	 * @return the compact URI or the URI if it cannot be compact.
	 */
	public String getURIAbbrev(){
		String ns=getOntResource().getModel().getNsURIPrefix(getOntResource().getNameSpace());
		if(ns!=null){
			return ns+":"+getOntResource().getLocalName();
		}
		return getOntResource().getURI();
	}
	
	public int compareTo(DocumentableOntologicalObject o) {
		return getURI().compareTo(o.getURI());
	}

    /**
     * Converts an iterator over Jena resources to a collection of documentable elements.
     * @param it an iterator over Jena resources.
     * @return a collection of documentable elements.
     */
	@SuppressWarnings("unchecked")
	protected <TR extends DocumentableObject, TJ extends Resource> Collection<TR> resourceIteratorToDocumentableObjectList(Iterator<TJ> it) {
		
		List<TR> documentableObjectList = new LinkedList<TR>();
		
		while(it.hasNext()){
			Resource resource = it.next();
			
			Identifier identifier = null;
			
			if (resource.isAnon() == false){
				identifier = new URIIdentifier(resource.getURI());
			} else {
				identifier = new JenaAnonymousIdentifier(resource.getId());
			}
			
			TR _resource = (TR) this.getRegister().findDocumentableObject(identifier); 

			if (_resource != null) { 
				documentableObjectList.add(_resource);
			} else {  // do not add null elements in the list
				// logger.debug("Not found in register: " + identifier);
				if (resource.isAnon()){
					logger.debug("Not added in the list the anon resource: " + resource.getId());
				}else {
					if (isDomainSpecific(resource.getURI())) {
						_resource = (TR) new ExternalDocumentableObject(resource.getURI());
						documentableObjectList.add(_resource);
					} else {
						logger.debug("Not added in the list the resource " + identifier + " (not domain specific)");
					}
				}

			}
		}
		return documentableObjectList;
	}
	
	/**
     * Returns <code>true</code> if, and only if, the URI is domain specified.
     * @param uri the URI.
     * @return <code>true</code> if the URI is domain specified, otherwise <code>false</code>.
	 */
	public static boolean isDomainSpecific(String uri) {
       	return !uri.startsWith(RDFS.getURI()) && !uri.startsWith(RDF.getURI()) && !uri.startsWith(OWL.getURI());
    }
	
    public String getDescription(Locale locale) {
		return getAnnotationStrategy().getDescription(getOntResource(), locale);
	}
    
	public Collection<Label> getLabels(Locale locale){
   		return getAnnotationStrategy().getLabels(getOntResource(), locale);
   	}

    public Collection<Label> getLabels(){
   		return getAnnotationStrategy().getLabels(getOntResource(), null);
   	}

    public String getLabel(String language) {
    	return getLabel(new Locale(language));
    }

    public String getLabel(Locale locale) {
    	String label = getAnnotationStrategy().getLabel(getOntResource(), locale);
    	
    	if (label != null){
    		return label;
    	} else {
    		return getKindString() + getIdentifier().toString();
    	}
    }
    
    public String getLabel() {
        return getAnnotationStrategy().getLabel(getOntResource(), null);
    }

	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
        return getAnnotationStrategy().getRelatedDocuments(getOntResource(), locale);
    }
	
	public boolean isDeprecated(){
		return getAnnotationStrategy().isDeprecated(getOntResource());
	}
	
	public String getStatus(){
		return getAnnotationStrategy().getStatus(getOntResource());
	}
	
	
}
