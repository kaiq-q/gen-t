package com.example.gent.dao;

import com.example.gent.entity.Carga;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CargaDao extends CrudRepository<Carga, Integer> {


}
