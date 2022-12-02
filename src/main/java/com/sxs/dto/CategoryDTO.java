package com.sxs.dto;

import com.sxs.validation.AddValidationGroup;
import com.sxs.validation.UpdateValidationGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author sxs
 */
@Data
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "id不能为空",groups = UpdateValidationGroup.class)
    private Long id;
    @NotBlank(message = "名称不能为空",groups = {AddValidationGroup.class,UpdateValidationGroup.class})
    private String name;
    @Pattern(regexp = "^[0-9]{1,9}$",message = "排序只能是非负整数且不能超过9位",groups = {AddValidationGroup.class,UpdateValidationGroup.class})
    private String sort;
    @Pattern(regexp = "^[1-2]$",message = "非法参数",groups = {AddValidationGroup.class})
    private String type;
}
