package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UpdateVendorService {
    private final VendorRepository vendorRepository;
    public UpdateVendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Transactional
    public Vendor updateVendor(Long id, Vendor updatedVendor) {
        Vendor existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Vendor not found with id: " + id)));

        existingVendor.setName(updatedVendor.getName());
        existingVendor.setDocument(updatedVendor.getDocument());
        existingVendor.setContractTypeEnum(updatedVendor.getContractTypeEnum());
        existingVendor.setEmail(updatedVendor.getEmail());

        return vendorRepository.save(existingVendor);
    }
}
