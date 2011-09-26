package com.ff.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ff.enums.UpdateType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {		
	UpdateType updateClient() default UpdateType.ALWAYS;
	UpdateType updateServer() default UpdateType.NEVER;	
}
