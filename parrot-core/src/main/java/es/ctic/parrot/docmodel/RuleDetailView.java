package es.ctic.parrot.docmodel;

import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;

public class RuleDetailView implements DetailView{

    private static final Logger logger = Logger.getLogger(RuleDetailView.class);
    private Rule rule;
    private Identifier identifier;
	private String uri;
	private String label;
	private String comment;
	private String date;
	private Collection<DocumentableOntologicalObject> referencedOntologicalObjects;
    
    /**
	 * @return the referencedOntologicalObjects
	 */
	public Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects() {
		return Collections.unmodifiableCollection(referencedOntologicalObjects);
	}

	/**
	 * @param referencedOntologicalObjects the referencedOntologicalObjects to set
	 */
	public void setReferencedOntologicalObjects(
			Collection<DocumentableOntologicalObject> referencedOntologicalObjects) {
		this.referencedOntologicalObjects = referencedOntologicalObjects;
	}

	public RuleDetailView(Rule rule) {
        this.rule=rule;
        logger.debug("Created " + this.getClass());
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

	public String getAnchor() {
		return rule.getLocalName();
	}
	
	public String getUri(){
		return uri;
	}
	
	public void setUri(String uri){
		this.uri=uri;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}
    
}
