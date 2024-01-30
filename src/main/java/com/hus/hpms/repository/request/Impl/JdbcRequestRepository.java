package com.hus.hpms.repository.request.Impl;

import com.hus.hpms.constants.RequestSql;
import com.hus.hpms.domain.Request;
import com.hus.hpms.repository.request.RequestDeleteParam;
import com.hus.hpms.repository.request.RequestRepository;
import com.hus.hpms.repository.request.RequestStatusUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class JdbcRequestRepository implements RequestRepository
{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcRequestRepository(DataSource dataSource)
    {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("REQUEST");
    }

    @Override
    public List<Request> saveAll(List<Request> requests)
    {
        String id = UUID.randomUUID().toString();
        for (Request request : requests) {
            request.setId(id);
            SqlParameterSource param = new BeanPropertySqlParameterSource(request);
            namedParameterJdbcTemplate.update(RequestSql.INSERT_REQUEST, param);
            //simpleJdbcInsert.executeAndReturnKey(param);

        }
        return requests;
    }

    @Override
    public void delete(RequestDeleteParam requestDeleteParam)
    {
        SqlParameterSource param = new BeanPropertySqlParameterSource(requestDeleteParam);
        namedParameterJdbcTemplate.update(RequestSql.DELETE_REQUEST, param);
    }

    @Override
    public List<Request> findAllById(String id)
    {
        Map<String, Object> param = Map.of("id", id);
        return namedParameterJdbcTemplate.query(RequestSql.FIND_ALL_REQUESTS_BY_ID, param, requestRowMapper());
    }

    @Override
    public void deleteAllById(String id) {
        Map<String, Object> param = Map.of("id", id);
        namedParameterJdbcTemplate.update(RequestSql.DELETE_REQUEST, param);
    }

    @Override
    public void updateStatus(String id, String status) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("status", status)
                .addValue("id", id);
        namedParameterJdbcTemplate.update(RequestSql.UPDATE_STATUS_BY_ID, param);
    }

    @Override
    public List<Request> findAllRequestsByDepartmentId(Long departmentId)
    {
        Map<String, Object> param = Map.of("departmentId", departmentId);
        return namedParameterJdbcTemplate.query(RequestSql.FIND_ALL_REQUESTS_BY_DEPARTMENT_ID, param, requestRowMapper());
    }

    @Override
    public void update(List<Request> requests, String id)
    {
        deleteAllById(id);
        for (Request request : requests) {
            request.setId(id);
            SqlParameterSource param = new BeanPropertySqlParameterSource(request);
            namedParameterJdbcTemplate.update(RequestSql.INSERT_REQUEST, param);
        }
    }

    private RowMapper<Request> requestRowMapper()
    {
        return BeanPropertyRowMapper.newInstance(Request.class);
    }
}
