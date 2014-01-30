package org.mongo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Documented;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CollectionName {
	/**
	 * This is used in class declaration to identify a collection name this is
	 * used with the collection annotation
	 * 
	 * @return
	 */
	String collectionName() default "";
}
