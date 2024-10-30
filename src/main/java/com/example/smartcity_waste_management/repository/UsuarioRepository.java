package com.example.smartcity_waste_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smartcity_waste_management.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
