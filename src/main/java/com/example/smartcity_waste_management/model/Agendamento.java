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
@Table(name = "TB_AGENDAMENTO")
@Data
public class Agendamento {
    @Id
    @NotBlank(message = "ID do agendamento é obrigatório")
    private String idAgenda;

    @NotNull(message = "Data do agendamento é obrigatória")
    private Date dtAgenda;

    @NotNull(message = "Hora do agendamento é obrigatória")
    private Date hrAgenda;

    @NotBlank(message = "Endereço de coleta é obrigatório")
    private String endColeta;

    private String dsObs;
}
