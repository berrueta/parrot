package es.ctic.parrot.reader.rifle;

import net.sourceforge.rifle.psparser.ErrorReporter;

import org.apache.log4j.Logger;

public class ErrorReporterImpl implements ErrorReporter {

    private static final Logger logger = Logger.getLogger(ErrorReporterImpl.class);
    
    private String firstError = null;
    
    public void reportError(String msg) {
        logger.error("While parsing RIF PS document: " + msg);
        if (getFirstError() == null) {
            this.firstError = msg;
        }
    }

    public String getFirstError() {
        return firstError;
    }

}
