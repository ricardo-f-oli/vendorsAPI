package com.example.vendorsAPI.adapter.controller;

import com.example.vendorsAPI.domain.entities.Branch;
import com.example.vendorsAPI.domain.service.BranchAPICallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
public class BranchAPICallerController {

    @Autowired
    private BranchAPICallerService branchAPICallerService;

    @GetMapping("/branch")
    public Branch getBranch(String branchName) {
        return branchAPICallerService.getBranch(branchName);
    }
}
