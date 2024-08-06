package com.example.vendorsAPI.domain.entities;

import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String registration;
    @NotNull
    private String name;

    private Date birthday;
    @NotNull
    private String document;
    @NotNull
    private String email;
    @NotNull
    private ContractTypeEnum contractTypeEnum;
    @NotNull
    private Branch branch;

}
