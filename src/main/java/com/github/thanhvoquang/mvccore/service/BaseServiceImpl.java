package com.github.thanhvoquang.mvccore.service;

import com.github.thanhvoquang.mvccore.model.entity.BaseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Long> implements BaseService<E, ID> {

    private final JpaRepository<E, ID> repo;

    public BaseServiceImpl(JpaRepository<E, ID> repo) {
        this.repo = repo;
    }

    @Override
    public List<E> getAll(Pageable pageable) {
        if (pageable == null) {
            //just get 10 first when default
            pageable = PageRequest.of(0, 10);
        }
        List<E> entities = repo.findAll(pageable).get().collect(Collectors.toList());
        return entities;
    }

    @Override
    public E add(E entity) {
        repo.save(entity);
        return entity;
    }

    @Override
    public List<E> addMultiple(List<E> entities, int batchSize) {
        //recommend just 50 element each loop
        if (batchSize < entities.size()) {
            List<List<E>> batchEntities = IntStream.range(0, entities.size())
                    .boxed()
                    .collect(Collectors.groupingBy(index -> index / batchSize))
                    .values()
                    .stream()
                    .map(indices -> indices
                            .stream()
                            .map(entities::get)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            for (List<E> batch : batchEntities) {
                repo.saveAll(batch);
            }
        }

        repo.saveAll(entities);

        return entities;
    }

    @Override
    public E update(E entities) {
        Optional<E> eOptional = repo.findById(entities.getId());
        if (eOptional.isPresent()) {
            E eSave = eOptional.get();
            repo.saveAndFlush(eSave);
            return entities;
        }
        throw new NoSuchElementException("No value present");
    }
}
