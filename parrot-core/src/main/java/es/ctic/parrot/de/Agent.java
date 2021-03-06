package es.ctic.parrot.de;

/**
 * An agent.
 * 
 * @author Carlos Tejo Alonso (<a href="http://www.fundacionctic.org">Fundación CTIC</a>)
 * @version 1.0
 * @since 1.0
 *
 */
public class Agent {
	
	private String name;
	private String homepage;
	private String mbox;

	public Agent(String name, String homepage, String mbox) {
		super();
		this.name = name;
		this.homepage = homepage;
		this.setMbox(mbox);
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param homepage the homepage to set
	 */
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	/**
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((homepage == null) ? 0 : homepage.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Agent))
			return false;
		Agent other = (Agent) obj;
		if (homepage == null) {
			if (other.homepage != null)
				return false;
		} else if (!homepage.equals(other.homepage))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the mbox
	 */
	public String getMbox() {
		return mbox;
	}

	/**
	 * @param mbox the mbox to set
	 */
	public void setMbox(String mbox) {
		this.mbox = mbox;
	}	
}