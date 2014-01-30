package org.mongo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Documented;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CollectionKey {
	/**
	 * this is used to identify a key value in a collection and can be found on
	 * the field in the model class
	 * 
	 * @return
	 */
	String key() default "";
}
