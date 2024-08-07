package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateVendorService {
    private final VendorRepository vendorRepository;

    public CreateVendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    public Vendor createVendor(Vendor vendor) {
        Vendor vendorc = new Vendor();
        return vendorc;
    }
}
