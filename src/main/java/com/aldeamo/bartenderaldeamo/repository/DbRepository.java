package com.aldeamo.bartenderaldeamo.repository;

import javax.transaction.Transactional;
import com.aldeamo.bartenderaldeamo.modelData.Arrays;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface DbRepository extends CrudRepository<Arrays, Integer> {
    Arrays findArraysById(int id);
}
