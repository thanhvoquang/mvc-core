package com.github.thanhvoquang.mvccore.model.mapper;

import java.util.List;

public interface BaseMapper<E, D> {

    D toDTO(E e);

    List<D> toDTOs(List<E> eList);

    E toEntity(D d);

    List<E> toEntities(List<D> dList);
}
