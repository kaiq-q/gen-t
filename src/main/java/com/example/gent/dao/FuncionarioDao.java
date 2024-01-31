package com.example.gent.dao;

import com.example.gent.entity.Cliente;
import com.example.gent.entity.Funcionario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FuncionarioDao extends CrudRepository<Funcionario, Integer> {

    @Query("SELECT f FROM Funcionario f " +
            "WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(f.sobrenome) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Funcionario> findByName(@Param("searchTerm") String searchTerm);

    @Query("SELECT f FROM Funcionario f "+
           "WHERE f.cargo.id = :searchTerm"
    )
    List<Funcionario> findAllMotorista(@Param("searchTerm") Integer searchTerm);

    @Query("SELECT f FROM Funcionario f "+
            "WHERE f.cargo.id = :searchTerm"
    )
    List<Funcionario> findAllAjudante(@Param("searchTerm") Integer searchTerm);

}
