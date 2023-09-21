package net.paclabs.common.webapp.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class ListOfEntityResponse<T> {

	final boolean success;
	final String  service;
	String error;
	List<String> encodedEntityList;
	String entityType;
	long   size;

	public ListOfEntityResponse( String service, String error) {
		this.service = service;
		this.success = false;
		this.error = error;
	}
	
	
	public ListOfEntityResponse( String service, List<T> entityList) {
		this.service = service;
		this.success = true;
		
		Gson gson = new Gson();
		
		encodedEntityList = entityList.stream().map( el -> {
			
			return gson.toJson(el);
			
		}).collect(Collectors.toList());
		
		
		this.size = entityList.size();
		
		if (this.size > 0) {
			entityType = entityList.get(0).getClass().getCanonicalName();
		}
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


	public List<String> getEncodedEntityList() {
		return encodedEntityList;
	}


	public String getEntityType() {
		return entityType;
	}


	public long getSize() {
		return size;
	}
	
	public List<T> getEntityList(Class<T> classOfX) {
		
		List<T> response = new ArrayList<>();
		
		if (size == 0) {
			return response;
		}
		
		Gson gson = new Gson();		
		
		encodedEntityList.stream().forEach( json -> {
			 T t = gson.fromJson(json, classOfX);
			 response.add(t);
		});
		
		return response;
	}
	
}
