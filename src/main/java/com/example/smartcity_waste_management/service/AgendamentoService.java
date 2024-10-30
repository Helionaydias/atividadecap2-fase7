package com.example.smartcity_waste_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartcity_waste_management.model.Agendamento;
import com.example.smartcity_waste_management.repository.AgendamentoRepository;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> getAllAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento getAgendamentoById(String id) {
        return agendamentoRepository.findById(id).orElse(null);
    }

    public Agendamento saveAgendamento(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public void deleteAgendamento(String id) {
        agendamentoRepository.deleteById(id);
    }
}
