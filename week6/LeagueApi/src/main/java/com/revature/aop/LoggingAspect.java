package com.revature.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class LoggingAspect {
	
	
	@Around("allMethods()")
	public Object logger(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		System.out.println("Method called with signature " + pjp.getSignature());
		System.out.println("With arguments: " + Arrays.toString(pjp.getArgs()));
		
		try {
			
			obj = pjp.proceed(); // actually calls the method that this is wrapping around
			
			if (obj != null) {
				System.out.println("Method with signature " + pjp.getSignature() + " returned \n\t" + obj.toString());
			} else {
				System.out.println("Method with signature " +pjp.getSignature() + " returned nothing");
			}
		} catch (Throwable e) {
			System.out.println("Method with signature " + pjp.getSignature() + " threw exception with message: " + e.getMessage());
			throw e;
		}
		
		return obj;
	}
	
	@Pointcut("execution(* com.revature.*..*(..)) " + 
			"&& !execution(* com.revature.LeagueApiApplication.*(..)) " +
			"&& !execution(* com.revature.configuration.*..*(..)) ")
	public void allMethods() {
	}

}
