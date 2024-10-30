package com.example.smartcity_waste_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.smartcity_waste_management.model.Usuario;
import com.example.smartcity_waste_management.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(new Usuario(), new Usuario()));
        assertEquals(2, usuarioService.getAllUsuarios().size());
    }

    @Test
    public void testGetUsuarioById() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario("1");
        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));
        assertEquals(usuario, usuarioService.getUsuarioById("1"));
    }

    @Test
    public void testSaveUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        assertEquals(usuario, usuarioService.saveUsuario(usuario));
    }

    @Test
    public void testDeleteUsuario() {
        String id = "1";
        usuarioService.deleteUsuario(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
