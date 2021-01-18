package com.hxzy.controller.exception;

public class InvalidPasswordException  extends RuntimeException {
    public InvalidPasswordException() {
        super("密码错误");
    }
    public InvalidPasswordException(String message) {
        super(message);
    }
}
