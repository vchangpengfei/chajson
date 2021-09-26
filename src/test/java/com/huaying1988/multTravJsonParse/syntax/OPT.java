package com.huaying1988.multTravJsonParse.syntax;

import java.lang.reflect.Method;

import com.huaying1988.multTravJsonParse.lex.Token;

/**
 * ���������صľ�̬����
 * @author huaying1988.com
 *
 */
public class OPT {
	public static final Method VAL = getMethod("val");
	public static final Method ARRAV = getMethod("arrav");
	public static final Method OBJAK = getMethod("objak");
	public static final Method OBJAV = getMethod("objav");
	public static final Method ARRS = getMethod("arrs");
	public static final Method OBJS = getMethod("objs");

	public static Method getMethod(String methodName){
		Method m =  null;
		try {
			m = Operator.class.getMethod(methodName, new Class[]{Integer.class,Integer.class,Token.class});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return m;
	}
}
