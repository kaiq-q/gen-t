package com.example.gent.service;

import com.example.gent.dao.CargoDao;
import com.example.gent.entity.Carga;
import com.example.gent.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService {

    @Autowired
    public CargoDao cargoDao;

    public void saveCargo(Cargo cargo){
        if (cargo == null){
            System.err.println("Cargo is null. Are you sure you have connected your form to the application?");
            return;
        }
        cargoDao.save(cargo);
    }

    public List<Cargo> getCargo(String filter){
        if (filter == null || filter.isEmpty()){
            List<Cargo> cargoList = new ArrayList<>();
            cargoDao.findAll().forEach(cargoList::add);
            return cargoList;
        }else{
            //return cargaDao.search(stringFilter);
            return null;
        }
    }

    public Cargo getCargo(Integer cargoId){
        return cargoDao.findById(cargoId).orElseThrow();
    }

    public void deactiveCargo(Cargo cargo){
        cargoDao.findById(cargo.getId());
        cargo.setStatus(false);
        cargoDao.save(cargo);
    }

    public Cargo updateCargo(Cargo cargo){
        cargoDao.findById(cargo.getId());
        return cargoDao.save(cargo);
    }

}

