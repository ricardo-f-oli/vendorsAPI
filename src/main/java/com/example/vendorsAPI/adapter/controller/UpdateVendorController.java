package com.example.vendorsAPI.adapter.controller;

import com.example.vendorsAPI.adapter.controller.mapper.VendorMapper;
import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import com.example.vendorsAPI.domain.service.UpdateVendorService;
import com.example.vendorsAPI.model.VendorRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class UpdateVendorController {
    private final UpdateVendorService updateVendorService;
    private final VendorRepository vendorRepository;

    public UpdateVendorController(UpdateVendorService updateVendorService, VendorRepository vendorRepository) {
        this.updateVendorService = updateVendorService;
        this.vendorRepository = vendorRepository;
    }

    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable Long id, @RequestBody VendorRequest vendorRequest) {
        Vendor vendor = VendorMapper.toDto(vendorRequest);
        return updateVendorService.updateVendor(id, vendor);
    }
}
