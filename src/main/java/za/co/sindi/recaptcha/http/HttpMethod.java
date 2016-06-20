/**
 * 
 */
package za.co.sindi.recaptcha.http;

/**
 * @author Bienfait Sindi
 * @since 30 March 2014
 *
 */
public enum HttpMethod {
	DELETE("DELETE"),
	GET("GET"),
	POST("POST"),
	PUT("PUT")
	;
	
	private final String text;

	/**
	 * @param text
	 */
	private HttpMethod(final String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return text;
	}
}
