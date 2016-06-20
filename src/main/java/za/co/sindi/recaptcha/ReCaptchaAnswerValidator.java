/**
 * 
 */
package za.co.sindi.recaptcha;

import za.co.sindi.recaptcha.http.HttpClient;

/**
 * @author Bienfait Sindi
 * @since 30 March 2014
 *
 */
public interface ReCaptchaAnswerValidator {

	public void setVerifyApiUrl(String verifyApiUrl);
	public void setHttpClient(HttpClient httpClient);
	public ReCaptchaResponse validate(final String privateKey, final String remoteIP, final String challenge, final String response);
}
