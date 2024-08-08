package com.example.vendorsAPI.domain.repository;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Optional<Vendor> findByRegistration(String registration);

    void deleteByRegistration(String registration);

//    List<Vendor> findByBranch(String branchName);

    List<Vendor> findByContractTypeEnum(ContractTypeEnum contractType);

    boolean existsByDocument(String document);

    boolean existsByEmail(String email);
}
