package com.hus.hpms.dto.department;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class DepartmentLoginParam
{
    private String loginId;
    private String loginPw;

    public DepartmentLoginParam() {}

    public DepartmentLoginParam(String loginId, String loginPw)
    {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}
