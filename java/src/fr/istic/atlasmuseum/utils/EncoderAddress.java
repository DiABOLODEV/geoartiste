package fr.istic.atlasmuseum.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncoderAddress {
	
	public static String encodeStringForHTTPGET(String address){
		String ad = address;
		try {
			ad = URLEncoder.encode(address, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "string encoding error";
		}
		return ad;
	}
}
