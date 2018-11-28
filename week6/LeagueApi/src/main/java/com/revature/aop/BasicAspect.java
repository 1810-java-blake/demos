package com.revature.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class BasicAspect {

	@Around("allControllerMethods()")
	public Object beforeMethod(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("controller method with signature " + pjp.getSignature() + " called");
		CodeSignature sig = (CodeSignature) pjp.getSignature();
		boolean hasAuthtokenParam = false;
		int authTokenParamIndex = -1;
		String[] paramNames = sig.getParameterNames();
		for(int j = 0; j <paramNames.length; j++) {
			if("authToken".equals(paramNames[j])) {
				hasAuthtokenParam = true;
				authTokenParamIndex = j;
			}
		}
		
		if (hasAuthtokenParam) {
			String token = (String) pjp.getArgs()[authTokenParamIndex];
			System.out.println("token provided: " + token);
			if ("my really secure token that is not a token".equals(token)) {
				return pjp.proceed();
			} else {
				// really should set status code to 403
				return null;
			}
		} else {
			return pjp.proceed();
		}
	}

//	@After("allControllerMethods()")
//	public void afterMethod(JoinPoint jp) {
//		System.out.println("controller method with signature " + jp.getSignature() + " has returned");
//	}
//
//	@AfterReturning("allControllerMethods()")
//	public void afterReturning(JoinPoint jp) {
//		System.out.println("controller method with signature " + jp.getSignature() + " has returned normally");
//	}
//
//	@AfterThrowing("allControllerMethods()")
//	public void afterThrowing(JoinPoint jp) {
//		System.out.println("controller method with signature " + jp.getSignature() + " has thrown exception");
//	}

	// return type package.class.method(params)
	@Pointcut("execution(* com.revature.controllers.*..*(..))")
	public void allControllerMethods() {
	}
}
