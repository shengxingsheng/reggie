package com.sxs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxs.entity.Dish;
import com.sxs.service.DishService;
import com.sxs.mapper.DishMapper;
import org.springframework.stereotype.Service;

/**
* @author sxs
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-12-02 15:48:48
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

}




