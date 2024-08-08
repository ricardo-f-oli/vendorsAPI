package com.example.vendorsAPI.validator;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.repository.VendorRepository;
import com.example.vendorsAPI.model.VendorRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.InputMismatchException;
import org.springframework.beans.factory.annotation.Autowired;

public class DocumentValidator implements ConstraintValidator<Document, VendorRequest> {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public void initialize(Document constraintAnnotation) {
    }

    @Override
    public boolean isValid(VendorRequest vendorRequest, ConstraintValidatorContext context) {
        if (vendorRequest.getDocument() == null || vendorRequest.getContractType() == null) {
            return false;
        }

        if(!isUnique(vendorRequest.getDocument())){
            return false;
        }

        String document = vendorRequest.getDocument();
        VendorRequest.ContractTypeEnum contractType = vendorRequest.getContractType();

        switch (contractType) {
            case CLT:
            case OUTSOURCING:
                return isValidCPF(document);
            case PJ:
                return isValidCNPJ(document);
            default:
                return false;
        }

    }

    private boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            char dig10, dig11;
            int sm, r, num, peso;

            sm = 0;
            peso = 10;
            for (int i = 0; i < 9; i++) {
                num = cpf.charAt(i) - 48;
                sm += (num * peso--);
            }

            r = 11 - (sm % 11);
            dig10 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

            sm = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                num = cpf.charAt(i) - 48;
                sm += (num * peso--);
            }

            r = 11 - (sm % 11);
            dig11 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException e) {
            return false;
        }
    }

    private boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            char dig13, dig14;
            int sm, r, num, peso;

            sm = 0;
            peso = 2;
            for (int i = 11; i >= 0; i--) {
                num = cnpj.charAt(i) - 48;
                sm += (num * peso);
                peso = (peso == 9) ? 2 : peso + 1;
            }

            r = sm % 11;
            dig13 = (r == 0 || r == 1) ? '0' : (char) ((11 - r) + 48);

            sm = 0;
            peso = 2;
            for (int i = 12; i >= 0; i--) {
                num = cnpj.charAt(i) - 48;
                sm += (num * peso);
                peso = (peso == 9) ? 2 : peso + 1;
            }

            r = sm % 11;
            dig14 = (r == 0 || r == 1) ? '0' : (char) ((11 - r) + 48);

            return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));
        } catch (InputMismatchException e) {
            return false;
        }
    }

    private boolean isUnique(String documento) {
        return !vendorRepository.existsByDocument(documento);
    }
}