package br.com.bb.demo.service;

import java.util.List;

public interface SNService<T, U, V> {
    T save(U u);
    T getById(V v);
    List<T> getAll();
    void delete(V v);
}