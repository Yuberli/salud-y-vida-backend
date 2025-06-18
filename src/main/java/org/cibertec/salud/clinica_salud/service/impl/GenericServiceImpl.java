package org.cibertec.salud.clinica_salud.service.impl;
import org.cibertec.salud.clinica_salud.service.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class GenericServiceImpl <T,ID> implements GenericService<T, ID> {

    protected  JpaRepository<T,ID> repository;

    @Override
    public T getById(ID id) {
        return repository.findById(id).get();
    }

    @Override
    public List<T> getAll() {
        return  repository.findAll();
    }

    @Override
    public void create(T entity) {
        repository.save(entity);
    }

    @Override
    public void modify(T entity) {
        repository.save(entity);
    }

    @Override
    public void remove(ID id) {
        repository.deleteById(id);
    }
}



/*
    public GenericServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }*/

