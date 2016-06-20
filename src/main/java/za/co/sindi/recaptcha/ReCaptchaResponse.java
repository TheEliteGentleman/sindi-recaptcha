/**
 * 
 */
package za.co.sindi.recaptcha;

/**
 * @author Bienfait Sindi
 * @since 29 March 2014
 *
 */
public interface ReCaptchaResponse {

	public boolean isValid();
	public String getErrorCode();
}
