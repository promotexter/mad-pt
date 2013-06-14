/**
 * 
 */
package com.pt.mobile.madpt.container;

/**
 * @author f3rmin
 *
 */
public class SocketDetail {

	private String url;
	private Integer port;
	
	public SocketDetail()
	{
		url = "";
		port = 0;
	}
	
	public SocketDetail(String url, Integer port) {
		super();
		this.url = url;
		this.port = port;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getCompleteURL()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("http://").append(getUrl()).append(":").append(getPort());
		
		return buffer.toString();
	}
}
