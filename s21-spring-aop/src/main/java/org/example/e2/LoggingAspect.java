package org.example.e2;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    public static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(public void org.example.e2.LogicV2.perform())")
    public void logPointcutWithExecution() {
    }

    @Before("logPointcutWithExecution()")
    public void logMethodCallsWithExecutionAdvice() {
        LOGGER.warning("In Aspect from execution");
    }
}
