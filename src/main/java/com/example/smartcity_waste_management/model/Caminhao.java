package com.example.smartcity_waste_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "TB_CAMINHAO")
@Data
public class Caminhao {
    @Id
    @NotBlank(message = "ID do caminhão é obrigatório")
    private String idCaminhao;

    @NotBlank(message = "Placa do caminhão é obrigatória")
    private String nmPlaca;

    @NotNull(message = "Capacidade do caminhão é obrigatória")
    private Double kgCapacidade;

    @NotBlank(message = "Estado de origem é obrigatório")
    private String estOrigem;
}
