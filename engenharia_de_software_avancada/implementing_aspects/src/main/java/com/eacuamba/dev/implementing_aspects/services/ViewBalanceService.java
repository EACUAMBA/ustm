package com.eacuamba.dev.implementing_aspects.services;

import com.eacuamba.dev.implementing_aspects.ConsoleUtilities;
import com.eacuamba.dev.implementing_aspects.SessionConcept;
import com.eacuamba.dev.implementing_aspects.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewBalanceService {
    private final AccountRepository accountRepository;
    private final SessionConcept sessionConcept;

    public void process(){

        System.out.println("");

    }
}
