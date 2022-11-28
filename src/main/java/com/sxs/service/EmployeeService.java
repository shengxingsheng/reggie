package com.sxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxs.common.R;
import com.sxs.dto.EmployeeDto;
import com.sxs.entity.Employee;

import javax.servlet.http.HttpSession;

public interface EmployeeService extends IService<Employee> {
    R login(EmployeeDto employeeDto, HttpSession session);

    R logout(HttpSession session);
}
