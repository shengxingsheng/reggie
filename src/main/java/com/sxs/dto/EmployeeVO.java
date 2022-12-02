package com.sxs.dto;


import com.sxs.entity.Employee;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class EmployeeVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long total;
    private List<Employee> records;
}
