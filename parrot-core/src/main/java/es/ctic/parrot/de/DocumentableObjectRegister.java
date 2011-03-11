package es.ctic.parrot.de;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * A register for the documentable elements.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class DocumentableObjectRegister {

    private static final Logger logger = Logger.getLogger(DocumentableObjectRegister.class);
    
    private Map<Identifier, DocumentableObject> documentableObjects = new HashMap<Identifier, DocumentableObject>();

    /**
     * Register a given documentable element.
     * @param documentableObject the documentable element to register. 
     */
    public void registerDocumentableObject(DocumentableObject documentableObject) {
        logger.debug("Registering documentable object [" +documentableObject.getClass().getSimpleName() + "] " + documentableObject);
        documentableObjects.put(documentableObject.getIdentifier(), documentableObject);
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
     * Returns the documentable elements stored in the register.
     * @return the documentable elements stored in the register.
     */
    public Collection<DocumentableObject> getDocumentableObjects() {
        return documentableObjects.values();
    }

}
