package com.javen.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javen.service.FileRecordsSendService;

@Component
public class OtherJob{

    @Autowired FileRecordsSendService fileRecordsSendService;
    public void execute(){
    	int start=fileRecordsSendService.cxYjJnNewCount();
        System.out.println("这是目前数量："+start);
    }
}