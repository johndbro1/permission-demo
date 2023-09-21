package net.paclabs.common.service.api;

public class Result<T> {

	private final T data;
	private final boolean success;
	private final String  error;
	
	public Result( T t ) {
		
		this.data = t;
		this.success = true;
		this.error = null;
	}
	
	
	public Result( boolean pSuccess, String pMessage ) {
		this.data = null;
		this.success = pSuccess;
		this.error = pMessage;
	}
	
	public Result(String pMessage) {
		this.data = null;
		this.success = false;
		this.error = pMessage;
	}
	
	
	public Result( T t, boolean pSuccess, String pMessage ) {
		this.data = t;
		this.success = pSuccess;
		this.error = pMessage;
	}
	
	
	public T get() {
		
		if (this.data == null) {
			throw new RuntimeException("Result: Invalid access of empty result");
		}
		return this.data;
	}
	
	public boolean isSuccess() {
		return this.success;
	}
	
	public String getError() {
		return this.error;
	}


	@Override
	public String toString() {
		return "Result [data=" + data + ", success=" + success + ", message=" + error + "]";
	}
	
	
	
}
