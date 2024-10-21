package com.khanhisdev.movieservice.dto.Mapper;

import java.util.List;

public interface GenericMapper<E,I,O> {
    E mapToEntity (I request);

    O mapToDto(E entity);

    List<E> requestToEntity(List<I> requests);

    List<O> entityToResponse(List<E> entities);
}
