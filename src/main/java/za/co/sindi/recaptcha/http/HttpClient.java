/**
 * 
 */
package za.co.sindi.recaptcha.http;

import java.io.IOException;
import java.io.InputStream;

import za.co.sindi.recaptcha.exception.HttpException;

/**
 * @author Bienfait Sindi
 * @since 30 March 2014
 *
 */
public interface HttpClient {

	public void addParameter(String name, String value);
	public int getStatusCode() throws IOException;
	public String getStatusReason() throws IOException;
	public InputStream execute(HttpMethod requestMethod, String url) throws HttpException;
}
