package com.cdw.beans;

/**
 * @author: cdw
 * @date: 2021/12/15 20:16
 * @description:
 */
public class Car {
    public Car() {
        System.out.println("car constructor ....");
    }

    public void  init(){
        System.out.println("car init ....");
    }

    public void  destroy(){
        System.out.println("car destroy ...");
    }
}
