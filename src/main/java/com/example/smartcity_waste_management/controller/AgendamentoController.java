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

import com.example.smartcity_waste_management.model.Agendamento;
import com.example.smartcity_waste_management.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List<Agendamento> getAllAgendamentos() {
        return agendamentoService.getAllAgendamentos();
    }

    @GetMapping("/{id}")
    public Agendamento getAgendamentoById(@PathVariable String id) {
        return agendamentoService.getAgendamentoById(id);
    }

    @PostMapping
    public Agendamento createAgendamento(@RequestBody Agendamento agendamento) {
        return agendamentoService.saveAgendamento(agendamento);
    }

    @PutMapping("/{id}")
    public Agendamento updateAgendamento(@PathVariable String id, @RequestBody Agendamento agendamento) {
        Agendamento existingAgendamento = agendamentoService.getAgendamentoById(id);
        if (existingAgendamento != null) {
            agendamento.setIdAgenda(id);
            return agendamentoService.saveAgendamento(agendamento);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAgendamento(@PathVariable String id) {
        agendamentoService.deleteAgendamento(id);
    }
}
