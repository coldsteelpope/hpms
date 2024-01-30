package com.hus.hpms.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class RequestUpdateParam extends RequestCRUDParam
{
    public RequestUpdateParam()
    {
        this.departmentIds = new ArrayList<>();
        this.detailArea = new ArrayList<>();
    }
}
