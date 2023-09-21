package net.paclabs.demo.permission;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.annotation.PostConstruct;


@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({
	"net.paclabs.demo.permission",
	"net.paclabs.common"
	})
@EnableScheduling
public class AppConfig {
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	
//	/** 
//	 * The IP Address or hostname at which OPA is listening
//	 *  
//	 */
//	@Value("${OPA_SERVER_ADDRESS}")
//	public String OPA_SERVER_ADDRESS;
//
//	/**
//	 * the port upon which OPA is listening
//	 */
//	@Value("${OPA_PORT}")
//	public int OPA_PORT;
//
//
//	/**
//	 * The Rego package path
//	 */
//	@Value("${OPA_RULE_PATH}")
//	public String OPA_RULE_PATH;
//	
//	/**
//	 * http or https
//	 */
//	@Value("${OPA_WEB_PROTOCOL}")
//	public String OPA_WEB_PROTOCOL;

	
	
	@PostConstruct
	public void init() {
						
		log.debug("AppConfig.init()");
		
		// these values are loaded from the specified properties file
		
		
		//		log.info("Using OPA at: {}://{}:{}/{}", OPA_WEB_PROTOCOL, OPA_SERVER_ADDRESS, OPA_PORT, OPA_RULE_PATH);
		
		
	}
	
	
}
