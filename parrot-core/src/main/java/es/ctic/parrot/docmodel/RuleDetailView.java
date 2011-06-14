package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Rule;

/**
 * A detailed view of a rule.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class RuleDetailView extends AbstractVersionableDetailView{

    private static final Logger logger = Logger.getLogger(RuleDetailView.class);

	private Collection<DocumentableObjectReference> referencedOntologicalObjects;
	private DocumentableObjectReference parent;
    
	
	/**
	 * Constructs a rule detail view (Suppress default constructor for noninstantiability).
	 */
	private RuleDetailView() {
        logger.debug("Created " + this.getClass());
    }

	/**
	 * Returns the references to the ontological elements that reference this rule.
	 * @return the references to the ontological elements that reference this rule.
	 */
	public Collection<DocumentableObjectReference> getReferencedOntologicalObjects() {
		return Collections.unmodifiableCollection(referencedOntologicalObjects);
	}

	/**
	 * Sets the references to the ontological elements that reference this rule.
	 * @param referencedOntologicalObjects the references to the ontological elements that reference this rule.
	 */
	private void setReferencedOntologicalObjects(
			Collection<DocumentableObjectReference> referencedOntologicalObjects) {
		this.referencedOntologicalObjects = referencedOntologicalObjects;
	}

	/**
	 * Sets the reference to the parent element.
	 * @param parent the reference to the parent element.
	 */
	private void setParent(DocumentableObjectReference parent) {
		this.parent = parent;
	}

	/**
	 * Returns the reference to the parent element.
	 * @return the reference to the parent element.
	 */
	public DocumentableObjectReference getParent() {
		return parent;
	}
	
	/**
	 * Returns a detailed view for the rule given.
	 * @param object the rule.
	 * @param locale the locale.
	 * @return a detailed view for a rule.
	 */
    public static RuleDetailView createFromRule(Rule object, Locale locale) {
    	
	    RuleDetailView details = new RuleDetailView();
	    details.setIdentifier(object.getIdentifier());
		details.setUri(object.getURI());
		details.setUriFragment(object.getUriFragment());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		
		// Control version information
		details.setVersion(object.getVersion());
		details.setDate(object.getDate());
		
		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		
		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());

		details.setRights(object.getRights());
		details.setLicenseLabel(object.getLicenseLabel());

		details.setParent(DocumentableObjectReference.createReference(object.getParent(), locale));
		details.setReferencedOntologicalObjects(DocumentableObjectReference.createReferences(object.getReferencedOntologicalObjects(), locale));
		details.setLabels(object.getLabels());
		details.setSynonyms(object.getSynonyms());
		
        details.setAnchor(object.getLocalName());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		return details;
    }
    
}
