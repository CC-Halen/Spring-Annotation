package com.cdw.beans;

import org.springframework.stereotype.Component;

/**
 * @author: cdw
 * @date: 2021/12/15 19:24
 * @description:
 */
@Component
public class Color {
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Color{" +
                "car=" + car +
                '}';
    }
}


