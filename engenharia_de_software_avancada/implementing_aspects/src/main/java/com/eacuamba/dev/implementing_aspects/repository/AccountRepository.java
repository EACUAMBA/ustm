package com.eacuamba.dev.implementing_aspects.repository;

import com.eacuamba.dev.implementing_aspects.entities.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {
    private static final List<AccountEntity> ACCOUNT_ENTITY_LIST = new ArrayList<>();

    public static List<AccountEntity> findAll() {
            return ACCOUNT_ENTITY_LIST;
    }

    public Optional<AccountEntity> findAccountByNumber(String number) {

        return ACCOUNT_ENTITY_LIST
                .stream()
                .filter(accountEntity -> accountEntity.getNumber().equals(number))
                .findFirst();
    }
}
