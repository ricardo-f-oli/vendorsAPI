package com.example.vendorsAPI.adapter.controller.mapper;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import com.example.vendorsAPI.domain.service.BranchAPICallerService;
import com.example.vendorsAPI.model.VendorRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VendorMapper {

    private static VendorRepository vendorRepository;

    public VendorMapper(VendorRepository vendorRepository) {
        VendorMapper.vendorRepository = vendorRepository;
    }

    private static final BranchAPICallerService branchAPICallerService = new BranchAPICallerService();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SecureRandom RANDOM = new SecureRandom();

    public static Vendor toDto(VendorRequest vendorRequest){
        String registration;
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
        do {
            registration = generateRegistration(vendorDto.getContractTypeEnum());
        } while (vendorRepository.findByRegistration(registration).isPresent());
        vendorDto.setRegistration(registration);
        vendorDto.setBranch(branchAPICallerService.getBranch(vendorRequest.getBranch()));
        return vendorDto;
    }

    public static ContractTypeEnum toContractTypeEnum(VendorRequest.ContractTypeEnum contractTypeEnum){
        return ContractTypeEnum.valueOf(contractTypeEnum.name());
    }


    public static String generateRegistration(ContractTypeEnum contractTypeEnum) {
        int randomNumber = RANDOM.nextInt(90000000) + 10000000; // Generate an 8-digit number
        return String.format("%08d-%s", randomNumber, contractTypeEnum.name());
    }
}
