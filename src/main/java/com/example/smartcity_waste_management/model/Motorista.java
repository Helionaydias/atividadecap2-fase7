package com.example.smartcity_waste_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "TB_MOTORISTA")
@Data
public class Motorista {
    @Id
    @NotBlank(message = "ID do motorista é obrigatório")
    private String idMotorista;

    @NotBlank(message = "Nome do motorista é obrigatório")
    private String nmMotorista;

    @NotBlank(message = "CPF do motorista é obrigatório")
    private String cpfMotorista;

    @NotNull(message = "CNH do motorista é obrigatória")
    private Long cnhMotorista;

    @NotBlank(message = "Categoria da CNH é obrigatória")
    private String cnhCategoria;

    @NotBlank(message = "Status do motorista é obrigatório")
    private String statusMotorista;
}
