package com.example.smartcity_waste_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartcity_waste_management.model.Coleta;
import com.example.smartcity_waste_management.repository.ColetaRepository;

@Service
public class ColetaService {
    @Autowired
    private ColetaRepository coletaRepository;

    public List<Coleta> getAllColetas() {
        return coletaRepository.findAll();
    }

    public Coleta getColetaById(String id) {
        return coletaRepository.findById(id).orElse(null);
    }

    public Coleta saveColeta(Coleta coleta) {
        return coletaRepository.save(coleta);
    }

    public void deleteColeta(String id) {
        coletaRepository.deleteById(id);
    }
}
