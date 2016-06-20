/**
 * 
 */
package za.co.sindi.recaptcha;

/**
 * @author Bienfait Sindi
 * @since 30 March 2014
 *
 */
public final class Constants {

	public static final String RECAPTCHA_API_SERVER_URL = "www.google.com/recaptcha/api";
	public static final String RECAPTCHA_VERIFICATION_API_URL = " http://" + RECAPTCHA_API_SERVER_URL + "/verify";
	public static final String RECAPTCHA_CHALLENGE_FIELD = "recaptcha_challenge_field";
	public static final String RECAPTCHA_RESPONSE_FIELD = "recaptcha_response_field";
	public static final String MANUAL_CHALLENGE = "manual_challenge";
	
	private Constants() {
		
	}
}
