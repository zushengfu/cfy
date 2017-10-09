package com.javen.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;
@Component("MyJob")
public class MyJob {

    public void execute(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Quartz的任务调度！！！"+format.format(new Date()));
    }
}
