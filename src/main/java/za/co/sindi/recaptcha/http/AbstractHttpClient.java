/**
 * 
 */
package za.co.sindi.recaptcha.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Bienfait Sindi
 * @since 30 March 2014
 */
public abstract class AbstractHttpClient implements HttpClient {

	private Map<String, String[]> parameterMap = new LinkedHashMap<String, String[]>();
	private int statusCode;
	private String statusReason;
	
	/* (non-Javadoc)
	 * @see za.co.sindi.recaptcha.http.HttpClient#addParameter(java.lang.String, java.lang.String)
	 */
	public void addParameter(String name, String value) {
		// TODO Auto-generated method stub
		if (!parameterMap.containsKey(name)) {
			parameterMap.put(name, new String[] {value});
		} else {
			List<String> list = new ArrayList<String>(Arrays.asList(parameterMap.get(name)));
			if (!list.contains(value)) {
				list.add(value);
			}
			
			//Remove
			parameterMap.remove(name);
			//Add
			parameterMap.put(name, list.toArray(new String[list.size()]));
		}
	}
	
	protected Set<Entry<String, String[]>> parameterEntrySet() {
		return parameterMap.entrySet();
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.recaptcha.http.HttpClient#getStatusCode()
	 */
	public int getStatusCode() throws IOException {
		// TODO Auto-generated method stub
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	protected void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.recaptcha.http.HttpClient#getStatusReason()
	 */
	public String getStatusReason() throws IOException {
		// TODO Auto-generated method stub
		return statusReason;
	}

	/**
	 * @param statusReason the statusReason to set
	 */
	protected void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	protected String toParameterQueryString(Set<Entry<String, String[]>> entries) {
		if (entries == null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> entry : entries) {
			for (String value : entry.getValue()) {
				if (sb.length() > 0) {
					sb.append("&");
				}
				
				sb.append(entry.getKey()).append("=").append(value).append("");
			}
		}
		
		return sb.toString();
	}
}
