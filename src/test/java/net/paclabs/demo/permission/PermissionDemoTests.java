package net.paclabs.demo.permission;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import net.paclabs.demo.permission.api.helper.KVHelper;

@SpringBootTest
class PermissionDemoTests {

	@Test
	void contextLoads() {
	}

	
	@Test
	public void testKVHelper() {
		
		KVHelper kvHelper = new KVHelper();
		
		Entry<String,String> e = kvHelper.convert(null);
		
		assertThat(e, is(nullValue()));
		
		e = kvHelper.convert("");
				
		assertThat(e, is(nullValue()));

		e = kvHelper.convert("blah");
		
		assertThat(e, is(nullValue()));
		
		e = kvHelper.convert("blah=");
		
		assertThat(e, is(nullValue()));
		
		e = kvHelper.convert("blah=blah=blah");
		
		assertThat(e, is(nullValue()));
		
		
		e = kvHelper.convert("key=value");

		assertThat(e.getKey(), is("key"));
		assertThat(e.getValue(), is("value"));
		
	}

}
