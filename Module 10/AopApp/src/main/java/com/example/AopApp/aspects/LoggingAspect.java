package com.example.AopApp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    //@Before("execution(* orderPackage(..))")
    //@Before("execution(* com.example.AopApp.services.impl.ShipmentServiceImpl.orderPackage(..))")
    @Before("execution(* com.example.AopApp.services.impl.*.*(..))")
    public void beforeOrderPackage(JoinPoint joinPoint) {
        log.info("Before called from LoggingAspect kind, {}" , joinPoint.getKind());
        log.info("Before called from LoggingAspect signature, {}" , joinPoint.getSignature());
    }

    @Before("within(com.example.AopApp..*)")
    public void beforeServiceImplCall() {
        log.info("service Impl calls");
    }

    @Before("myloggingAndAppMethodsPointCut()")
    public void beforeTransactionalAnnotationcalls() {
        log.info("transactional annotation calls");
    }

    @Pointcut("@annotation(com.example.AopApp.aspects.MyLogging) && within(com.example.AopApp..*)")
    public void myloggingAndAppMethodsPointCut() {

    }

}
