package net.paclabs.demo.permission.api.webapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import net.paclabs.demo.permission.api.helper.KVHelper;
import net.paclabs.demo.permission.api.request.ActionRequest;
import net.paclabs.demo.permission.service.PermitioService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/main")
public class MainController {
	

	@Autowired
	PermitioService policyService;
	

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final KVHelper kvHelper = new KVHelper();
	private final Gson gson = new Gson();
	
	private static final String ROOT = "/api/v1/main";
	
	
    @RequestMapping("/")
    public String index() {
        	return	"API methods: "
        				+ "GET: "
                		+ "/healthz, "
                		+ "/livez, "
                		+ " "
                		+ "POST: "
                		+ "/action, "
                		;
    }
    
    
	@GetMapping("/healthz")
	public String healthz( ) {

		log.debug("/healthz called");
		
		return "OK";
	}
	
	@GetMapping("/livez")
	public String livez( ) {

		log.debug("/livez called");
		
		return "200";
	}
	
	
	@GetMapping("/action")
	@ResponseBody
	public String getAction( 
			@RequestParam String username, 
			@RequestParam String service, 
			@RequestParam(required=false) String role, 
			@RequestParam(required=false) String param1,
			@RequestParam(required=false) String param2,
			@RequestParam(required=false) String param3,
			@RequestParam(required=false) String param4,
			@RequestParam(required=false) String param5) {
		
		final String apiService = ROOT+"/action (GET)";
		
		
        Entry<String,String> e1 = kvHelper.convert(param1);
        Entry<String,String> e2 = kvHelper.convert(param2);
        Entry<String,String> e3 = kvHelper.convert(param3);
        Entry<String,String> e4 = kvHelper.convert(param4);
        Entry<String,String> e5 = kvHelper.convert(param5);
        
        
        Map<String,Object> input = new HashMap<>();
        
        input.put("username", username);
        input.put("service", service);
        input.put("role", "n/a");  // this is not used for permit.io
        
        add(input, e1);
        add(input, e2);
        add(input, e3);
        add(input, e4);
        add(input, e5);

        return checkPermissions(apiService, input);
        
		
	}
	
	@PostMapping("/action")
	@ResponseBody
	public String action( @RequestBody ActionRequest request ) {
		
		final String service = ROOT+"/action (POST)";

        log.info("#DEBUG: {} - form is: {}", service, request);        
        
        Entry<String,String> e1 = kvHelper.convert(request.getParam1());
        Entry<String,String> e2 = kvHelper.convert(request.getParam2());
        Entry<String,String> e3 = kvHelper.convert(request.getParam3());
        Entry<String,String> e4 = kvHelper.convert(request.getParam4());
        Entry<String,String> e5 = kvHelper.convert(request.getParam5());
        
        
        Map<String,Object> input = new HashMap<>();
        
        input.put("username", request.getUsername());
        input.put("service", request.getService());
        // input.put("role", request.getRole());    // this is not used in permit.io        
        input.put("role", "n/a");    // this is not used in permit.io
        
        add(input, e1);
        add(input, e2);
        add(input, e3);
        add(input, e4);
        add(input, e5);

        return checkPermissions(service, input);
        
	}
	

	private String checkPermissions(String service, Map<String,Object> input) {
		
        log.info("{} : PaC Query input: {}", service, input);
        
        Map<String,String> response = policyService.query(input);
        
        log.info("{} : PaC Response: {}", service, response);
        
        return gson.toJson(response);
		
	}
	
	
	
	private void add(Map<String,Object> map, Entry<String,String> entry) {
		if (entry != null) {
			map.put(entry.getKey(), entry.getValue());
		}
	}
	
}
