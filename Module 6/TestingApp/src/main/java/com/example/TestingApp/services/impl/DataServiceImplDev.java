package com.example.TestingApp.services.impl;

import com.example.TestingApp.services.DataService ;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImplDev implements DataService {


    @Override
    public String getdata() {
        return "Dev Data";
    }
}
