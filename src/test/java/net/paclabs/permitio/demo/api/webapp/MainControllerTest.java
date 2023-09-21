package net.paclabs.permitio.demo.api.webapp;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import net.paclabs.common.helper.GsonHelper;
import net.paclabs.permitio.demo.AppConfig;
import net.paclabs.permitio.demo.api.request.ActionRequest;
import net.paclabs.permitio.demo.service.PermitioService;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(AppConfig.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class MainControllerTest {

	
	@Autowired
	MockMvc mockMvc;
	
	
	@Autowired
	AppConfig appConfig;
	
	@MockBean
	PermitioService permitioService;
	
	
	@BeforeEach
	void setup() {
		
		
		// do nothing yet.
	}
	
	
	@Test
	public void testActionGET_ReturnsString() throws Exception {
		
		Map<String,String> mockResult = new HashMap<>();
		mockResult.put("allow", "false");
		mockResult.put("message", "this is a bamboozle");
		
		when(permitioService.query(any())).thenReturn(mockResult);
	
		
		MvcResult result = mockMvc.perform(
				get("/api/v1/main/action?username=john&service=devops&role=r_devops&param1=key=value")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andReturn();
		
		
		String response = result.getResponse().getContentAsString();
		
		
		assertThat(response, containsString("bamboozle"));
		
	}
	
	@Test
	public void testAction_ReturnsString() throws Exception {
		
		ActionRequest request = new ActionRequest();
		request.setUsername("abc");
		request.setRole("def");
		request.setService("ghi");
		request.setParam1("jkl=mno");
		
		
		Map<String,String> mockResult = new HashMap<>();
		mockResult.put("allow", "false");
		mockResult.put("message", "this is a mock");
		
		when(permitioService.query(any())).thenReturn(mockResult);
		
		
		MvcResult result = mockMvc.perform(
				post("/api/v1/main/action")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(GsonHelper.gson().toJson(request)))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andReturn();
		
		
		String response = result.getResponse().getContentAsString();
		
		
		assertThat(response, containsString("mock"));
		
		
		
	}
	
}
