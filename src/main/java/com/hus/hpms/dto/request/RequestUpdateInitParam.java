package com.hus.hpms.dto.request;

import com.hus.hpms.domain.Department;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class RequestUpdateInitParam
{
    private List<Long> departmentIds;
    private List<Department> departments;
    private String area;
    private String detailArea;
    private String request;
    private String requestDate;
    private String expectedCompleteDate;

    public RequestUpdateInitParam()
    {
        this.departmentIds = new ArrayList<>();
        this.departments = new ArrayList<>();
    }

    public RequestUpdateInitParam(
            List<Long> departmentIds,
            List<Department> departments,
            String area,
            String detailArea,
            String request,
            String requestDate,
            String expectedCompleteDate
    )
    {
        this.departmentIds = departmentIds;
        this.departments = departments;
        this.area = area;
        this.detailArea = detailArea;
        this.request = request;
        this.requestDate = requestDate;
        this.expectedCompleteDate = expectedCompleteDate;
    }
}
