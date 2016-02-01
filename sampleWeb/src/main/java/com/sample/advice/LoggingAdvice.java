package com.sample.advice;

import java.lang.reflect.Method;


public class LoggingAdvice{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("Here in advice");
		
	}

}
