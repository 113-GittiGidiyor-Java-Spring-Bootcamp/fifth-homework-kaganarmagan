package com.system.management.service;

import java.util.List;

public interface BaseService<T>{

    List<T> findAll();
    T findById(int id);
    void delete(T object);
    void deleteById(int id);

}
