package es.ctic.parrot.reader.rifle;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import es.ctic.parrot.de.AbstractVersionable;
import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.de.DocumentableOntologicalObject;
import es.ctic.parrot.de.Identifier;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.RelatedDocument;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.de.URIIdentifier;
import es.ctic.parrot.reader.jena.OntResourceAnnotationStrategy;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of {@link es.ctic.parrot.de.RuleSet} coupled to <a href="https://bitbucket.org/fundacionctic/rifle">RIFLE</a>. 
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class RuleSetImpl extends AbstractVersionable implements RuleSet {
	
	private net.sourceforge.rifle.ast.Group ruleSet;
	private DocumentableObject parent;

    private Identifier identifier;
	
	private static final Logger logger = Logger.getLogger(RuleSetImpl.class);
	
	/**
 	 * Constructs a rule set.
	 * @param group the rule set.
	 * @param register the register.
	 * @param annotationStrategy the annotation strategy.
	 * @param ontModel the ontModel.
	 */

	public RuleSetImpl(net.sourceforge.rifle.ast.Group group, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy, OntModel ontModel) {
		this.ruleSet = group;
		this.setRegister(register);
		this.setAnnotationStrategy(annotationStrategy);
		
		if (ruleSet.getId() == null) {
			// Rule without identifier
		    this.identifier = new RifleAnonymousIdentifier(Integer.toString(ruleSet.getLocalId()));
		} else {
			// Rule with identifier
			this.identifier = new URIIdentifier(ruleSet.getId());
			
			Model auxModel = ModelFactory.createDefaultModel().add(ruleSet.getIriMeta());
			StmtIterator listStatements = auxModel.listStatements(ResourceFactory.createResource(ruleSet.getId()), null, (RDFNode) null);
			ontModel.add(listStatements ); // add metadata ONLY about this ruleset 
		}

		this.setOntResource(ontModel.getOntResource(getURI()));
	}
	
	/**
	 * Sets the parent element.
	 * @param parent the parent to set.
	 */
	public void setParent(DocumentableObject parent) {
		this.parent = parent;
	}

	/**
	 * Returns the parent
	 * @return the parent
	 */
	public DocumentableObject getParent() {
		return parent;
	}
    
	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
		return visitor.visit(this);
	}

	public Identifier getIdentifier() {
		return identifier;
	}
	
	public String getURI() {
		if (ruleSet.getId() == null) {
		    return null;
		} else {
			return getIdentifier().toString();
		}	
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
	
	public int compareTo(DocumentableOntologicalObject o) {
		return getURI().compareTo(o.getURI());
	}

	public Collection<DocumentableOntologicalObject> getReferencedOntologicalObjects() {
		Set<DocumentableOntologicalObject> referencedOntologicalObjects = new TreeSet<DocumentableOntologicalObject>(new DocumentableObjectComparator());
		// FIXME implement this method properly
		return referencedOntologicalObjects;
	}

	public Collection<Rule> getRules() {
		return Collections.unmodifiableCollection(astRuleCollectionToRuleCollection(ruleSet.getRules()));
	}
	
	private Collection<es.ctic.parrot.de.Rule> astRuleCollectionToRuleCollection(Collection<net.sourceforge.rifle.ast.Rule> astRules) {
		Collection<es.ctic.parrot.de.Rule> ruleList = new LinkedList<es.ctic.parrot.de.Rule>();

		for(net.sourceforge.rifle.ast.Rule astRule : astRules){
			Rule rule = (Rule) this.getRegister().findDocumentableObject(new URIIdentifier(astRule.getId()));
			if (rule != null){
				ruleList.add(rule);
			}else{
				// anonymous rule
				rule = (Rule) this.getRegister().findDocumentableObject(new RifleAnonymousIdentifier(Integer.toString(astRule.getLocalId())));
				if (rule != null){ 
					ruleList.add(rule);
				} else {
					logger.debug("Rule not found in register " + astRule.toString());
				}
			}
		}
		return ruleList;
	}

	public String getStrategy() {
		return ruleSet.getStrategy();
	}

	public Integer getPriority() {
		return ruleSet.getPriority();
	}

	public Collection<RuleSet> getRuleSets() {
		return Collections.unmodifiableCollection(astGroupCollectionToRuleSetCollection(ruleSet.getGroups()));

	}

	private Collection<es.ctic.parrot.de.RuleSet> astGroupCollectionToRuleSetCollection(Collection<net.sourceforge.rifle.ast.Group> astGroups) {
		
		Collection<es.ctic.parrot.de.RuleSet> ruleSetList = new LinkedList<es.ctic.parrot.de.RuleSet>();

		for(net.sourceforge.rifle.ast.Group group : astGroups){
			RuleSet ruleSet = (RuleSet) this.getRegister().findDocumentableObject(new URIIdentifier(group.getId()));
			if (ruleSet != null){
				ruleSetList.add(ruleSet);
			}else{
				// anonymous rule
				ruleSet = (RuleSet) this.getRegister().findDocumentableObject(new RifleAnonymousIdentifier(Integer.toString(group.getLocalId())));
				if (ruleSet != null){ 
					ruleSetList.add(ruleSet);
				} else {
					logger.debug("Group not found in register " + group.toString());
				}
			}
		}
		return ruleSetList;
	}
	
    public String getKindString() {
        return Kind.RULE_SET.toString();
    }
    
    /****************************************************************************/
    
    public String getDescription(Locale locale) {
    	return getAnnotationStrategy().getDescription(getOntResource(), locale);
    }
    
    public Collection<Label> getLabels(){
   		return getAnnotationStrategy().getLabels(getOntResource(), null);
   	}

    public Collection<Label> getLabels(Locale locale){
   		return getAnnotationStrategy().getLabels(getOntResource(), locale);
   	}

    public String getLabel() {
   		return getLabel(null);
    }

    public String getLabel(Locale locale) {
    	// Anonymous ruleset
    	if (this.isAnonymous()){
    		return getKindString() + getIdentifier().toString();
    	} else {
   			return getAnnotationStrategy().getLabel(getOntResource(), locale);
    	}
    }
    
	public Collection<RelatedDocument> getRelatedDocuments(Locale locale) {
        return getAnnotationStrategy().getRelatedDocuments(getOntResource(), locale);
    }

	/**
	 * Returns <code>true</code> if the ruleset is anonymous, otherwise returns <code>false</code>.
	 * @return <code>true</code> if the ruleset is anonymous, otherwise returns <code>false</code>.
	 */
	private boolean isAnonymous(){
    	if (this.getURI() == null){
    		return true;
    	} else {
    		return false;
    	}
	}
}

