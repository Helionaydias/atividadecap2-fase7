package com.example.smartcity_waste_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartcity_waste_management.model.Motorista;
import com.example.smartcity_waste_management.repository.MotoristaRepository;

@Service
public class MotoristaService {
    @Autowired
    private MotoristaRepository motoristaRepository;

    public List<Motorista> getAllMotoristas() {
        return motoristaRepository.findAll();
    }

    public Motorista getMotoristaById(String id) {
        return motoristaRepository.findById(id).orElse(null);
    }

    public Motorista saveMotorista(Motorista motorista) {
        return motoristaRepository.save(motorista);
    }

    public void deleteMotorista(String id) {
        motoristaRepository.deleteById(id);
    }
}
