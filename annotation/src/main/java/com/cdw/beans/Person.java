package com.cdw.beans;

import org.springframework.beans.factory.annotation.Value;

import java.security.PrivateKey;

/**
 * @author: cdw
 * @date: 2021/12/15 16:12
 * @description:
 */
public class Person {
    /**
     * 用@Value进行赋值：
     * 1.基本数值
     * 2.可以写SpEL：#{ }
     * 3.可以用 ${ }，取出配置文件的值
     */

    @Value("张三")
    private String name;
    @Value("#{99-56}")
    private Integer age;
    @Value("${person.nickName}")
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
