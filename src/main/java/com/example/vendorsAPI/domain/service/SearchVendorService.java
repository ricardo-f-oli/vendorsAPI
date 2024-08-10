package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchVendorService {
    private final VendorRepository vendorRepository;
    public SearchVendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

//    public List<Vendor> findByBranch(String branchName) {
//        return vendorRepository.findByBranch(branchName);
//    }

    public List<Vendor> findByContractType(String contractType) {
        return vendorRepository.findByContractTypeEnum(ContractTypeEnum.fromValue(contractType));
    }
    public Vendor findByRegistration(String registration) {
        if (vendorRepository.findByRegistration(registration).isPresent()) {
            return vendorRepository.findByRegistration(registration).get();
        } else {
            throw new RuntimeException("Seller not found with registration: " + registration);
        }
    }
    public Optional<Vendor> findVendorById(long id) {
        return vendorRepository.findById(id);
    }
}
