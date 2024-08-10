package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import com.example.vendorsAPI.exceptions.InvalidDocumentException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;


@Service
public class CreateVendorService {
    private final VendorRepository vendorRepository;
    private final BranchAPICallerService branchAPICallerService;
    private static final SecureRandom RANDOM = new SecureRandom();

    public CreateVendorService(VendorRepository vendorRepository, BranchAPICallerService branchAPICallerService) {
        this.vendorRepository = vendorRepository;
        this.branchAPICallerService = branchAPICallerService;
    }
    public Vendor createVendor(Vendor vendor, String branchName) {
        if(!isUnique(vendor.getDocument())){
            throw new InvalidDocumentException("CPF ou CNPJ já cadastrado na base de dados!");
        }
        vendor.setRegistration(GenerateRegistration(vendor.getContractTypeEnum()));
        vendor.setBranch(branchAPICallerService.getBranch(branchName));
        vendorRepository.save(vendor);
        return vendor;
    }

    public String GenerateRegistration(ContractTypeEnum contractTypeEnum){
            String registration;
            do {
                int randomNumber = RANDOM.nextInt(90000000) + 10000000;
                registration = String.format("%08d-%s", randomNumber, contractTypeEnum.name());
            } while (this.vendorRepository.findByRegistration(registration).isPresent());

            return registration;
    }

    private boolean isUnique(String document) {
        return !vendorRepository.existsByDocument(document);
    }
}
