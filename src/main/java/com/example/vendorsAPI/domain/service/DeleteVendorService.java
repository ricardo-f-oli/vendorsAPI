package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DeleteVendorService {

    private final VendorRepository vendorRepository;

    public DeleteVendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Transactional
    public void deleteByRegistration(String registration) {
        Vendor vendor = vendorRepository.findByRegistration(registration)
                .orElseThrow(() -> new RuntimeException("Vendor Not Found"));
        vendorRepository.delete(vendor);
    }
}
