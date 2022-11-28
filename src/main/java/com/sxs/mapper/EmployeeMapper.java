package com.sxs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxs.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
