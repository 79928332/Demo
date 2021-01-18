package com.hxzy.util.autoservice;

public class AutoMain {

    public static void main(String[] args) throws InterruptedException {

        AutoTempalteClass   autoTempalteClass=new AutoTempalteClass("BaseRepo","Integer");
        autoTempalteClass.create();
    }
}
