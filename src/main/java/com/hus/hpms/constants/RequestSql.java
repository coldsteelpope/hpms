package com.hus.hpms.constants;

public interface RequestSql
{
    public String INSERT_REQUEST = "INSERT INTO REQUEST (id, department_id, area, detail_area, request, status, request_date, complete_date, expected_complete_date, insert_date, update_date) VALUES (:id, :departmentId, :area, :detailArea, :request, :status, :requestDate, :completeDate, :expectedCompleteDate, :insertDate, :updateDate)";
    public String DELETE_REQUEST = "DELETE FROM REQUEST WHERE id=:id";
    public String FIND_ALL_REQUESTS_BY_ID = "SELECT * FROM REQUEST WHERE id=:id";
    public String UPDATE_STATUS_BY_ID = "UPDATE REQUEST SET status=:status WHERE id=:id";
    public String FIND_ALL_REQUESTS_BY_DEPARTMENT_ID = "SELECT * FROM REQUEST WHERE department_id=:departmentId";
}
