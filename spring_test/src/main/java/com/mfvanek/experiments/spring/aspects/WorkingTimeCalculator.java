package com.mfvanek.experiments.spring.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class WorkingTimeCalculator {

    private static final Logger logger = LoggerFactory.getLogger(WorkingTimeCalculator.class);

    @Around("execution(public String quack())")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        final long start = System.nanoTime();
        Object result = pjp.proceed();
        final long elapsedTime = System.nanoTime() - start;
        logger.info("[{}] {}() execution time: {} microseconds",
                pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName(), elapsedTime / 1_000);
        return result;
    }
}
