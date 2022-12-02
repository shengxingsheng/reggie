package com.sxs.mapper;

import com.sxs.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sxs
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2022-12-02 13:35:24
* @Entity com.sxs.entity.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




