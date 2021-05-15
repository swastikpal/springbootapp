package com.gcdc.sample.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.gcdc.openapi.model.Pet;

public class ApplicationUtils {

	public static String xmlConverter(Pet p) {
		XmlMapper mapper = new XmlMapper();
		String xml = "";
		try {
			xml = mapper.writeValueAsString(p);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return xml;
	}

	public static String jsonConverter(Pet p) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(p);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
