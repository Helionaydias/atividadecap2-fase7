package com.example.smartcity_waste_management.model;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "TB_COLETA")
@Data
public class Coleta {
    @Id
    @NotBlank(message = "ID da coleta é obrigatório")
    private String idColeta;

    @NotNull(message = "Quantidade de coleta é obrigatória")
    private Double kgColeta;

    @NotNull(message = "Data da coleta é obrigatória")
    private Date dtColeta;

    @NotNull(message = "Hora da coleta é obrigatória")
    private Date hrColeta;

    @NotBlank(message = "ID do motorista é obrigatório")
    private String idMotorista;

    @NotBlank(message = "ID do caminhão é obrigatório")
    private String idCaminhao;
}

