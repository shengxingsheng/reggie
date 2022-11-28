package com.sxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxs.common.R;
import com.sxs.dto.EmployeeDto;
import com.sxs.entity.Employee;
import com.sxs.mapper.EmployeeMapper;
import com.sxs.service.EmployeeService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public R<Employee> login(EmployeeDto employee, HttpSession session) {
        //1.拿到用户名和密码
        //2.查数据库校验用户名
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(employee.getUsername()), Employee::getUsername, employee.getUsername());
        Employee one = this.getOne(wrapper);
        //3.失败 返回错误 `
        if (one == null) {
            return R.error("用户名错误!");
        }
        //4.成功则检查密码;
        String pwd = DigestUtils.md5Hex(employee.getPassword());
        if (!one.getPassword().equals(pwd)) {
            //5.1 失败 返回错误信息
            return R.error("密码错误");
        }
        //5.2  查看用户状态是否禁用
        if (one.getStatus() == 0) {
            return R.error("用户已禁用");
        }
        //5.3  成功 返回实体类
        session.setAttribute("employee",one.getId());
        return R.success(one);
    }

    @Override
    public R logout(HttpSession session) {
        session.removeAttribute("employee");
        return R.success("退出成功");
    }
}
