package com.system.management.repository;

import com.system.management.entity.ErrorResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ErrorRepository extends CrudRepository<ErrorResponse,Long> {

}
