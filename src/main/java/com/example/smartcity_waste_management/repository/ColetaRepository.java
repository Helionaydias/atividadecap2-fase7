package com.example.smartcity_waste_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smartcity_waste_management.model.Coleta;

public interface ColetaRepository extends JpaRepository<Coleta, String> {
}
