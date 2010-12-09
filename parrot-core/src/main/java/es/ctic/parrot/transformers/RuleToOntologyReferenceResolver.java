package es.ctic.parrot.transformers;

import java.util.Collection;

import org.apache.log4j.Logger;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;

public class RuleToOntologyReferenceResolver extends AbstractDocumentableObjectVisitor {
    
    private static final Logger logger = Logger.getLogger(RuleToOntologyReferenceResolver.class);

    public Object visit(Rule rule) {
        Collection<OntologyProperty> referencedOntologyProperties = rule.getReferencedOntologyProperties();
        for ( OntologyProperty ontologyProperty : referencedOntologyProperties ) {
            logger.debug("Adding reference from rule " + rule + " to ontology property " + ontologyProperty);
            ontologyProperty.addReference(rule);
        }
        return null;
    }

    public Object visit(OntologyProperty property) {
        DocumentableObject domain = property.getDomain();
        if(domain!=null){
            logger.debug("Adding reference from property " + property + " to ontology class " + domain);
            domain.addReference(property);
        }
        DocumentableObject range = property.getRange();
        if(range!=null){
            logger.debug("Adding reference from property " + property + " to ontology class " + range);
            range.addReference(property);
        }
        return null;
    }

}