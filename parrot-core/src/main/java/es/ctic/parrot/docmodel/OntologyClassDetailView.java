package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.OntologyClass;

/**
 * A detailed view of a ontology class.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyClassDetailView extends AbstractOntologicalObjectDetailView implements DetailView{
	
    private static final Logger logger = Logger.getLogger(OntologyClassDetailView.class);

    private OntologyClass ontologyClass;

	private Collection<DocumentableObjectReference> superClasses = new LinkedList<DocumentableObjectReference>();
	private Collection<DocumentableObjectReference> subClasses = new LinkedList<DocumentableObjectReference>();
	private Collection<DocumentableObjectReference> equivalentClasses;
	private Collection<DocumentableObjectReference> disjointClasses;
	private Collection<DocumentableObjectReference> inverseReferences = new LinkedList<DocumentableObjectReference>();
	private Collection<DocumentableObjectReference> individuals;

	/**
	 * Constructs a ontology class detail view (Suppress default constructor for noninstantiability). 
	 */
	private OntologyClassDetailView(){
        logger.debug("Created " + this.getClass());
	}

	/**
	 * Set the ontology class (documentable element).
	 * @param ontologyClass the ontologyClass to set.
	 */
	public void setOntologyClass(OntologyClass ontologyClass) {
		this.ontologyClass = ontologyClass;
	}

	/**
	 * Returns the ontology class.
	 * @return the ontology class.
	 */
	public OntologyClass getOntologyClass() {
		return ontologyClass;
	}
	
	/**
	 * Returns the super classes.
	 * @return the super classes.
	 */	
	public Collection<DocumentableObjectReference> getSuperClasses() {
		return Collections.unmodifiableCollection(this.superClasses);
	}

	/**
	 * Returns the sub classes.
	 * @return the sub classes.
	 */
	public Collection<DocumentableObjectReference> getSubClasses() {
		return Collections.unmodifiableCollection(this.subClasses);
	}

	/**
	 * Set the inverse references to this detailed view.
	 * @param inverseReferences the inverse references to set to this detailed view.
	 */
	private void setInverseReferences(Collection<DocumentableObjectReference> inverseReferences) {
		this.inverseReferences = inverseReferences;
	}

	/**
	 * Returns the inverse references.
	 * @return the inverse references.
	 */
	public Collection<DocumentableObjectReference> getInverseReferences() {
		return inverseReferences;
	}

	/**
	 * Set the individuals to this detailed view.
	 * @param individuals the individuals to set to this detailed view.
	 */
	private void setIndividuals(Collection<DocumentableObjectReference> individuals) {
		this.individuals = individuals;
	}

	/**
	 * Returns the individuals.
	 * @return the individuals.
	 */
	public Collection<DocumentableObjectReference> getIndividuals() {
		return Collections.unmodifiableCollection(individuals);
	}

	/**
	 * Set the equivalent classes to this detailed view.
	 * @param equivalentClasses the equivalent classes to set.
	 */
	private void setEquivalentClasses(Collection<DocumentableObjectReference> equivalentClasses) {
		this.equivalentClasses = equivalentClasses;
	}

	/**
	 * Returns the equivalent classes.
	 * @return the equivalent classes.
	 */
	public Collection<DocumentableObjectReference> getEquivalentClasses() {
		return Collections.unmodifiableCollection(equivalentClasses);
	}

	/**
	 * Set the disjoint classes to this detailed view.
	 * @param disjointClasses the disjoint classes to set to this detailed view.
	 */
	private void setDisjointClasses(Collection<DocumentableObjectReference> disjointClasses) {
		this.disjointClasses = disjointClasses;
	}

	/**
	 * Returns the disjoint classes.
	 * @return the disjoint classes.
	 */
	public Collection<DocumentableObjectReference> getDisjointClasses() {
		return Collections.unmodifiableCollection(disjointClasses);
	}
	
	/**
	 * Set the super classes to this detailed view.
	 * @param superClasses the super classes to set to this detailed view.
	 */
	private void setSuperClasses(Collection<DocumentableObjectReference> superClasses) {
		this.superClasses = superClasses;
	}

	/**
	 * Set the sub classes to this detailed view.
	 * @param subClasses the sub classes to set to this detailed view.
	 */
	private void setSubClasses(Collection<DocumentableObjectReference> subClasses) {
		this.subClasses = subClasses;
	}
	
    /**
     * Returns <code>true</code> if, and only if, this class is an OWL class.
     * @return <code>true</code> if this class is an OWL class, otherwise <code>false</code>.
     */
	public boolean isOwlClass() {
		return getOntologyClass().isOwlClass();
	}
	
    /**
     * Returns <code>true</code> if, and only if, this class is an RDFS class.
     * @return <code>true</code> if this class is an RDFS class, otherwise <code>false</code>.
     */
	public boolean isRdfsClass() {
		return getOntologyClass().isRdfsClass();
	}
	
	/**
	 * Returns a detailed view for the ontology class given.
	 * @param object the ontology class.
	 * @param locale the locale.
	 * @return a detailed view for an ontology class.
	 */
    public static OntologyClassDetailView createFromClass(OntologyClass object, Locale locale) {
    	
    	OntologyClassDetailView details = new OntologyClassDetailView();
    	
    	details.setOntologyClass(object);

		details.setUri(object.getURI());
		details.setUriFragment(object.getUriFragment());
		details.setLabel(object.getLabel(locale));
		details.setDescription(object.getDescription(locale));
		
		// Control version information
		details.setVersion(object.getVersion());
		
		details.setDate(object.getDate());
		details.setModifiedDate(object.getModifiedDate());
		details.setIssuedDate(object.getIssuedDate());

		details.setCreators(object.getCreators());
		details.setContributors(object.getContributors());
		details.setPublishers(object.getPublishers());
		details.setCreatorAgents(object.getCreatorAgents());
		details.setContributorAgents(object.getContributorAgents());
		details.setPublisherAgents(object.getPublisherAgents());

		details.setRights(object.getRights());
		details.setLicenseLabel(object.getLicenseLabel());
		
		details.setSuperClasses(DocumentableObjectReference.createReferences(object.getSuperClasses(), locale));
		details.setSubClasses(DocumentableObjectReference.createReferences(object.getSubClasses(), locale));
		details.setEquivalentClasses(DocumentableObjectReference.createReferences(object.getEquivalentClasses(),locale));
		details.setDisjointClasses(DocumentableObjectReference.createReferences(object.getDisjointClasses(), locale));
		details.setInverseRuleReferences(DocumentableObjectReference.createReferences(object.getInverseRuleReferences(), locale));
		details.setInverseReferences(DocumentableObjectReference.createReferences(object.getInternalReferences(), locale));
		details.setIndividuals(DocumentableObjectReference.createReferences(object.getIndividuals(), locale));
		
		details.setLexicalInformation(LexicalInformation.createFromLabels(object.getLabels(), details.getLabel(), locale));

		details.setRelatedDocuments(object.getRelatedDocuments(locale));

		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		details.setIsDefinedBy(DocumentableObjectReference.createReference(object.getIsDefinedBy(),locale));
		details.setDeprecated(object.isDeprecated());
		
		details.setStatus(object.getStatus());

		return details;

    }

}
