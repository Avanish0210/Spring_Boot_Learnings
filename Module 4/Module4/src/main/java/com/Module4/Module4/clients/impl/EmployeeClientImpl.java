package com.Module4.Module4.clients.impl;


import com.Module4.Module4.advice.ApiResponse;
import com.Module4.Module4.clients.EmployeeClient;
import com.Module4.Module4.dto.EmployeeDto;
import com.Module4.Module4.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restCLient;
    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.trace("trying to get all employees in getAllEmployees");
        try {
            log.info("trying to get the employee in getEmployeeById ");
            ApiResponse<List<EmployeeDto>> employeeDtoList = restCLient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.info("succesfully retrived the employee in getAllEmployee" );
            log.trace("Retrive employees List in getAllEmployee : {}" , employeeDtoList.getData());
            return employeeDtoList.getData();

        }catch (Exception e){
            log.error("Exception occured in getALlEMployees " , e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
        log.trace("trying to get all employees in getEmployeeById with id : {}" , employeeId);
        try{
            ApiResponse<EmployeeDto> employeeRespose = restCLient.get()
                    .uri("employees/{employeeId}" , employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError , (req , res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Employee not found");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeRespose.getData();
        } catch (Exception e){
            log.error("Exception occured in getEmployeeById with id : {}" , employeeId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        log.trace("trying to create an employee with information : {}" , employeeDto);
        try{
            ResponseEntity<ApiResponse<EmployeeDto>> employeeDtoApiREsponse = restCLient.post()
                    .uri("employees")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError , (req , res) -> {
                        log.debug("4xxClient error occurred during createNewEmployee");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Employee not found");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.trace("Succefully create a new employee : {}" , employeeDtoApiREsponse.getBody());
            return employeeDtoApiREsponse.getBody().getData();
        }catch (Exception e){
            log.error("Exception occured in createNewEmployee : {}" , e);
            throw new RuntimeException(e);
        }
    }
}
