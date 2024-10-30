package com.example.smartcity_waste_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartcity_waste_management.model.Motorista;
import com.example.smartcity_waste_management.service.MotoristaService;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {
    @Autowired
    private MotoristaService motoristaService;

    @GetMapping
    public List<Motorista> getAllMotoristas() {
        return motoristaService.getAllMotoristas();
    }

    @GetMapping("/{id}")
    public Motorista getMotoristaById(@PathVariable String id) {
        return motoristaService.getMotoristaById(id);
    }

    @PostMapping
    public Motorista createMotorista(@RequestBody Motorista motorista) {
        return motoristaService.saveMotorista(motorista);
    }

    @PutMapping("/{id}")
    public Motorista updateMotorista(@PathVariable String id, @RequestBody Motorista motorista) {
        Motorista existingMotorista = motoristaService.getMotoristaById(id);
        if (existingMotorista != null) {
            motorista.setIdMotorista(id);
            return motoristaService.saveMotorista(motorista);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteMotorista(@PathVariable String id) {
        motoristaService.deleteMotorista(id);
    }
}
