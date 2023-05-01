package com.Softito.SoftitoECommerce.Repositories;

import java.util.List;
import java.util.Optional;

public interface IRepositoryService<T>{
    T add(T entity);
    List<T> getAll();
    T getById(Long id);
    boolean delete(Long id);
    String update(Long id,T entity);
}
