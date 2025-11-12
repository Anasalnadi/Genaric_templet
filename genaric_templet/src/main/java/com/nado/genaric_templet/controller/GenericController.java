package com.nado.genaric_templet.controller;

import com.nado.genaric_templet.service.GenericCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<D,ID> {

    protected final GenericCRUDService<D, ID> service;

    protected GenericController(GenericCRUDService<D, ID> service) {
        this.service = service;
    }


    @PostMapping
    public D create(@RequestBody D dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public D update(@PathVariable ID id, @RequestBody D dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public D getById(@PathVariable ID id) {
        return service.findById(id);
    }

    @GetMapping
    public List<D> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        service.delete(id);
    }
}
