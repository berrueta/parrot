package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyProperty;

public class OntologyPropertyDetailView extends AbstractOntologicalObjectDetailView  implements DetailView{
	private OntologyProperty ontologyProperty;

	private DocumentableObject domain;
	private DocumentableObject range;
    private Collection<OntologyProperty> superProperties;
    private Collection<OntologyProperty> subProperties;
    private Collection<DocumentableObject> inverseReferences;
	private Collection<OntologyProperty> equivalentProperties;
	private Collection<OntologyProperty> disjointProperties;
    private int cardinality;
    private DocumentableObject inverseOf;
	
	public OntologyPropertyDetailView(OntologyProperty ontologyProperty){
		this.ontologyProperty=ontologyProperty;
	}
	
	/**
	 * @return the ontologyProperty
	 */
	public OntologyProperty getOntologyProperty() {
		return ontologyProperty;
	}
	
	public Identifier getIdentifier(){
		return getOntologyProperty().getIdentifier();
	}

	public DocumentableObject getDomain() {
		return this.domain;
	}

	public void setDomain(DocumentableObject domain) {
		this.domain = domain;
	}

	public DocumentableObject getRange() {
		return this.range;
	}

	public void setRange(DocumentableObject range) {
		this.range = range;
	}

	public Collection<DocumentableObject> getInverseReferences() {
	    return Collections.unmodifiableCollection(inverseReferences);
	}
	
	public void setInverseReferences(Collection<DocumentableObject> inverseReferences) {
	    this.inverseReferences = inverseReferences;
	}

	public String getAnchor() {
		return getOntologyProperty().getLocalName();
	}

	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}

	public int getCardinality() {
		return cardinality;
	}

	/**
	 * @param collection the superProperties to set
	 */
	public void setSuperProperties(Collection<OntologyProperty> collection) {
		this.superProperties = collection;
	}

	/**
	 * @return the superProperties
	 */
	public Collection<OntologyProperty> getSuperProperties() {
		return Collections.unmodifiableCollection(superProperties);
	}

	/**
	 * @param subProperties the subProperties to set
	 */
	public void setSubProperties(Collection<OntologyProperty> subProperties) {
		this.subProperties = subProperties;
	}

	/**
	 * @return the subProperties
	 */
	public Collection<OntologyProperty> getSubProperties() {
		return Collections.unmodifiableCollection(subProperties);
	}

	/**
	 * @param inverseOf the inverseOf to set
	 */
	public void setInverseOf(DocumentableObject inverseOf) {
		this.inverseOf = inverseOf;
	}

	/**
	 * @return the inverseOf
	 */
	public DocumentableObject getInverseOf() {
		return inverseOf;
	}
	
	public boolean isDatatypeProperty(){
		return getOntologyProperty().isDatatypeProperty();
	}

	public boolean isObjectProperty(){
		return getOntologyProperty().isObjectProperty();
	}
	
	public boolean isAnnotationProperty(){
		return getOntologyProperty().isAnnotationProperty();
	}
	
	public boolean isTransitiveProperty() {
		return getOntologyProperty().isTransitiveProperty();
	}

	public boolean isSymmetricProperty() {
		return getOntologyProperty().isSymmetricProperty();
	}

	public boolean isFunctionalProperty() {
		return getOntologyProperty().isFunctionalProperty();
	}
	
	public boolean isInverseFunctionalProperty() {
		return getOntologyProperty().isInverseFunctionalProperty();
	}

	public boolean isReflexiveProperty() {
		return getOntologyProperty().isReflexiveProperty();
	}
	
	public boolean isIrreflexiveProperty() {
		return getOntologyProperty().isIrreflexiveProperty();
	}

	public boolean isAsymmetricProperty() {
		return getOntologyProperty().isAsymmetricProperty();
	}

	/**
	 * @param equivalentProperties the equivalentProperties to set
	 */
	public void setEquivalentProperties(Collection<OntologyProperty> equivalentProperties) {
		this.equivalentProperties = equivalentProperties;
	}

	/**
	 * @return the equivalentProperties
	 */
	public Collection<OntologyProperty> getEquivalentProperties() {
		return Collections.unmodifiableCollection(equivalentProperties);
	}

	/**
	 * @param disjointProperties the disjointProperties to set
	 */
	public void setDisjointProperties(Collection<OntologyProperty> disjointProperties) {
		this.disjointProperties = disjointProperties;
	}

	/**
	 * @return the disjointProperties
	 */
	public Collection<OntologyProperty> getDisjointProperties() {
		return Collections.unmodifiableCollection(disjointProperties);
	}


	 
}
