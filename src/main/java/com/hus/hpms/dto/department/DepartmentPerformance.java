package com.hus.hpms.dto.department;

import com.hus.hpms.domain.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class DepartmentPerformance
{
    private Long id;
    private String field;
    private String detailField;
    private Long totalRequests;
    private Long readyRequests;
    private Long processingRequests;
    private Long doneRequests;
    private Double doneRatio;
    private Double processingRatio;
    private Double ReadyRatio;

    private List<Request> requests;
    public DepartmentPerformance() {}
}
