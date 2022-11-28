package com.sxs.dto;

import com.sxs.validation.EditValidationGroup;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
public class EmployeeDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotEmpty(message = "id could not be empty", groups = {EditValidationGroup.class})
    private Long id;
    @NotBlank(message = "username could not be blank")
    private String username;
    @NotBlank(message = "password could not be blank")
    private String password;


}
