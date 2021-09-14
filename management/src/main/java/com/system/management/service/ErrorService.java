package com.system.management.service;

import com.system.management.entity.ErrorResponse;
import com.system.management.repository.ErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorService {
    private final ErrorRepository repository;

    @Transactional(readOnly = true)
    public List<ErrorResponse> findAll(){

        Iterable<ErrorResponse> errorIter =repository.findAll();
        List<ErrorResponse> errorResponseList =new ArrayList<>();
        errorIter.iterator().forEachRemaining(errorResponseList::add);
        return errorResponseList;
    }

    @Transactional
    public ErrorResponse save(ErrorResponse errorResponse){
        return repository.save(errorResponse);
    }
}
