package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateVendorService {
    private final VendorRepository vendorRepository;
    public UpdateVendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor updateVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}
