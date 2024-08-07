package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.repository.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteVendorService {

    private final VendorRepository vendorRepository;

    public DeleteVendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public void delete(String registration) {
        if (vendorRepository.findByRegistration(registration).isPresent()) {
            vendorRepository.deleteByRegistration(registration);
        } else {
            throw new RuntimeException("Seller not found with registration: " + registration);
        }
    }
}
