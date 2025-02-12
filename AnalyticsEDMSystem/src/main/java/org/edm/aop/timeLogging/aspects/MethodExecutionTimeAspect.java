package org.edm.aop.timeLogging.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodExecutionTimeAspect {
    private static final Logger logger  = LoggerFactory.getLogger(MethodExecutionTimeAspect.class);
    @Around("@annotation(org.edm.aop.timeLogging.annotations.LogMethodExecutionTime)")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        logger.info("Method "+ joinPoint.getSignature().getName() +" is executing " + executionTime +" ms." );
        return proceed;
    }

}
