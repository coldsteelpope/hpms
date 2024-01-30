package com.hus.hpms.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestCreateParam extends RequestCRUDParam
{
    public RequestCreateParam()
    {
        this.departmentIds = new ArrayList<>();
        this.detailArea = new ArrayList<>();
    }
}
