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
        if (carga == null) {
            System.err.println("Carga is null. Are you sure you have connected your form to the application?");
            return;
        }
        cargaDao.save(carga);
    }

    public List<Carga> getCargas(String stringFilter){

        if (stringFilter == null || stringFilter.isEmpty()){
            List<Carga> cargaList = new ArrayList<>();
            cargaDao.findAll().forEach(cargaList::add);
            return cargaList;
        }else{
            return cargaDao.search(stringFilter);
        }


    }

    public Carga getCargas(Integer cargaId){
        return cargaDao.findById(cargaId).orElseThrow();
    }

    public void deleteCarga(Integer cargaId){
        cargaDao.deleteById(cargaId);
    }

    public Carga updateCarga(Carga carga){
        cargaDao.findById(carga.getCardaId()).orElseThrow();
        return cargaDao.save(carga);
    }


}
