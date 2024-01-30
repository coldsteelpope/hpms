package com.hus.hpms.dto.department;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class DepartmentSession
{
    private Long id;
    private String field;
    private String detailField;
    private Boolean commit;
    private Boolean master;
    private Boolean admin;

    public DepartmentSession() {}

    public DepartmentSession(
            Long id,
            String field,
            String detailField,
            Boolean commit,
            Boolean master,
            Boolean admin
    )
    {
        this.id = id;
        this.field = field;
        this.detailField = detailField;
        this.commit = commit;
        this.master = master;
        this.admin = admin;
    }
}
