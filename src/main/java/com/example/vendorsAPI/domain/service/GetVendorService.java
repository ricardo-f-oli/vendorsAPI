package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetVendorService {
    private final VendorRepository vendorRepository;
    public GetVendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }
    public Vendor findById(int id) {
        return new Vendor();
    }
}
