package com.sxs.controller;

import com.sxs.common.R;
import com.sxs.dto.EmployeeDTO;
import com.sxs.service.EmployeeService;
import com.sxs.validation.AddValidationGroup;
import com.sxs.validation.LoginValidationGroup;
import com.sxs.validation.StatusValidationGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author sxs
 *
 * The type Employee controller.
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * Instantiates a new Employee controller.
     *
     * @param employeeService the employee service
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Login r.
     *
     * @param employeeDto the employee dto
     * @param session     the session
     * @return the r
     */
    @PostMapping("login")
    public R login(@Validated({LoginValidationGroup.class}) @RequestBody EmployeeDTO employeeDto, HttpSession session){
        return  employeeService.login(employeeDto, session);
    }

    /**
     * Logout r.
     *
     * @param session the session
     * @return the r
     */
    @PostMapping("logout")
    public R logout(HttpSession session){
        return employeeService.logout(session);
    }

    /**
     * Page r.
     *
     * @param page     the page
     * @param pageSize the page size
     * @param name     the name
     * @return the r
     */
    @GetMapping("page")
    public R page(@RequestParam("page") Integer page,
                  @RequestParam("pageSize") Integer pageSize,
                  @RequestParam(value = "name",required = false) String name){
        return employeeService.page(page,pageSize,name);
    }

    /**
     * Add r.
     *
     * @param employeeDto the employee dto
     * @return the r
     */
    @PostMapping
    public R add(@Validated({AddValidationGroup.class}) @RequestBody EmployeeDTO employeeDto){
        return employeeService.add(employeeDto);
    }
    @PutMapping
    public R update(@RequestBody @Validated({StatusValidationGroup.class,AddValidationGroup.class}) EmployeeDTO employeeDTO){
        return employeeService.update(employeeDTO);
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable Long id){
        return employeeService.findById(id);
    }
}
