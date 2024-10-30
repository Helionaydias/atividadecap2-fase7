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

import com.example.smartcity_waste_management.model.Coleta;
import com.example.smartcity_waste_management.service.ColetaService;

@RestController
@RequestMapping("/coletas")
public class ColetaController {
    @Autowired
    private ColetaService coletaService;

    @GetMapping
    public List<Coleta> getAllColetas() {
        return coletaService.getAllColetas();
    }

    @GetMapping("/{id}")
    public Coleta getColetaById(@PathVariable String id) {
        return coletaService.getColetaById(id);
    }

    @PostMapping
    public Coleta createColeta(@RequestBody Coleta coleta) {
        return coletaService.saveColeta(coleta);
    }

    @PutMapping("/{id}")
    public Coleta updateColeta(@PathVariable String id, @RequestBody Coleta coleta) {
        Coleta existingColeta = coletaService.getColetaById(id);
        if (existingColeta != null) {
            coleta.setIdColeta(id);
            return coletaService.saveColeta(coleta);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteColeta(@PathVariable String id) {
        coletaService.deleteColeta(id);
    }
}
