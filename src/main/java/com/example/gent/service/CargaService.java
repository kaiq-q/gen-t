package com.example.gent.service;

import com.example.gent.dao.CargaDao;
import com.example.gent.entity.Carga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargaService {

    @Autowired
    public CargaDao cargaDao;


    public void saveCarga(Carga carga){
        cargaDao.save(carga);
    }

    public void deleteCarga(Integer cargaId){
        cargaDao.deleteById(cargaId);
    }

    public List<Carga> getCargas(String filter){
        if(filter == null || filter.isEmpty()){
            List<Carga> cargaList = new ArrayList<>();
            cargaDao.findAll().forEach(cargaList::add);
            return cargaList;
        }else{
            List<Carga> cargaList = new ArrayList<>();
            cargaDao.findAll().forEach(cargaList::add);
            return cargaList;
        }
    }

}
