package es.ctic.parrot.project;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

import es.ctic.parrot.generators.OutputGenerator.Profile;
import es.ctic.parrot.reader.Input;

/**
 * A documentary project represents a unit of work executed by Parrot. It
 * contains the information required by Parrot to generate a document.
 * 
 * @author <a href="http://www.fundacionctic.org">CTIC Foundation</a>
 * @version 1.0
 * @since 1.0
 *
 */
public class DocumentaryProjectImpl implements DocumentaryProject {

	private final Collection<Input> inputs = new HashSet<Input>();;
	private final Locale locale;
	private final String prologueURL;
	private final String appendixURL;
	private final String generatedReportUrl;
	private final String customizeCssUrl;
	private final Profile profile;

	public static class Builder {
		// Required parameters
		// Optional parameters - initialized to default values
		private Locale locale = Locale.ENGLISH;
		private String prologueURL;
		private String appendixURL;
		private String generatedReportUrl;
		private String customizeCssUrl;
		private Profile profile = Profile.TECHNICAL;

		public Builder() {
			// Required parameters
		}

		public Builder locale(Locale val) { 
			locale = val; 	
			return this;
		}

		public Builder prologueURL(String val){
			prologueURL = val;
			return this;
		}

		public Builder appendixURL(String val){
			appendixURL = val;
			return this;
		}

		public Builder generatedReportUrl(String val){
			generatedReportUrl = val;
			return this;
		}

		public Builder customizeCssUrl(String val){
			customizeCssUrl = val;
			return this;
		}
		
		public Builder profile(Profile val) { 
			profile = val; 	
			return this;
		}


		public DocumentaryProject build() {
			return new DocumentaryProjectImpl(this);
		}


	}

	private DocumentaryProjectImpl(Builder builder) {
		locale = builder.locale;
		prologueURL = builder.prologueURL;
		appendixURL = builder.appendixURL;
		generatedReportUrl = builder.generatedReportUrl;
		customizeCssUrl = builder.customizeCssUrl;
		profile = builder.profile;
	}

	/* (non-Javadoc)
	 * @see es.ctic.parrot.DocumentaryProject#addInput(es.ctic.parrot.reader.Input)
	 */
	public boolean addInput(Input input) {
		return inputs.add(input);
	}

	/* (non-Javadoc)
	 * @see es.ctic.parrot.DocumentaryProject#addAllInput(java.util.Collection)
	 */
	public boolean addAllInput(Collection<Input> collection) {
		return inputs.addAll(collection);
	}

	/* (non-Javadoc)
	 * @see es.ctic.parrot.DocumentaryProject#getInputs()
	 */
	public Collection<Input> getInputs() {
		return Collections.unmodifiableCollection(inputs);
	}

	/* (non-Javadoc)
	 * @see es.ctic.parrot.DocumentaryProject#getLocale()
	 */
	public Locale getLocale() {
		return locale;
	}

	/* (non-Javadoc)
	 * @see es.ctic.parrot.DocumentaryProject#getPrologueURL()
	 */
	public String getPrologueURL() {
		return prologueURL;
	}

	/* (non-Javadoc)
	 * @see es.ctic.parrot.DocumentaryProject#getAppendixURL()
	 */
	public String getAppendixURL() {
		return appendixURL;
	}


	/* (non-Javadoc)
	 * @see es.ctic.parrot.DocumentaryProject#getReportURL()
	 */
	public String getGeneratedReportURL() {
		return generatedReportUrl;
	}

	/* (non-Javadoc)
	 * @see es.ctic.parrot.DocumentaryProject#getCustomizeCssUrl()
	 */
	public String getCustomizeCssUrl() {
		return customizeCssUrl;
	}

	public Profile getProfile() {
		return profile;
	}


}
