package com.appdirect.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ViewMapper<T> {

	public ObjectMapper MAPPER = new ObjectMapper();

	public T map(Object obj, Class<T> clazz) {
		MAPPER = MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return MAPPER.convertValue(obj, clazz);
	}
	public List<T> maplist(List<? extends Object> objs, Class<T> clazz) {
		MAPPER = MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<T> objtoBeReturned=new ArrayList<T>();
		for (Object obj:objs){
			objtoBeReturned.add(MAPPER.convertValue(obj, clazz));
		}
		return objtoBeReturned;
	}

}
