package com.eacuamba.dev.implementing_aspects.aspects;

import com.eacuamba.dev.implementing_aspects.ConsoleUtilities;
import com.eacuamba.dev.implementing_aspects.SessionConcept;
import com.eacuamba.dev.implementing_aspects.entities.AccountEntity;
import com.eacuamba.dev.implementing_aspects.repository.AccountRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Objects;
import java.util.Optional;

@Aspect
public class AuthenticatorAspect {
    private AccountRepository accountRepository;

    @Pointcut("execution(* com.eacuamba.dev.implementing_aspects.services.ViewBalanceService.process())")
    public void authentication() {
    }

    @Around("authentication()")
    public void beforeExecuteAuthenticate( ProceedingJoinPoint pjp) throws Throwable {
        int loggingAttemps = 1;

        while (loggingAttemps <= 3) {
            System.out.println("Account number:");
            String accountNumber = ConsoleUtilities.getStringFromScanner();

            System.out.println("Account password:");
            String accountPassword = ConsoleUtilities.getStringFromScanner();

            Optional<AccountEntity> accountEntityOptional = this.accountRepository.findAccountByNumber(accountNumber);

            if (accountEntityOptional.isPresent()) {
                AccountEntity accountEntity = accountEntityOptional.get();
                if (Objects.equals(accountEntity.getPassword(), accountPassword)) {
                    SessionConcept.currentAccount = accountEntity;
                        pjp.proceed();
                    return;
                }
            }
            loggingAttemps++;
        }

        System.out.println("Authentication failed. Please try again later.\n");
        pjp.

    }
}
