package com.sxs.service;

import com.sxs.common.R;
import com.sxs.dto.CategoryDTO;
import com.sxs.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author sxs
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-12-02 13:35:24
*/
public interface CategoryService extends IService<Category> {

    R getPage(Integer page, Integer pageSize);

    R add(CategoryDTO categoryDTO);

    R update(CategoryDTO categoryDTO);

    R delete(Long id);
}
