package com.example.vendorsAPI.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Branch {

//    @JsonProperty("ID")
//    private long ID;
    @JsonProperty("Nome")
    private String branchName;
    @JsonProperty("CNPJ")
    private String branchCNPJ;
    @JsonProperty("Cidade")
    private String branchCity;
    @JsonProperty("UF")
    private String branchUF;
    @JsonProperty("Tipo")
    private String branchType;
    @JsonProperty("Ativo")
    private Boolean active;
    @JsonProperty("Data Cadastro")
    private LocalDate branchRegisterDate;
    @JsonProperty("Última Atualização")
    private LocalDate branchLastUpdateDate;

}
