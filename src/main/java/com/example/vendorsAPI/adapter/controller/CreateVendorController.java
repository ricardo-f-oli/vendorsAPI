package com.example.vendorsAPI.adapter.controller;


import com.example.vendorsAPI.adapter.controller.mapper.VendorMapper;
import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.service.CreateVendorService;
import com.example.vendorsAPI.model.VendorRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class CreateVendorController {
    private final CreateVendorService createVendorService;

    public CreateVendorController(CreateVendorService createVendorService) {
        this.createVendorService = createVendorService;
    }
    @PostMapping("/vendor")
    public Vendor CreateVendor(@Valid @RequestBody VendorRequest vendorRequest){
        Vendor vendor = VendorMapper.toDto(vendorRequest);
        return createVendorService.createVendor(vendor, vendorRequest.getBranch());
    }

}
