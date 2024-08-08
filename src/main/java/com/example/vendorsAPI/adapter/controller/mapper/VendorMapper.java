package com.example.vendorsAPI.adapter.controller.mapper;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.model.VendorRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VendorMapper {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static Vendor toDto(VendorRequest vendorRequest){
        Vendor vendorDto = new Vendor();
        vendorDto.setName(vendorRequest.getName());
        try {
            vendorDto.setBirthday(dateFormat.parse(vendorRequest.getBirthday()));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd-MM-yyyy");
        }
        vendorDto.setEmail(vendorRequest.getEmail());
        vendorDto.setDocument(vendorRequest.getDocument());
        vendorDto.setContractTypeEnum(toContractTypeEnum(vendorRequest.getContractType()));
        return vendorDto;
    }

    public static ContractTypeEnum toContractTypeEnum(VendorRequest.ContractTypeEnum contractTypeEnum){
        return ContractTypeEnum.valueOf(contractTypeEnum.name());
    }
}
