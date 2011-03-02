package es.ctic.parrot.docmodel;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.Ontology;

public class OntologyDetailView extends AbstractOntologicalObjectDetailView implements DetailView{
	
    private static final Logger logger = Logger.getLogger(OntologyDetailView.class);

	private String version;
	private List<String> creators;
	private List<String> contributors;
	private String preferredPrefix;
	private String preferredNamespace;
	private String date;
	private String rights;
	private String licenseLabel;
	
	private OntologyDetailView(){
		logger.debug("Created " + this.getClass());
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setCreators(List<String> creators) {
		this.creators = creators;
	}

	public List<String> getCreators() {
		return creators;
	}

	public void setPreferredPrefix(String preferredPrefix) {
		this.preferredPrefix = preferredPrefix;
	}

	public String getPreferredPrefix() {
		return preferredPrefix;
	}

	public void setContributors(List<String> contributors) {
		this.contributors = contributors;
	}

	public List<String> getContributors() {
		return contributors;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}
	
	/**
	 * @param preferredNamespace the preferredNamespace to set
	 */
	public void setPreferredNamespace(String preferredNamespace) {
		this.preferredNamespace = preferredNamespace;
	}

	/**
	 * @return the preferredNamespace
	 */
	public String getPreferredNamespace() {
		return preferredNamespace;
	}
	
    public static OntologyDetailView createFromOntology(Ontology object, Locale locale) {
    	
	    OntologyDetailView details = new OntologyDetailView();
		
	    details.setUri(object.getURI());
		details.setLabel(object.getLabel(locale));
		details.setComment(object.getComment(locale));
		details.setVersion(object.getVersion());
		details.setCreators(object.getCreators());
		details.setDate(object.getDate());
		details.setRights(object.getRights());
		details.setLicenseLabel(object.getLicenseLabel());
		details.setContributors(object.getContributors());
		details.setPreferredPrefix(object.getPreferredPrefix());
		details.setPreferredNamespace(object.getPreferredNamespace());
		details.setLabels(object.getLabels());
		details.setRelatedDocuments(object.getRelatedDocuments(locale));
		
		details.setAnchor(object.getLocalName());
		details.setIdentifier(object.getIdentifier());
		
		return details;

    }

	/**
	 * @param rights the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}

	/**
	 * @return the rights
	 */
	public String getRights() {
		return rights;
	}

	/**
	 * @param licenseLabel the licenseLabel to set
	 */
	public void setLicenseLabel(String licenseLabel) {
		this.licenseLabel = licenseLabel;
	}

	/**
	 * @return the licenseLabel
	 */
	public String getLicenseLabel() {
		return licenseLabel;
	}




}
