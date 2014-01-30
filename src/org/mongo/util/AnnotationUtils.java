package org.mongo.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Map;

public class AnnotationUtils {

	/**
	 * used for annotation Processing to return a value Annotation(key=value)
	 * 
	 * @param value
	 *            this is the key used to find a value in the string
	 * @param annotaionString
	 *            this is the .toString() method used for the annotation
	 * @return
	 */
	private static String getValueFromAnntotationToString(String value,
			String annotaionString) {
		String string = "";
		string = annotaionString.substring(annotaionString.indexOf("(") + 1,
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

	private static String getAnnotationValueForFieldofClass(Class clazz,
			String field, String key) {
		String s = "";
		Field[] fields = clazz.getDeclaredFields();

		for (Field f : fields) {
			String theField = f.toString();
			if (field.equalsIgnoreCase(theField.substring(theField
					.lastIndexOf(".") + 1))) {
				Annotation[] annotations = f.getDeclaredAnnotations();
				for (Annotation a : annotations) {
					s = getValueFromAnntotationToString(key, a.toString());
					if (s != null && !s.isEmpty()) {
						break;
					}
				}
			}
		}
		return s;
	}

	private static String getAnnotationValueForClass(Class clazz, String key) {
		String s = "";
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation a : annotations) {
			s = getValueFromAnntotationToString(key, a.toString());
			if (s != null && !s.isEmpty()) {
				break;
			}
		}
		return s;
	}

	/**
	 * returns the annotation of clazz if present otherwise returns null
	 * 
	 * @param clazz
	 * @param classAnnotation
	 * @return
	 */
	public static Annotation getAnnotationValueForClass(Class clazz,
			Class classAnnotation) {
		Annotation a = clazz.getAnnotation(classAnnotation);
		return a;
	}

	/**
	 * Takes the Class finds the specified field and returns the annotation
	 * other wise returns null
	 * 
	 * @param clazz
	 * @param field
	 * @param annotationClass
	 * @return
	 */
	public static Annotation getAnnotationForFieldofClass(Class clazz,
			String field, Class annotationClass) {
		String s = "";
		Field[] fields = clazz.getDeclaredFields();
		Annotation annotation;
		for (Field f : fields) {
			String theField = f.toString();
			if (field.equalsIgnoreCase(theField.substring(theField
					.lastIndexOf(".") + 1))) {
				annotation = f.getAnnotation(annotationClass);
				if (annotation != null) {
					return annotation;
				}
			}
		}
		return null;
	}

	public static List getListOfClassLevelAnnotationFromClass(
			Class annotationClass) {
		List l = new ArrayList();
		Annotation[] classes = annotationClass.getDeclaredAnnotations();
		if (classes.length > 0) {
			Collections.addAll(l, classes);
		}
		return l;
	}

	public static Map<Field, Annotation[]> getMapOfFeildLevelAnnotationFromClass(
			Class annotationClass) {
		Map l = new HashMap();
		Field[] fields = annotationClass.getDeclaredFields();
		if (fields.length > 0) {
			for (Field f : fields) {
				Annotation[] a = f.getDeclaredAnnotations();
				if (a.length > 0) {
					l.put(f, a);
				}
			}
		}
		return l;
	}

	public static Map<Field, Annotation[]> getMapOfMethodLevelAnnotationFromClass(
			Class annotationClass) {
		Map m = new HashMap();
		Method[] fields = annotationClass.getDeclaredMethods();
		if (fields.length > 0) {
			for (Method f : fields) {
				Annotation[] a = f.getDeclaredAnnotations();
				if (a.length > 0) {
					m.put(f, a);
				}
			}
		}
		return m;
	}

}
