package com.sxs.controller;

import com.sxs.common.R;
import com.sxs.dto.CategoryDTO;
import com.sxs.service.CategoryService;
import com.sxs.validation.AddValidationGroup;
import com.sxs.validation.UpdateValidationGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author sxs
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/page")
    public R page(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return categoryService.getPage(page, pageSize);
    }

    @PostMapping
    public R add(@RequestBody @Validated(AddValidationGroup.class) CategoryDTO categoryDTO) {
        return categoryService.add(categoryDTO);
    }
    @PutMapping
    public R update(@RequestBody @Validated(UpdateValidationGroup.class) CategoryDTO categoryDTO) {
        return categoryService.update(categoryDTO);
    }
    @DeleteMapping
    public R delete(@RequestParam Long id) {
        return categoryService.delete(id);
    }

}
