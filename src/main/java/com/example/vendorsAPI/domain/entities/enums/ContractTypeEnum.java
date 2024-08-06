package com.example.vendorsAPI.domain.entities.enums;

import com.example.vendorsAPI.model.Vendor;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContractTypeEnum {

    OUTSOURCING("outsourcing"),

    CLT("clt"),

    PJ("pj");

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
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
