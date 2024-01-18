package com.example.gent.dao;

import com.example.gent.entity.Cargo;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CargoDao extends CrudRepository<Cargo,Integer> {
}
