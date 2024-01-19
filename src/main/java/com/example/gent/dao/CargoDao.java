package com.example.gent.dao;

import com.example.gent.entity.Cargo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CargoDao extends CrudRepository<Cargo,Integer> {


    @Query("select c from Cargo c " +
            "where lower(c.info) like lower(concat('%', :searchTerm, '%')) ")
    List<Cargo> search(@Param("searchTerm") String searchTerm);
}
