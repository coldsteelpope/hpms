package com.hus.hpms.service;

import com.hus.hpms.domain.Request;
import com.hus.hpms.dto.request.RequestCreateParam;
import com.hus.hpms.repository.comment.CommentRepository;
import com.hus.hpms.repository.request.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestService
{
    private final RequestRepository requestRepository;

    public List<Request> save(List<Request> requests)
    {
        return requestRepository.saveAll(requests);
    }

    public void update(List<Request> requests, String id)
    {
        requestRepository.update(requests, id);
    }
    public List<Request> findById(String id)
    {
        return requestRepository.findAllById(id);
    }
    public void updateStatus(String id, String status)
    {
        requestRepository.updateStatus(id, status);
    }
}
