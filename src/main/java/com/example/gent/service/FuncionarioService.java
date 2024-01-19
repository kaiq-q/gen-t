package com.example.gent.service;

import com.example.gent.dao.CargoDao;
import com.example.gent.dao.FuncionarioDao;
import com.example.gent.entity.Cargo;
import com.example.gent.entity.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioDao funcionarioDao;

    CargoDao cargoDao;

    public FuncionarioService(CargoDao cargoDao){
        this.cargoDao = cargoDao;
    }

    public void saveFuncionario(Funcionario funcionario){
        if (funcionario == null){
            System.err.println("Funcionario is null. Are you sure you have connected your form to the application?");
            return;
        }
        funcionario.setStatus(true);
        funcionario.setDataAdmissao(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        funcionarioDao.save(funcionario);
    }

    //Overload methods
    public List<Funcionario> getFuncionario(String nameFilter){
        if (nameFilter == null || nameFilter.isEmpty()){
            List<Funcionario> funcionarioList = new ArrayList<>();
            funcionarioDao.findAll().forEach(funcionarioList::add);
            return funcionarioList;
        }else{
            return funcionarioDao.findByName(nameFilter);
        }
    }

    public Funcionario getFuncionario(Integer idFuncionario){
        return funcionarioDao.findById(idFuncionario).orElseThrow();
    }

    public Funcionario updateFuncionatio(Funcionario funcionario){
        funcionarioDao.findById(funcionario.getId()).orElseThrow();
        return funcionarioDao.save(funcionario);
    }

    public void deactiveFuncionario(Funcionario funcionario){
        funcionarioDao.findById(funcionario.getId());
        funcionario.setStatus(false);
        funcionarioDao.save(funcionario);

    }

    public List<Cargo> findAllCargos(){
        List<Cargo> cargoList = new ArrayList<>();
        cargoDao.findAll().forEach(cargoList::add);
        return cargoList;
    }

}
