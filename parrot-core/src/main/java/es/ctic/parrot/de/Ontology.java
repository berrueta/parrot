package es.ctic.parrot.de;

import java.util.List;

public interface Ontology extends DocumentableOntologicalObject {
	
	public abstract String getPreferredPrefix();
	public abstract String getPreferredNamespace();
	public abstract String getVersion();
	public abstract List<String> getCreators();
	public abstract List<String> getContributors();
	public abstract String getDate();
	public abstract String getRights();
	public abstract String getLicenseLabel();

}
