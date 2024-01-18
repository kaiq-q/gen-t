package com.example.gent.dao;

import com.example.gent.entity.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ClienteDao extends CrudRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c " +
            "WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(c.sobrenome) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Cliente> findByName(@Param("searchTerm") String searchTerm);

}
