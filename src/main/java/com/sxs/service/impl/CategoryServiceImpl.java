package com.sxs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxs.common.R;
import com.sxs.dto.CategoryDTO;
import com.sxs.entity.Category;
import com.sxs.entity.Dish;
import com.sxs.entity.Setmeal;
import com.sxs.mapper.CategoryMapper;
import com.sxs.service.CategoryService;
import com.sxs.service.DishService;
import com.sxs.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author sxs
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2022-12-02 13:35:24
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    private DishService dishService;
    private SetmealService setmealService;

    @Autowired
    public CategoryServiceImpl(DishService dishService, SetmealService setmealService) {
        this.dishService = dishService;
        this.setmealService = setmealService;
    }

    @Override
    public R getPage(Integer page, Integer pageSize) {
        IPage<Category> iPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Category::getSort);
        this.page(iPage,wrapper);
        return R.success(iPage);
    }

    @Override
    public R add(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .sort(Integer.parseInt(categoryDTO.getSort()))
                .type(Integer.parseInt(categoryDTO.getType()))
                .build();
        boolean b = this.save(category);
        if (!b){
            return R.error("操作失败");
        }
        return R.success("添加成功");
    }

    @Override
    public R update(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .sort(Integer.parseInt(categoryDTO.getSort()))
                .build();
        boolean b = this.updateById(category);
        if (!b) {
            return R.error("操作失败");
        }
        return R.success("修改成功");
    }

    @Override
    public R delete(Long id) {
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getCategoryId,id);
        long count = dishService.count(wrapper);
        if (count>0){
            return R.error("当前分类下关联了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Setmeal::getCategoryId,id);
        long count1 = setmealService.count(wrapper1);
        if (count > 0) {
            return R.error("当前分类下关联了套餐，不能删除");
        }
        boolean b = this.removeById(id);
        if (!b) {
            return R.error("操作失败");
        }
        return R.success("删除成功");
    }
}




