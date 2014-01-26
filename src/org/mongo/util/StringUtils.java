package org.mongo.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class StringUtils {

	public static String makeFirstLetterUpperCase(String s) {
		String string = "";
		if (s != null) {
			string = s.substring(0, 1) + s.substring(1);
		}
		return string;
	}

	/**
	 * used for annotation Processing to return a value Annotation(key=value)
	 * 
	 * @param value
	 *            this is the key used to find a value in the string
	 * @param annotaionString
	 *            this is the .toString() method used for the annotation
	 * @return
	 */
	public static String getValueFromAnntotationToString(String value,
			String annotaionString) {
		String string = "";
		string = annotaionString.substring(annotaionString.indexOf("(")+1,
				annotaionString.indexOf(")"));
		String[] strings = string.split(",");
		if (strings != null && strings.length > 0) {
			for (String s : strings) {
				if (s.contains(value)) {
					string = s.split("=")[1];
				}
			}
		}
		return string;
	}

	public static String getAnnotationValueForFieldofClass(Class clazz,
			String field, String key) {
		String s = "";
		Field[] fields = clazz.getDeclaredFields();

		for (Field f : fields) {
			String theField = f.toString();
			if (field.equalsIgnoreCase(theField.substring(theField.lastIndexOf(".")+1))) {
				Annotation[] annotations = f.getDeclaredAnnotations();
				for (Annotation a : annotations) {
					s = getValueFromAnntotationToString(key, a.toString());
					if(s != null && !s.isEmpty()){
						break;
					}
				}
			}
		}
		return s;
	}

	public static String getAnnotationValueForClass(Class clazz, String key) {
		String s = "";
				Annotation[] annotations = clazz.getAnnotations();
				for (Annotation a : annotations) {
					s = getValueFromAnntotationToString(key, a.toString());
					if(s != null && !s.isEmpty()){
						break;
					}
				}
		return s;
	}
	
}
