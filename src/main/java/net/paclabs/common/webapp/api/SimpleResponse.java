package net.paclabs.common.webapp.api;

public class SimpleResponse {

	final protected boolean success;
	final protected String  message;
	final protected String  service;
	
	public static IVersion version = null;
	
	
	public SimpleResponse(String service, boolean success, String message) {
		super();
		this.service = service;
		this.success = success;
		this.message = message;
	}


	public boolean isSuccess() {
		return success;
	}


	public String getMessage() {
		return message;
	}


	public IVersion getVersion() {
		return version;
	}
	
	
	
	public String getService() {
		return service;
	}


	public String getSource() {
		if (version != null) {
			return version.getArtifactId();
		} else {
			// if you get this, you need to statically load the version information
			// at web app startup.
			return "Version not Initialized";
		}
	}
	
	
}
