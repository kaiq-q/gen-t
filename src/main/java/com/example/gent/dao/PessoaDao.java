package com.example.gent.dao;

import com.example.gent.entity.Pessoa;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PessoaDao extends CrudRepository<Pessoa, Integer> {

}
