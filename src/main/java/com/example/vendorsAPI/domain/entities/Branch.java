package com.example.vendorsAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String name;
    private String document;
    private String city;
    private String UF;
    private String type;
    private Boolean active;
    @JsonProperty("dataCadastro")
    private LocalDate registerDate;
    @JsonProperty("ultimaAtualizacao")
    private LocalDate lastUpdateDate;

}
