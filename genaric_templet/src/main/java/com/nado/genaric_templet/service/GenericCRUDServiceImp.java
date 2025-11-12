package com.nado.genaric_templet.service;

import com.nado.genaric_templet.exception.custom.ResourceNotFoundException;
import com.nado.genaric_templet.mapper.GenericMapper;
import com.nado.genaric_templet.repo.GenericRepository;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericCRUDServiceImp<E,D,ID> implements GenericCRUDService<D,ID> {

    protected final GenericRepository<E, ID> repository;
    protected final GenericMapper<E, D> mapper;

    protected GenericCRUDServiceImp(GenericRepository<E, ID> repository, GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public D create(D dto) {
        E entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public D update(ID id, D dto) {
        E existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        E updated = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(updated));
    }

    @Override
    public D findById(ID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
    }

    @Override
    public List<D> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entity not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
