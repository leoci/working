package com.leo.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface SampleAnnotation {
	
	EnumSample sheet();

}
