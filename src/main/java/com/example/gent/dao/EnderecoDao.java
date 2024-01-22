package com.example.gent.dao;

import com.example.gent.entity.Endereco;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EnderecoDao extends CrudRepository<Endereco, Integer> {

}
