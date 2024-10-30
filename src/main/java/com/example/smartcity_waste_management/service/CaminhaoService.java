package com.example.smartcity_waste_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartcity_waste_management.model.Caminhao;
import com.example.smartcity_waste_management.repository.CaminhaoRepository;

@Service
public class CaminhaoService {
    @Autowired
    private CaminhaoRepository caminhaoRepository;

    public List<Caminhao> getAllCaminhoes() {
        return caminhaoRepository.findAll();
    }

    public Caminhao getCaminhaoById(String id) {
        return caminhaoRepository.findById(id).orElse(null);
    }

    public Caminhao saveCaminhao(Caminhao caminhao) {
        return caminhaoRepository.save(caminhao);
    }

    public void deleteCaminhao(String id) {
        caminhaoRepository.deleteById(id);
    }
}
