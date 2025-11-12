package com.nado.genaric_templet.service;

import java.util.List;

public interface GenericCRUDService<D,ID> {
    D create(D dto);
    D update(ID id, D dto);
    D findById(ID id);
    List<D> findAll();
    void delete(ID id);
}
