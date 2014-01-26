package org.mongo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CollectionName {
	String collectionName() default "";
}