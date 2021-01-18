package com.hxzy.controller.exception;

public class AccountLockedException  extends  RuntimeException{
    public AccountLockedException() {
        super("账户被锁定!");
    }

    public AccountLockedException(String message) {
        super(message);
    }
}
