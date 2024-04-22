package org.example.e7;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(org.example.e7.LogicV7)")
    public void logPointcutWithArgs() {
    }

    @Around("logPointcutWithArgs()")
    public void logMethodCallsWithArgsAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.warning("In Aspect from Args");
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            Object arg = joinPoint.getArgs()[i];
            LOGGER.warning("arg " + i + " value " + arg);
        }
        joinPoint.proceed();
    }
}
