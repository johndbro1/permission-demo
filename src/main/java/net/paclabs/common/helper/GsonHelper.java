package net.paclabs.common.helper;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import net.paclabs.common.webapp.api.EntityResponse;

public class GsonHelper {

	private static Gson _gson = null;
	
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS").withLocale(Locale.ENGLISH);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withLocale(Locale.ENGLISH);
	
	public <T> T getEntity( EntityResponse<T> er, Class<T> classOfT) {				
		
		T t = gson().fromJson(er.getEncodedEntity(), classOfT);
		
		return t;
	}
	
	public static Gson gson() {
		
		if (_gson == null) {
			_gson = new GsonBuilder()
					
					// convert from string to localdatetime and vice versa
					.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
											LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), dateTimeFormatter))
					.registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (datetime, type, jsonSerializationContext) ->
											new JsonPrimitive( dateTimeFormatter.format(datetime)))
					
					// convert from string to localdate and vice versa
					.registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) ->
											LocalDate.parse(json.getAsJsonPrimitive().getAsString(), dateFormatter))
					.registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (date, type, jsonSerializationContext) ->
											new JsonPrimitive(dateFormatter.format(date)))
								
								
					.setPrettyPrinting()
					.create();
		}
		
		return _gson;
		
	}	
	
}
