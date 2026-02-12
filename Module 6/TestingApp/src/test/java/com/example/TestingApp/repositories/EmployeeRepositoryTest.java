package com.example.TestingApp.repositories;

import com.example.TestingApp.TestContainerConfiguration;
import com.example.TestingApp.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1L)
                .name("Avanish")
                .email("avanish@gmail.com")
                .salary(100L)
                .build();
    }
    @Test
    void TestFindByEmail_WhenEmailIsPresent_thenReturnEmployee() {
        //Arrange given
        employeeRepository.save(employee);
        //Act , when
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());

        //Assert , Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isNotZero();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());

    }

    @Test
    void TestFindByEmail_WhenEmailIsNotFound_thenReturnEmptyEmployeeList() {
        //given

        String email = "notpresent.123@gmail.com";
        //when

        List<Employee> employeeList = employeeRepository.findByEmail(email);
        //Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isZero();
    }
}