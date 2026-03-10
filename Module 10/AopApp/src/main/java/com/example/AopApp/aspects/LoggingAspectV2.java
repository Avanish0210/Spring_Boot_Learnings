package com.example.AopApp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectV2 {

    @Before("allServiceMethodPointCut()")
    public void beforeServiceMethodCalls(JoinPoint joinPoint){
        log.info("Before advice method call, {}", joinPoint.getSignature());
    }

//    @After("allServiceMethodPointCut()")
    @AfterReturning(value = "allServiceMethodPointCut()" , returning = "returningObj")
    public void afterServiceMethodCalls(JoinPoint joinPoint , Object returnObj){
        log.info("After returning advice method call, {}", joinPoint.getSignature());
        log.info("After returning returned value, {}", returnObj);
    }

    @AfterThrowing("allServiceMethodPointCut()")
    public void afterServiceMethodCallsThrow(JoinPoint joinPoint){
        log.info("After Throwing advice method call, {}", joinPoint.getSignature());
    }

    @Pointcut("execution(* com.example.AopApp.services.impl.*.*(..))")
    public void allServiceMethodPointCut(){

    }
}
