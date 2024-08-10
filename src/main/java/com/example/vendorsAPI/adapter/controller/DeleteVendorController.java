package com.example.vendorsAPI.adapter.controller;

import com.example.vendorsAPI.domain.service.DeleteVendorService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class DeleteVendorController {
    private final DeleteVendorService deleteVendorService;

    public DeleteVendorController(DeleteVendorService deleteVendorService) {
        this.deleteVendorService = deleteVendorService;
    }

    @DeleteMapping("/vendor/{registration}")
    public void deleteVendor(@PathVariable String registration) {
        deleteVendorService.deleteByRegistration(registration);
    }
}
