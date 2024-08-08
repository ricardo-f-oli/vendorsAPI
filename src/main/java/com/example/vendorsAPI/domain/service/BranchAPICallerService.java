package com.example.vendorsAPI.domain.service;

import com.example.vendorsAPI.domain.entities.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class BranchAPICallerService {

    @Autowired
    private RestTemplate restTemplate;

    public Branch getBranch(String name) {
        String EXTERNAL_API_URL = "http://localhost:1080/external-api/branch/" + name;
        String url = UriComponentsBuilder.fromHttpUrl(EXTERNAL_API_URL)
                .toUriString();
        return restTemplate.getForObject(url, Branch.class);
    }
}
