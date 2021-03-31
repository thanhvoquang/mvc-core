package com.github.thanhvoquang.mvccore.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<E, ID> {
    List<E> addMultiple(List<E> entities, int batchSize);
    E add(E entity);
    E update(E entity);
    List<E> getAll(Pageable pageable);
    E getById(ID id);
    String deleteById(ID id);
}
