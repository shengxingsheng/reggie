package com.sxs.mapper;

import com.sxs.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sxs
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2022-12-02 15:48:48
* @Entity com.sxs.entity.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}




