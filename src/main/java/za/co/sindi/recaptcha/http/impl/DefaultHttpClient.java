/**
 * 
 */
package za.co.sindi.recaptcha.http.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.sindi.recaptcha.exception.HttpException;
import za.co.sindi.recaptcha.http.AbstractHttpClient;
import za.co.sindi.recaptcha.http.HttpMethod;

/**
 * @author Bienfait Sindi
 * @since 30 March 2014
 *
 */
public class DefaultHttpClient extends AbstractHttpClient {

	private static final Logger LOGGER = Logger.getLogger(DefaultHttpClient.class.getName());
	
	/* (non-Javadoc)
	 * @see za.co.sindi.recaptcha.http.HttpClient#execute(za.co.sindi.recaptcha.http.HttpMethod, java.lang.String)
	 */
	public InputStream execute(HttpMethod requestMethod, String url) throws HttpException {
		// TODO Auto-generated method stub
		if (requestMethod == null) {
			throw new IllegalArgumentException("HTTP request method is null.");
		}
		
		if (url == null) {
			throw new IllegalArgumentException("HTTP URL is null.");
		}
		
		int questionMarkPos = url.indexOf('?');		
		String queryString = toParameterQueryString(parameterEntrySet());
		OutputStream output = null;
		
		try {
			String s = url;
			if (requestMethod == HttpMethod.GET) {
				s += ((questionMarkPos > 0) ? "&" : "?") + queryString;
			}
			
			HttpURLConnection urlConnection = (HttpURLConnection) new URL(s).openConnection();
			urlConnection.setRequestMethod(requestMethod.toString());
			urlConnection.setDoInput(true);
			
			if (requestMethod == HttpMethod.POST) {
				urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				urlConnection.setRequestProperty("Content-Length", Integer.toString(queryString.getBytes("UTF-8").length));
				
				urlConnection.setDoOutput(true);
				output = urlConnection.getOutputStream();
				output.write(queryString.getBytes("UTF-8"));
				output.flush();
			}
			
			setStatusCode(-1);
			setStatusReason(null);
			
			InputStream input = null;
			try {
				input = urlConnection.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				input = urlConnection.getErrorStream();
			}
			
			setStatusCode(urlConnection.getResponseCode());
			setStatusReason(urlConnection.getResponseMessage());
			
			return input;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new HttpException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new HttpException(e);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					LOGGER.log(Level.WARNING, "Error closing OutputStream.", e);
				}
			}
		}
	}
}
