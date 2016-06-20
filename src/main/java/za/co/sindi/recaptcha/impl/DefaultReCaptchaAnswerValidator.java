/**
 * 
 */
package za.co.sindi.recaptcha.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.recaptcha.Constants;
import za.co.sindi.recaptcha.ReCaptchaAnswerValidator;
import za.co.sindi.recaptcha.ReCaptchaResponse;
import za.co.sindi.recaptcha.exception.HttpException;
import za.co.sindi.recaptcha.http.HttpClient;
import za.co.sindi.recaptcha.http.HttpMethod;
import za.co.sindi.recaptcha.http.impl.DefaultHttpClient;

/**
 * @author Bienfait Sindi
 * @since 30 March 2014
 *
 */
public class DefaultReCaptchaAnswerValidator implements ReCaptchaAnswerValidator {

	private static final Logger LOGGER = Logger.getLogger(DefaultReCaptchaAnswerValidator.class.getName());
	
	private String verifyApiUrl;
	private HttpClient httpClient;
	
	/**
	 * 
	 */
	public DefaultReCaptchaAnswerValidator() {
		this(Constants.RECAPTCHA_VERIFICATION_API_URL, new DefaultHttpClient());
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param apiUrl
	 * @param httpClient
	 */
	public DefaultReCaptchaAnswerValidator(String apiUrl, HttpClient httpClient) {
		super();
		this.verifyApiUrl = apiUrl;
		this.httpClient = httpClient;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.recaptcha.RecaptchaAnswerValidator#setVerifyApiUrl(java.lang.String)
	 */
	public void setVerifyApiUrl(String apiUrl) {
		// TODO Auto-generated method stub
		this.verifyApiUrl = apiUrl;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.recaptcha.RecaptchaAnswerValidator#setHttpClient(za.co.sindi.recaptcha.http.HttpClient)
	 */
	public void setHttpClient(HttpClient httpClient) {
		// TODO Auto-generated method stub
		this.httpClient = httpClient;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.recaptcha.RecaptchaAnswerValidator#validate(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ReCaptchaResponse validate(String privateKey, String remoteIP, String challenge, String response) {
		// TODO Auto-generated method stub
		if (privateKey == null || privateKey.isEmpty()) {
			throw new IllegalArgumentException("No reCAPTCHA private key was provided.");
		}
		
		if (remoteIP == null || remoteIP.isEmpty()) {
			throw new IllegalArgumentException("No user's remote IP address was provided.");
		}
		
		if (challenge == null || challenge.isEmpty()) {
			throw new IllegalArgumentException("No \"" + Constants.RECAPTCHA_CHALLENGE_FIELD + "\" value was provided.");
		}
		
		if (response == null || response.isEmpty()) {
			throw new IllegalArgumentException("No \"" + Constants.RECAPTCHA_RESPONSE_FIELD + "\" value was provided.");
		}
		
		if (verifyApiUrl == null || verifyApiUrl.isEmpty()) {
			throw new IllegalStateException("No verify API URL was provided.");
		}
		
		if (httpClient == null) {
			throw new IllegalStateException("No HttpClient was provided.");
		}
		
		ReCaptchaResponse reCaptchaResponse = null;
		try {
			httpClient.addParameter("privatekey", privateKey);
			httpClient.addParameter("remoteip", remoteIP);
			httpClient.addParameter("challenge", challenge);
			httpClient.addParameter("response", response);
			String responseString = toString(httpClient.execute(HttpMethod.POST, verifyApiUrl));
			if (httpClient.getStatusCode() != 200) {
				throw new HttpException("HTTP " + httpClient.getStatusCode() + ": " + httpClient.getStatusCode() +"\n" + responseString);
			}
			
			if (responseString != null && !responseString.isEmpty()) {
				String[] responses = responseString.split("\r\n|\r|\n");
				boolean valid = Boolean.parseBoolean(responses[0]);
				String errorCode = (responses.length > 1) ? responses[1] : null;
				reCaptchaResponse = new DefaultReCaptchaResponse(valid, errorCode);
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		
		return reCaptchaResponse;
	}
	
	private String toString(InputStream inputStream) throws IOException {
		if (inputStream == null) {
			return null;
		}
		
		StringWriter sw = new StringWriter();
		int c;
		
		while ((c = inputStream.read()) != -1) {
			sw.write(c);
		}
		
		return sw.toString();
	}
}
