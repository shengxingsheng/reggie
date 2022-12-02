package com.sxs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxs.common.R;
import com.sxs.dto.EmployeeDTO;
import com.sxs.entity.Employee;

import javax.servlet.http.HttpSession;

/**
 * The interface Employee service.
 * @author sxs
 */
public interface EmployeeService extends IService<Employee> {
    /**
     * Login r.
     *
     * @param employeeDto the employee dto
     * @param session     the session
     * @return the r
     */
    R login(EmployeeDTO employeeDto, HttpSession session);

    /**
     * Logout r.
     *
     * @param session the session
     * @return the r
     */
    R logout(HttpSession session);

    /**
     * Page r.
     *
     * @param page     the page
     * @param pageSize the page size
     * @param name     the name
     * @return the r
     */
    R page(Integer page, Integer pageSize, String name);

    /**
     * Add r.
     *
     * @param employeeDto the employee dto
     * @return the r
     */
    R add(EmployeeDTO employeeDto);

    R update(EmployeeDTO employeeDTO);

    R findById(Long id);
}
