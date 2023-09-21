package net.paclabs.common.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.paclabs.common.service.api.Result;


public class DateFormatHelper {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings({ "serial", "unused" })
	private static final Map<String, Boolean> SHORT_DATE_REGEXPS = new HashMap<String, Boolean>() {{
		put("^\\d{1,2}-\\d{1,2}-\\d{2}$", true);
	}};
	
	@SuppressWarnings("serial")
	private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
	    put("^\\d{8}$", "yyyyMMdd");
	    put("^\\d{1,2}-\\d{1,2}-\\d{2}$", "MM-dd-yy");
	    put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "MM-dd-yyyy");
	    put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
	    put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
	    put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
	    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
	    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
	    put("^\\d{12}$", "yyyyMMddHHmm");
	    put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
	    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
	    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
	    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
	    put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
	    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
	    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
	    put("^\\d{14}$", "yyyyMMddHHmmss");
	    put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
	    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
	    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
	    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
	    put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
	    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
	    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
	}};

	/**
	 * Determine SimpleDateFormat pattern matching with the given date string. Returns null if
	 * format is unknown. You can simply extend DateUtil with more formats if needed.
	 * @param dateString The date string to determine the SimpleDateFormat pattern for.
	 * @return The matching SimpleDateFormat pattern, or null if format is unknown.
	 * @see SimpleDateFormat
	 */
	public String determineDateFormat(String dateString) {
		
//		log.info("#DEBUG: determineDateFormat: checking {}", dateString);
		
	    for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
	    	
//	    	log.info("#DEBUG: determineDateFormat: checking {} against {}", dateString, regexp);
	    	
	        if (dateString.toLowerCase().trim().matches(regexp)) {
	            return DATE_FORMAT_REGEXPS.get(regexp);
	        }
	    }
	    return null; // Unknown format.
	}
	
	
	public SimpleDateFormat dateFormat(String dateString) {
		
		
		
		String format = determineDateFormat(dateString);
		
//		log.info("#DEBUG: dateFormat: attempting to identify: {}, found: {}", dateString, format);		
		
		if (format != null) {
			
			return new SimpleDateFormat(format);
		}
		
		log.warn("dateFormat: unable to find format for {}, using yyyy-MM-dd", dateString);		

		return new SimpleDateFormat("yyyy-MM-dd");
				
	}
	
	
	public Result<Date> parse(String dateString) {
				
//		log.info("#DEBUG: parse: attempting to identify: {}", dateString);
				
		Date d = null;
		try {
			d = dateFormat(dateString).parse(dateString);
		} catch (ParseException e) {
			return new Result<>("Unable to parse date: "+dateString+" : "+e.getMessage());
		}
			
		return new Result<>(d);
	}
}
