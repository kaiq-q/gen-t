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

}
