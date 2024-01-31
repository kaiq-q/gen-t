package com.example.gent.service;

import com.example.gent.dao.ClienteDao;
import com.example.gent.dao.EnderecoDao;
import com.example.gent.entity.Cliente;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteDao clienteDao;

    EnderecoDao enderecoDao;

    public ClienteService(EnderecoDao enderecoDao){
        this.enderecoDao = enderecoDao;
    }

    public void saveCliente(Cliente cliente){
        if (cliente == null){
            System.err.println("Cliente is null. Are you sure you have connected your form to the application?");
            return;
        }
        cliente.setStatus(true);
        cliente.setDataCadastro(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        cliente.setCpf(cliente.getFormattedCpf());
        enderecoDao.save(cliente.getEndereco());
        clienteDao.save(cliente);
    }

    public List<Cliente> getCliente(String nameFiler){
        if(nameFiler == null || nameFiler.isEmpty()){
            List<Cliente> clienteList = new ArrayList<>();
            clienteDao.findAll().forEach(clienteList::add);
            return clienteList;
        }else{
            return clienteDao.findByName(nameFiler);
        }
    }

    public Cliente getCliente(Integer idCliente){
        return clienteDao.findById(idCliente).orElseThrow(() -> new EntityNotFoundException("Cliente not found with id: " + idCliente));
    }

    public void deactiveCliente(Cliente cliente){
      clienteDao.findById(cliente.getId());
      cliente.setStatus(false);
      clienteDao.save(cliente);
    }

    public Cliente updateCliente(Cliente cliente){
        clienteDao.findById(cliente.getId()).orElseThrow();
        return clienteDao.save(cliente);
    }
}
