package com.hus.hpms.constants;

public interface DepartmentSql
{
    public String UPDATE_COMMIT = "UPDATE DEPARTMENT SET commit=:commit, update_date=:updateDate WHERE id=:id";
    public String FIND_ALL_TYPE_DEP = "SELECT * FROM DEPARTMENT WHERE detail_field IS NULL";
    public String FIND_ALL_TYPE_MAJOR_BY_FIELD = "SELECT * FROM DEPARTMENT WHERE field=:field AND detail_field IS NOT NULL";
    public String FIND_DEPARTMENT_BY_FIELD = "SELECT * FROM DEPARTMENT WHERE detail_field=:detailField";
    public String FIND_DEPARTMENT_BY_ID = "SELECT * FROM DEPARTMENT WHERE id=:id";
    public String FIND_DEPARTMENT_COUNT_BY_LOGIN_ID = "SELECT COUNT(*) FROM DEPARTMENT WHERE login_id = :loginId";
    public String FIND_DEPARTMENT_BY_LOGIN_ID = "SELECT * FROM DEPARTMENT WHERE login_id=:loginId";
    public String FIND_ALL_DEPARTMENTS = "SELECT * FROM DEPARTMENT";
    public String UPDATE_DEPARTMENT = "UPDATE DEPARTMENT SET field=:field, detail_field=:detailField, master=:master, admin=:admin WHERE id=:id";

    public String FIND_ALL_DEP_TYPE_DEPARTMENTS_PERFORMANCE = """
            SELECT
            department_table.field AS field,
            COUNT(request_table.id) AS totalRequests,
            COUNT(CASE WHEN request_table.status = 'ready' THEN 1 END) AS readyRequests,
            COUNT(CASE WHEN request_table.status = 'processing' THEN 1 END) AS processingRequests,
            COUNT(CASE WHEN request_table.status = 'done' THEN 1 END) AS doneRequests,
            department_table.id AS id
            FROM DEPARTMENT department_table
            LEFT JOIN REQUEST request_table ON department_table.id = request_table.department_id
            WHERE department_table.master = false AND department_table.detail_field IS NULL
            GROUP BY department_table.field
            """;

    public String FIND_ALL_MAJOR_TYPE_DEPARTMENTS_PERFORMANCE = """
            SELECT
            department_table.field AS field,
            department_table.detail_field AS detailField,
            COUNT(request_table.id) AS totalRequests,
            COUNT(CASE WHEN request_table.status = 'ready' THEN 1 END) AS readyRequests,
            COUNT(CASE WHEN request_table.status = 'processing' THEN 1 END) AS processingRequests,
            COUNT(CASE WHEN request_table.status = 'done' THEN 1 END) AS doneRequests,
            CASE WHEN COUNT(request_table.id) > 0 THEN CAST(COUNT(CASE WHEN request_table.status = 'done' THEN 1 END) AS DECIMAL) / COUNT(request_table.id) ELSE 0 END AS doneRatio,
            CASE WHEN COUNT(request_table.id) > 0 THEN CAST(COUNT(CASE WHEN request_table.status = 'processing' THEN 1 END) AS DECIMAL) / COUNT(request_table.id) ELSE 0 END AS processingRatio,
            CASE WHEN COUNT(request_table.id) > 0 THEN CAST(COUNT(CASE WHEN request_table.status = 'ready' THEN 1 END) AS DECIMAL) / COUNT(request_table.id) ELSE 0 END AS readyRatio,
            department_table.id AS id
            FROM DEPARTMENT department_table
            LEFT JOIN REQUEST request_table ON department_table.id = request_table.department_id
            WHERE department_table.master = false AND department_table.detail_field IS NOT NULL
            GROUP BY department_table.field
    """;

}

