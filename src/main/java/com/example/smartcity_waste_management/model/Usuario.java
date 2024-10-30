package com.example.smartcity_waste_management.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "TB_USUARIO")
@Data
public class Usuario {
    @Id
    @NotBlank(message = "ID do usuário é obrigatório")
    private String idUsuario;

    @NotBlank(message = "Nome do usuário é obrigatório")
    @Size(max = 50, message = "Nome do usuário não pode ter mais que 50 caracteres")
    private String nmUsuario;

    @NotBlank(message = "Email do usuário é obrigatório")
    @Email(message = "Email deve ser válido")
    private String dsEmail;

    @NotBlank(message = "CPF do usuário é obrigatório")
    private String nrCpf;

    @NotNull(message = "Data de nascimento do usuário é obrigatória")
    private Date dtNasc;
}

