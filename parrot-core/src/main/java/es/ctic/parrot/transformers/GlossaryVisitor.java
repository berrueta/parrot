package es.ctic.parrot.transformers;

import java.util.Collection;
import java.util.Locale;

import es.ctic.parrot.de.DocumentableObject;
import es.ctic.parrot.de.Label;
import es.ctic.parrot.de.Ontology;
import es.ctic.parrot.de.OntologyClass;
import es.ctic.parrot.de.OntologyIndividual;
import es.ctic.parrot.de.OntologyProperty;
import es.ctic.parrot.de.Rule;
import es.ctic.parrot.de.RuleSet;
import es.ctic.parrot.docmodel.Document;
import es.ctic.parrot.docmodel.Glossary;

/**
 * 
 * Visitor that creates a glossary from the labels of the documentable elements that are registered.
 * <code>GlossaryVisitor</code> is an implementation of the Visitor pattern.
 * Please refer to the Gang of Four book of Design Patterns for more details on the Visitor pattern.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class GlossaryVisitor extends AbstractDocumentableObjectVisitor {

    private final Glossary glossary;
    private final Locale locale;
    
    /**
     * Constructs a glossary visitor.
     * @param document the document which glossary will be fulfilled.
     * @param locale the locale.
     */
    public GlossaryVisitor(Document document, Locale locale) {
        this.glossary = document.getGlossary();
        this.locale = locale;
    }

    @Override
    public Object visit(OntologyClass object) throws TransformerException {
        return visitDocumentableObject(object);
    }

    @Override
    public Object visit(OntologyProperty object) throws TransformerException {
        return visitDocumentableObject(object);
    }

    @Override
    public Object visit(Rule object) throws TransformerException {
        return visitDocumentableObject(object);
    }

    @Override
    public Object visit(Ontology ontology) throws TransformerException {
        return visitDocumentableObject(ontology);
    }

    @Override
    public Object visit(OntologyIndividual ontologyIndividual)
            throws TransformerException {
        return visitDocumentableObject(ontologyIndividual);
    }

    @Override
    public Object visit(RuleSet ruleSet) throws TransformerException {
        return visitDocumentableObject(ruleSet);
    }

    /**
     * Adds entries in the glossary for the labels of the passed documentable element.
     * @param object the documentable element.
     * @return always null.
     */
    private Object visitDocumentableObject(DocumentableObject object) {
        Collection<Label> labels = object.getLabels(locale);
        for (Label label : labels) {
            glossary.addReference(label.getText(), object);
        }
        return null;
    }


}
