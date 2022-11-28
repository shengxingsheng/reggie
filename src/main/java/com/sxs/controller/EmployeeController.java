package com.sxs.controller;

import com.sxs.common.R;
import com.sxs.dto.EmployeeDto;
import com.sxs.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("login")
    public R login(@Validated @RequestBody EmployeeDto employeeDto, HttpSession session){
        return  employeeService.login(employeeDto, session);
    }
    @PostMapping("logout")
    public R logout(HttpSession session){
        return employeeService.logout(session);
    }
}
