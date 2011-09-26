package com.ff.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ff.enums.CreationPolicy;
import com.ff.enums.EventType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Event {		
	Class<? extends org.zkoss.zk.ui.event.Event>[] value();
	String alias() default "";
	EventType eventType() default EventType.IMPORTANT;
	CreationPolicy creationPolicy() default CreationPolicy.AUTO;
}
