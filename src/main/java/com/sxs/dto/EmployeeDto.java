package com.sxs.dto;

import com.sxs.validation.AddValidationGroup;
import com.sxs.validation.LoginValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author sxs
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
//    @NotNull(message = "id不能为null", groups = {StatusValidationGroup.class})
    private Long id;
//    @Pattern(regexp = "^[0-1]$",message = "状态格式错误",groups = {StatusValidationGroup.class})
    private Integer status;

    @NotBlank(message = "账号不能为空", groups = {LoginValidationGroup.class})
    @Pattern(regexp = "^[a-zA-Z0-9]{6,128}$", message = "账号至少6位且只能是字母或数字",groups = {AddValidationGroup.class})
    private String username;

    @NotBlank(message = "密码不能为空", groups = {LoginValidationGroup.class})
    private String password;


    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,}$",message = "员工姓名只能是中文且至少包含两位", groups = {AddValidationGroup.class})
    private String name;

    @Pattern(regexp = "^(([1-6][1-9]|50)\\d{4}\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3})|(([1-6][1-9]|50)\\d{4}(18|19|20)\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx])$",
            message = "身份证号错误", groups = {AddValidationGroup.class})
    private String idNumber;

    @Pattern(regexp = "^((13[0-9])|(14[5,6,7,9])|(15[^4])|(16[5,6])|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$",
            message = "手机号错误", groups = {AddValidationGroup.class})
    private String phone;

    @Pattern(regexp = "^[0-1]$", message = "性别错误", groups = {AddValidationGroup.class})
    private String sex;
}
