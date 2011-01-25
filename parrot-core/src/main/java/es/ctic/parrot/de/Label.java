package es.ctic.parrot.de;

import java.util.Locale;

public class Label {
	
	private static final String SKOS_XL_PREF_LABEL = "http://www.w3.org/2008/05/skos-xl#prefLabel";
	private static final String SKOS_CORE_PREF_LABEL = "http://www.w3.org/2004/02/skos/core#prefLabel";
	
	private String qualifier;
	private String uri;
	private String text;
	private Locale locale;
	
	/**
	 * @return the qualifier
	 */
	public String getQualifier() {
		return qualifier;
	}
	/**
	 * @param qualifier the qualifier to set
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public String toString(){
		return text;
		
	}
	
	public boolean isPrefLabel(){
		if (this.getQualifier().equals(SKOS_XL_PREF_LABEL) || this.getQualifier().equals(SKOS_CORE_PREF_LABEL)){
			return true;
		}
		else {
			return false;
		}
	}

	
}
