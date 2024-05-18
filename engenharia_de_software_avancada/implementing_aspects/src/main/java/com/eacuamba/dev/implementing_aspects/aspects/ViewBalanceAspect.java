package com.eacuamba.dev.implementing_aspects.aspects;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class ViewBalanceAspect {

    @Pointcut("execution(* com.eacuamba.dev.implementing_aspects.services.ViewBalanceService.process())")
    public void authentication() {}

    @Before("authentication()")
    public void beforeExecuteAuthenticate(){

    }
}
