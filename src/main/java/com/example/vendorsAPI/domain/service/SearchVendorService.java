package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchVendorService {
    private final VendorRepository vendorRepository;
    public SearchVendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    public List<Vendor> findByBranch(String branchName) {
        return vendorRepository.findByBranch(branchName);
    }

    public List<Vendor> findByContractType(String contractType) {
        return vendorRepository.findByContractType(ContractTypeEnum.valueOf(contractType));
    }
    public Vendor findByRegistration(String registration) {
        Vendor vendor = new Vendor();
        if (vendorRepository.findByRegistration(registration).isPresent()) {
            vendor = vendorRepository.findByRegistration(registration).get();
            return vendor;
        } else {
            throw new RuntimeException("Seller not found with registration: " + registration);
        }
    }
    public Vendor findById(int id) {
        return new Vendor();
    }
}
