package org.example.e1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomWrapperAspect {

    @Pointcut("@annotation(CustomWrapper) && args(userDto)")
    public void customWrapper(UserDto userDto) {
    }

    @Around("customWrapper(userDto)")
    public Object aroundAdvice(ProceedingJoinPoint pjp, UserDto userDto) throws Throwable {
        System.out.println("4 - CustomWrapperAspect#aroundAdvice() - BEGIN");

        System.out.println("5 - Caught method parameter = " + userDto);

        userDto.setLogin(userDto.getLogin() + "_twice");
        userDto.setPassword(userDto.getPassword() + "_twice");

        System.out.println("6 - CustomWrapperAspect#aroundAdvice() - BEFORE PROCEED");
        Object returnValue = pjp.proceed();
        System.out.println("9 - CustomWrapperAspect#aroundAdvice() - AFTER PROCEED");

        System.out.println("10 - returnValue is = " + returnValue);
        UserDto replaceReturnValue = new UserDto();
        replaceReturnValue.setLogin("replaced_login");
        replaceReturnValue.setPassword("replaced_password");

        System.out.println("9 - CustomWrapperAspect#aroundAdvice() - END");
        return replaceReturnValue;
    }

    @Around("customWrapper(UserDto)")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("1 - CustomWrapperAspect#aroundAdvice() - BEGIN");
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            System.out.println("    arg " + i + " value " + arg);
            if (arg instanceof UserDto) {
                UserDto userDto = (UserDto) arg;
                userDto.setLogin("PII_login_aspect_mutate");
                userDto.setPassword("PII_password_aspect_mutate");
            }
        }
        System.out.println("2 - Caught method parameter = " + args);

        System.out.println("3 - CustomWrapperAspect#aroundAdvice() - BEFORE PROCEED");
        Object returnValue = pjp.proceed(args);
        System.out.println("10 - CustomWrapperAspect#aroundAdvice() - AFTER PROCEED");

        System.out.println("11 - returnValue is = " + returnValue);
        UserDto replaceReturnValue = new UserDto();
        replaceReturnValue.setLogin("replaced_login_twice");
        replaceReturnValue.setPassword("replaced_password_twice");

        System.out.println("12 - CustomWrapperAspect#aroundAdvice() - END");
        return replaceReturnValue;
    }
}
