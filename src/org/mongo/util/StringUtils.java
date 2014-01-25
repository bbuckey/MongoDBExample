package org.mongo.util;

public class StringUtils {

	public static String makeFirstLetterUpperCase(String s){
		String string = "";
	if(s != null){
		string = s.substring(0, 1) + s.substring(1);
	}
		return string;
	}
	
}
