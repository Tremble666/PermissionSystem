package com.chenjin.util;

import java.lang.reflect.Method;

public class PermissionUtil {
	public static String buildExpression(Method method){
		String className = method.getDeclaringClass().getName();
		String methodName = method.getName();
		return className+":"+methodName;
	}
}
