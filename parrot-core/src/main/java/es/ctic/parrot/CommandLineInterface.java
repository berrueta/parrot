package es.ctic.parrot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

import es.ctic.parrot.appserv.ParrotAppServ;
import es.ctic.parrot.reader.DocumentReader;
import es.ctic.parrot.reader.FileInput;
import es.ctic.parrot.reader.URLInput;
import es.ctic.parrot.reader.jena.JenaOWLReader;
import es.ctic.parrot.reader.naiverifxml.RifXmlReader;

public class CommandLineInterface  {
    
    private static final Logger logger = Logger.getLogger(CommandLineInterface.class);
    
    private static final String DEFAULT_LANG = "EN";
    private static final String DEFAULT_TEMPLATE = "classpath:html/template.vm";
	
    public static void main( String[] args ) throws Exception {
        Options options = createOptions();
        CommandLine cmd = parseCommandLine(args, options);
        // default values
        OutputStream out = System.out;
        String lang = DEFAULT_LANG;
        String template = DEFAULT_TEMPLATE;
        // process options
        if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("parrot", options);
            return;
        }
        if (cmd.hasOption("o")) {
            out = new FileOutputStream(cmd.getOptionValue("o"));
        }
        if (cmd.hasOption("l")) {
            lang = cmd.getOptionValue("l");
        }
        if (cmd.hasOption("t")) {
            template = cmd.getOptionValue("t");
        }
        ParrotAppServ app = new ParrotAppServ();
        DocumentReader ontologyWrapper = new JenaOWLReader();
        DocumentReader ruleWrapper = new RifXmlReader(ontologyWrapper);
        app.setOntologyWrapper(ontologyWrapper);
        app.setRuleWrapper(ruleWrapper);
        try {
            InputStream templateInputStream = openTemplateInputStream(template);
            DocumentaryProject dp = new DocumentaryProject(templateInputStream, out, lang);
            for ( String inputFilename : cmd.getArgs() ) {
                if (inputFilename.startsWith("http:")) {
                    dp.addInput(new URLInput(new URL(inputFilename)));
                } else {
                    dp.addInput(new FileInput(new File(inputFilename)));
                }
            }
//            dp.addInput(new URLInput(new URL("http://ontorule-project.eu/resources/steel.owl"), "application/rdf+xml"));
//            dp.addInput(new URLInput(new URL("http://ontologies.ezweb.morfeo-project.org/reco/reco.owl"), "application/rdf+xml"));
//            dp.addInput(new URLInput(new URL("http://ontorule-project.eu/resources/rif/steel"), "application/rif+xml"));              
//            dp.addInput(new URLInput(new URL("http://ontorule-project.eu/resources/rif/steel.rif"), "application/rif+xml"));
//            dp.addInput(new URLInput(new URL("http://idi.fundacionctic.org/docom/docom.owl"), "application/rdf+xml"));
//            dp.addInput(new URLInput(new URL("http://www.w3.org/2005/rules/test/repository/tc/OWL_Combination_Vocabulary_Separation_Inconsistency_2/OWL_Combination_Vocabulary_Separation_Inconsistency_2-premise.rif"), "application/rif+xml"));
//            dp.addInput(new URLInput(new URL("http://localhost/~berrueta/ontorule/sample-rule.rif.xml"), "application/rif+xml"));
            app.createDocumentation(dp);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream openTemplateInputStream(String template) throws FileNotFoundException {
        if (template.startsWith("classpath:")) {
            String templateSuffix = template.substring("classpath:".length());
            logger.info("Reading template " + templateSuffix + " from classpath");
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(templateSuffix);
            if (is == null) {
                throw new FileNotFoundException(template);
            }
            return is;
        } else {
            logger.info("Reading template " + template + " from filesystem");
            return new FileInputStream(template);
        }
    }

    private static CommandLine parseCommandLine(String[] args, Options options)
            throws ParseException {
        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);
        return cmd;
    }

    private static Options createOptions() {
        Options options = new Options();
        options.addOption("o", true, "output file");
        options.addOption("h", false, "print help");
        options.addOption("l", true, "language (default: " + DEFAULT_LANG + ")");
        options.addOption("t", true, "template (default: " + DEFAULT_TEMPLATE + ")");
        return options;
    }
}