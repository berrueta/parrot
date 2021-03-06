package es.ctic.parrot.de;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * A register for the documentable elements.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class DocumentableObjectRegister {

    private static final Logger logger = Logger.getLogger(DocumentableObjectRegister.class);
    
    private Map<Identifier, DocumentableObject> documentableObjects = new HashMap<Identifier, DocumentableObject>();
    
    /**
     * Constructs a register (Suppress default constructor for noninstantiability).
     */
	public DocumentableObjectRegister() {
		logger.debug("Created DocumentableObjectRegister");
	}

    /**
     * Register a given documentable element.
     * @param documentableObject the documentable element to register. 
     */
    public void registerDocumentableObject(DocumentableObject documentableObject) {
        if (documentableObjects.containsKey(documentableObject.getIdentifier())){
        	logger.debug("Duplicate. Not registered documentable object [" +documentableObject.getClass().getSimpleName() + "] " + documentableObject);
        } else{
            logger.debug("Registering documentable object [" +documentableObject.getClass().getSimpleName() + "] " + documentableObject);
        	documentableObjects.put(documentableObject.getIdentifier(), documentableObject);
        }
    }
    
    /**
     * Returns the documentable element.
     * @param identifier Identifier of the documentable element.
     * @return the value or null if the register contains no mapping for this identifier.  
     */
    public DocumentableObject findDocumentableObject(Identifier identifier) {
        return documentableObjects.get(identifier);
    }
    
    /**
     * Returns the documentable object for that identifier.
     * @return the documentable object for that identifier.
     */
    public DocumentableObject getDocumentableObject(Identifier identifier){
    	if (this.containsIdentifier(identifier)) {
    		return findDocumentableObject(identifier);
    	} else {
    		if (identifier instanceof NamedIdentifier){
    			return new ExternalDocumentableObject((NamedIdentifier) identifier);
    		}
    		logger.info("Corner Case. identifier="+identifier);
    		return new ExternalDocumentableObject((NamedIdentifier) identifier); // FIXME corner case
    	}
    }
    
    /**
     * Returns the documentable elements stored in the register.
     * @return the documentable elements stored in the register.
     */
    public Collection<DocumentableObject> getAllRegisteredDocumentableObjects() {
        return documentableObjects.values();
    }

    /**
     * Returns <code>true</code> if the identifier is present in the register, otherwise <code>false</code>.
     * @param identifier Identifier of the documentable element.
     * @return <code>true</code> if the identifier is present in the register, otherwise <code>false</code>.
     */
    public boolean containsIdentifier(Identifier identifier) {
        return documentableObjects.containsKey(identifier);
    }

}
