package net.paclabs.common.webapp.api;


import com.google.gson.Gson;

public class EntityResponse<T> {

	
	final boolean success;
	final String  service;
	String error;
	String encodedEntity;
	String entityType;
	
	
	
	public EntityResponse( String service, String error) {
		this.service = service;
		this.success = false;
		this.error = error;
	}
	
	public EntityResponse( String service, T entity) {
		this.service = service;
		this.success = true;
		this.encodedEntity = new Gson().toJson(entity);
		this.entityType = entity.getClass().getCanonicalName();
	}
	
	public EntityResponse(String service, T entity, boolean success) {
		
		this.service = service;
		this.success = success;
		this.encodedEntity = new Gson().toJson(entity);
		this.entityType = entity.getClass().getCanonicalName();
		
	}

	public boolean isSuccess() {
		return success;
	}

	public String getService() {
		return service;
	}

	public String getError() {
		return error;
	}


	public String getEncodedEntity() {
		return encodedEntity;
	}


	public String getEntityType() {
		return entityType;
	}
	
	
	
	/**
	 * Tried two different ways to get this through the existing internals, but my java foo is
	 * not strong enough, so you have to pass it in as a parameter, even though we already
	 * know what it is because of the declaration.
	 * 
	 * @param classOfX
	 * @return
	 */
	public <X> X getEntity( Class<X> classOfX ) {
				
		return new Gson().fromJson(this.getEncodedEntity(), classOfX);
		
	}
}
