package com.example.vendorsAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    private long id;
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
