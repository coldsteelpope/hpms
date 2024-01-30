package com.hus.hpms.repository.request;

import com.hus.hpms.domain.Department;
import com.hus.hpms.domain.Request;
import org.aspectj.apache.bcel.Repository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository
{
    public List<Request> saveAll(List<Request> requests);
    public void delete(RequestDeleteParam requestDeleteParam);
    public List<Request> findAllById(String id);
    public void deleteAllById(String id);
    public void updateStatus(String id, String status);
    public List<Request> findAllRequestsByDepartmentId(Long departmentId);

    public void update(List<Request> requests, String id);
}
