package com.sxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxs.common.Constant;
import com.sxs.common.R;
import com.sxs.dto.EmployeeDTO;
import com.sxs.entity.Employee;
import com.sxs.mapper.EmployeeMapper;
import com.sxs.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;

/**
 * @author sxs
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public R<Employee> login(EmployeeDTO employee, HttpSession session) {
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
        String pwd = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        if (!one.getPassword().equals(pwd)) {
            //5.1 失败 返回错误信息
            return R.error("密码错误");
        }
        //5.2  查看用户状态是否禁用
        if (one.getStatus() == 0) {
            return R.error("用户已禁用");
        }
        //5.3  成功 返回实体类
        session.setAttribute("employee", one.getId());
        return R.success(one);
    }

    @Override
    public R logout(HttpSession session) {
        session.removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 分页查询
     *
     * @param page     页号
     * @param pageSize 页大小
     * @param name     姓名
     */
    @Override
    public R page(Integer page, Integer pageSize, String name) {
        IPage<Employee> iPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), Employee::getName, name);
        wrapper.orderByDesc(Employee::getUpdateTime);
        this.baseMapper.selectPage(iPage, wrapper);
        return R.success(iPage);
    }

    /**
     * 新增员工
     * @param employeeDto
     * @return R
     */
    @Override
    public R add(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employee.setPassword(DigestUtils.md5DigestAsHex(Constant.DEFAULT_PHONE.getBytes()));
        this.save(employee);
        return R.success("添加成功");
    }

    @Override
    public R update(EmployeeDTO employeeDTO) {
        Integer status = employeeDTO.getStatus();
        if (status!=null && status!=0 && status != 1){
            return R.error("非法参数");
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        boolean b = this.updateById(employee);
        if (b) {
            return R.success("员工信息修改成功");
        }else {
            return R.error("修改失败，请重试");
        }
    }

    @Override
    public R findById(Long id) {
        Employee employee = this.getById(id);
        if (employee==null){
            return R.error("请重试");
        }
        return R.success(employee);
    }
}
