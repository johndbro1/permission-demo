package net.paclabs.permitio.demo.api.helper;

import java.util.AbstractMap;
import java.util.Map.Entry;

public class KVHelper {

	public Entry<String,String> convert(String param) {
		
		if (param == null) {
			return null;
		}
		
		if (param.indexOf("=") == -1) {
			return  null;
		}
		
		
		String[] elements = param.split("=");
		
		
		if (elements.length != 2) {
			return null;
		}
		
		return new AbstractMap.SimpleEntry<>(elements[0], elements[1]);
		
	}
	
	
}
