package com.hxzy.controller.exception;

public class AccountNotFoundException extends RuntimeException  {
    public AccountNotFoundException() {
        super("账户不存在");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
