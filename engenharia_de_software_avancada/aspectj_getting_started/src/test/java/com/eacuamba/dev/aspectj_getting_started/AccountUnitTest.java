package com.eacuamba.dev.aspectj_getting_started;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountUnitTest {
    private AccountManagerApplication accountManager;

    @Before
    public void before() {
        accountManager = new AccountManagerApplication();
    }

    @Test
    public void testRunInAccountManager() {
        this.accountManager.run();
    }

}