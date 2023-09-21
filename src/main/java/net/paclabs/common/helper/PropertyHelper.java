package net.paclabs.common.helper;

import java.util.regex.Pattern;

public class PropertyHelper {

	private final Pattern upperLower = Pattern.compile("^[a-zA-Z0-9_\\-/]+$");
	private final Pattern alphaOnly = Pattern.compile("^[a-z\\-/]+$");

	public boolean isPropertyKey(String key) {
		return upperLower.matcher(key).matches();
	}
	
	public boolean isEntityKey(String key) {
		return alphaOnly.matcher(key).matches();
	}

	
}
