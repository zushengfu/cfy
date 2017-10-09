package com.javen.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class TimerCore {

    public static void main(String[] args) {
        System.out.println("timer start ...");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-beans.xml");

    }
}