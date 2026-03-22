package com.example.ChachingApp.services;

import com.example.ChachingApp.entities.Employee ;
import com.example.ChachingApp.entities.SalaryAccount ;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
