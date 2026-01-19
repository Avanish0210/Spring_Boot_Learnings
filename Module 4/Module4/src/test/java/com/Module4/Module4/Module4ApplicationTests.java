package com.Module4.Module4;

import com.Module4.Module4.clients.EmployeeClient;
import com.Module4.Module4.dto.EmployeeDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Module4ApplicationTests {

    @Autowired
    private EmployeeClient employeeClient;
    @Test
    @Order(3)
    void getAllEmployees(){
        List<EmployeeDto> employeeDtoList = employeeClient.getAllEmployees();
        System.out.println(employeeDtoList);
    }

    @Test
    @Order(2)
    void getEmployeeByIdTest(){
        EmployeeDto employeeDto = employeeClient.getEmployeeById(1L);
        System.out.println(employeeDto);
    }

    @Test
    @Order(1)
    void createNewEmployeeTest(){
        EmployeeDto employeeDto = new EmployeeDto(null , "Avanish" , "avanish@gmail.com" , 20,
                "USER" ,5000.00 , LocalDate.of(2020,12,1) , true);
        EmployeeDto savedEmployeeDto = employeeClient.createNewEmployee(employeeDto);
        System.out.println(savedEmployeeDto);
    }

}
