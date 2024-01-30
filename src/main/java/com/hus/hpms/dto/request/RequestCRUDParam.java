package com.hus.hpms.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class RequestCRUDParam
{
    protected String area;
    protected List<String> departmentIds;
    protected List<String> detailArea;
    protected String request;
    protected String currentDate;
    protected String expectedCompleteDate;
}
