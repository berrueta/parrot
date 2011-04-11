package es.ctic.parrot.reader.jena;

import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.shared.JenaException;

import es.ctic.parrot.de.DocumentableObjectRegister;
import es.ctic.parrot.transformers.DocumentableObjectVisitor;
import es.ctic.parrot.transformers.TransformerException;

/**
 * An implementation of the Ontology (documentable element) interface coupled to <a href="http://openjena.org/">Jena</a>.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class OntologyJenaImpl extends AbstractJenaDocumentableObject implements es.ctic.parrot.de.Ontology {
	
	/**
	 * Constructs an ontology.
	 * @param ontology the Jena ontology to set.
	 * @param register the register to set
	 * @param annotationStrategy the annotation strategy to set.
	 */
	public OntologyJenaImpl(OntResource ontology, DocumentableObjectRegister register, OntResourceAnnotationStrategy annotationStrategy) {
		super(ontology, register, annotationStrategy);
	}

	public Object accept(DocumentableObjectVisitor visitor) throws TransformerException {
        try{
        	return visitor.visit(this);
        }catch (JenaException e) {
        	throw new TransformerException(e);
        }
    }

	public String getPreferredPrefix() {
		return getAnnotationStrategy().getPreferredPrefix(getOntResource());
	}
	
	public String getPreferredNamespace() {
		return getAnnotationStrategy().getPreferredNamespace(getOntResource());
	}

    public String getKindString() {
        return Kind.ONTOLOGY.toString();
    }
    
}
