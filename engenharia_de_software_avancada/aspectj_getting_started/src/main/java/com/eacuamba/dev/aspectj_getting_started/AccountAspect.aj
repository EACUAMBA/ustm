package com.eacuamba.dev.aspectj_getting_started;

public aspect AccountAspect {

    pointcut callWithDraw(int amount, Account acc): call(boolean Account.withdraw(int)) && args(amount) && target(acc);

    before(int amount, Account acc): AccountAspect.callWithDraw(amount, acc) {
        System.out.println("Before balance " + acc.balance);
    }

    boolean around(int amount, Account acc): callWithDraw(amount, acc) {
        System.out.println("Around balance " + acc.getBalance());
        if (acc.getBalance() < amount) {
            return false;
        }
        return proceed(amount, acc);
    }

    after(int amount, Account balance): callWithDraw(amount, balance) {
        System.out.println("After balance " + balance.getBalance());
    }

}