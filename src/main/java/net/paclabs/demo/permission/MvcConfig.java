package net.paclabs.demo.permission;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		
		// the  favorPathExtension fix is no longer necessary, leaving this in for 
		// potential future help
		
//		// if you don't include this, Spring will automatically interpret URLs that end in <something@<something>.<blah> as email addresses and it 
//		// confuses the browser and causes 406 errors
//		//
//	    configurer.favorPathExtension(false);
		
    }	

    
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    // json
	    converters.add(new MappingJackson2HttpMessageConverter());
		
	}

	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
	
	
}
