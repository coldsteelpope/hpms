package com.hus.hpms.dto.department;

import lombok.Data;

@Data
public class DepartmentRegisterParam
{
    private String loginId;
    private String loginPw;
    private String departmentType;
    private String field;
    private String fieldType;
    private String detailField;

    public DepartmentRegisterParam() {}

    public DepartmentRegisterParam(
            String loginId,
            String loginPw,
            String departmentType,
            String field,
            String fieldType,
            String detailField
    )
    {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.departmentType = departmentType;
        this.field = field;
        this.fieldType = fieldType;
        this.detailField = detailField;
    }
}
