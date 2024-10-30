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

import com.example.smartcity_waste_management.model.Caminhao;
import com.example.smartcity_waste_management.service.CaminhaoService;

@RestController
@RequestMapping("/caminhoes")
public class CaminhaoController {
    @Autowired
    private CaminhaoService caminhaoService;

    @GetMapping
    public List<Caminhao> getAllCaminhoes() {
        return caminhaoService.getAllCaminhoes();
    }

    @GetMapping("/{id}")
    public Caminhao getCaminhaoById(@PathVariable String id) {
        return caminhaoService.getCaminhaoById(id);
    }

    @PostMapping
    public Caminhao createCaminhao(@RequestBody Caminhao caminhao) {
        return caminhaoService.saveCaminhao(caminhao);
    }

    @PutMapping("/{id}")
    public Caminhao updateCaminhao(@PathVariable String id, @RequestBody Caminhao caminhao) {
        Caminhao existingCaminhao = caminhaoService.getCaminhaoById(id);
        if (existingCaminhao != null) {
            caminhao.setIdCaminhao(id);
            return caminhaoService.saveCaminhao(caminhao);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCaminhao(@PathVariable String id) {
        caminhaoService.deleteCaminhao(id);
    }
}
