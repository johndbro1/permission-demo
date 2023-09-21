package net.paclabs.permitio.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.permit.sdk.Permit;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.openapi.models.UserCreate;
import io.permit.sdk.openapi.models.UserRead;

import net.paclabs.permitio.demo.AppConfig;

@Service
public class PermitioService {

	
	@Autowired
	AppConfig appConfig;
	
	private Permit permit;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void initConnection() {
		
    	this.permit = new Permit(
        		new PermitConfig.Builder(
        		  "permit_key_qIWRYDNZ17idYqr9sKhlRdYF1DW9Jk3O8QOQtP5ZGSXLcvvUuMyznpbyzNQ9sAsSaFVOgpzu7OZ3gbb3wmThyd"
        		)
                  .withPdpAddress("https://cloudpdp.api.permit.io")
                  .withDebugMode(true)
                  .build()
        	);
		
	}
	
	public Map<String, String> query( Map<String, Object> input ) {
		

    	User user = User.fromString(input.get("username")); // pass the user *key* to init a user object from string
    	
    	String action = "read";
    	if ("POST".equals(input.get("method"))) {
    		action = "update";
    	} else if ("PUT".equals(input.get("method"))) {
    		action = "create";
    	} else if ("DELETE".equals(input.get("method"))) {
    		action = "delete";
    	}
    	
    	String service = (String) input.get("service");
    	
    	Resource resource = new Resource.Builder(service)
            // you can set a specific tenant for the permission check
            // .withTenant("<TENANT KEY>")
    		.build();

        // to run a permission check, use permit.check()
    	boolean permitted = permit.check(user, action, resource);

    	String message = user.toString()+" is NOT permitted to perform "+action+" on "+service;
    	if (permitted) {
    			message = user.toString()+" is PERMITTED to "+action+" to "+service;
    	}
		
		Map<String, String> results = new HashMap<>();
		results.put("approve", Boolean.toString(permitted));
		results.put("message", message);
		
					
		return results;
	}
	
}
