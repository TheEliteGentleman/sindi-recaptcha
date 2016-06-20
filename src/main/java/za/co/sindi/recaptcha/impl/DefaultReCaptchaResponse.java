/**
 * 
 */
package za.co.sindi.recaptcha.impl;

import za.co.sindi.recaptcha.ReCaptchaResponse;

/**
 * @author Bienfait Sindi
 * @since 30 March 2014
 *
 */
public class DefaultReCaptchaResponse implements ReCaptchaResponse {

	private boolean valid;
	private String errorCode;
	
	/**
	 * @param valid
	 * @param errorCode
	 */
	public DefaultReCaptchaResponse(boolean valid, String errorCode) {
		super();
		this.valid = valid;
		this.errorCode = errorCode;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.recaptcha.RecaptchaResponse#isValid()
	 */
	public boolean isValid() {
		// TODO Auto-generated method stub
		return valid;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.recaptcha.RecaptchaResponse#getErrorCode()
	 */
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return errorCode;
	}
}
