package com.Module4.Module4.clients;

import com.Module4.Module4.dto.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(long employeeId);

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
}
