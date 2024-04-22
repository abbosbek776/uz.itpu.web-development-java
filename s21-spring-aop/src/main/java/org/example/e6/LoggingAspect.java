package org.example.e6;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(org.example.e6.LogicV6)")
    public void logPointcutWithArgs() {
    }

    @Before("logPointcutWithArgs()")
    public void logMethodCallsWithArgsAdvice(JoinPoint joinPoint) {
        LOGGER.warning("In Aspect from Args");
        LOGGER.warning(joinPoint.getSignature().getName());
    }
}
