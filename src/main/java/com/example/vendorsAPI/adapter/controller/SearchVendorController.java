package com.example.vendorsAPI.adapter.controller;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.service.SearchVendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class SearchVendorController {
    private final SearchVendorService searchVendorService;

    public SearchVendorController(SearchVendorService searchVendorService) {
        this.searchVendorService = searchVendorService;
    }

//    @GetMapping("/vendor/findByBranch")
//    public List<Vendor> findByBranch(@RequestParam String branchName) {
//        return searchVendorService.findByBranch(branchName);
//    }

    @GetMapping("/vendor/findByContractType")
    public List<Vendor> findByContractType(@RequestParam String contractType) {
        return searchVendorService.findByContractType(contractType);
    }

    @GetMapping("/vendor/{registration}")
    public Vendor findByRegistration(@PathVariable String registration) {
        return searchVendorService.findByRegistration(registration);
    }

}
