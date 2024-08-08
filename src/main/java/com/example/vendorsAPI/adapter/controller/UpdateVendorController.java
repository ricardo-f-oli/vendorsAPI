package com.example.vendorsAPI.adapter.controller;

import com.example.vendorsAPI.adapter.controller.mapper.VendorMapper;
import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.service.UpdateVendorService;
import com.example.vendorsAPI.model.VendorRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class UpdateVendorController {
    private final UpdateVendorService updateVendorService;

    public UpdateVendorController(UpdateVendorService updateVendorService) {
        this.updateVendorService = updateVendorService;
    }

    @PutMapping("/vendor")
    public Vendor updateVendor(@Valid @RequestBody VendorRequest vendorRequest) {
        Vendor vendor = VendorMapper.toDto(vendorRequest);
        return updateVendorService.updateVendor(vendor);
    }
}
