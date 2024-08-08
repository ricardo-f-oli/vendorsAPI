package com.example.vendorsAPI.domain.entities.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContractTypeEnum {

    OUT("OUTSOURCING"),

    CLT("CLT"),

    PJ("PJ");

    private String value;

    ContractTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static ContractTypeEnum fromValue(String value) {
        for (ContractTypeEnum b :ContractTypeEnum.values()) {
            if (b.value.equals(value.toUpperCase())) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
