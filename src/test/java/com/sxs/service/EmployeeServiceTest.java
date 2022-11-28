package com.sxs.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@SpringBootTest
@Component
public class EmployeeServiceTest {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeServiceTest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    public void login() {
//        EmployeeDto employee = EmployeeDto.builder()
//                .username("admin")
//                .password("123456")
//                .build();
//        employee.setUsername("admin");
//        employee.setPassword("123456");
//        R<Employee> login = employeeService.login(employee, new H);
//        System.out.println(login);
    }
    @Test
    @Validated
    public void valid(){

        add(null);
    }

    public void add(Integer a){
        System.out.println("...");
    }
}
