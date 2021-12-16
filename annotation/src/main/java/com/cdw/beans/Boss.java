package com.cdw.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: cdw
 * @date: 2021/12/16 14:36
 * @description:
 */
@Component
public class Boss {
    private Car car;

//    @Autowired
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss ....");
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }

    public Car getCar() {
        return car;
    }

//    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }
}
