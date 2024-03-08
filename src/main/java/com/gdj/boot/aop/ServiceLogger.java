package com.gdj.boot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ServiceLogger {

	//공통로직 Advice
	@Around("execution( * com.gdj.boot.**.*Service.*(..))")
	public Object log(ProceedingJoinPoint joinPoint)throws Throwable {
		log.info("===========Service 실행===========");
		log.info("=========매개변수 : {} ===========", joinPoint.getArgs());
		Object obj = joinPoint.proceed();
		log.info("=========리턴 값 : {}===========",obj);
		return obj;
		
	}
	
}
