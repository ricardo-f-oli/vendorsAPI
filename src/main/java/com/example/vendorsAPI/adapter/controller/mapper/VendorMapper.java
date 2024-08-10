package com.example.vendorsAPI.adapter.controller.mapper;

import com.example.vendorsAPI.domain.entities.Vendor;
import com.example.vendorsAPI.domain.entities.enums.ContractTypeEnum;
import com.example.vendorsAPI.exceptions.InvalidDocumentException;
import com.example.vendorsAPI.exceptions.InvalidEmailException;
import com.example.vendorsAPI.model.VendorRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class VendorMapper {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static Vendor toDto(VendorRequest vendorRequest) {
        Vendor vendorDto = new Vendor();
        vendorDto.setName(vendorRequest.getName());
        try {
            vendorDto.setBirthday(dateFormat.parse(vendorRequest.getBirthday()));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd-MM-yyyy");
        }
        if (!isValidEmail(vendorRequest.getEmail())) {
            throw new InvalidEmailException("O email fornecido é inválido.");
        }
        vendorDto.setEmail(vendorRequest.getEmail());
        isValid(vendorRequest);
        vendorDto.setDocument(vendorRequest.getDocument());
        vendorDto.setContractTypeEnum(toContractTypeEnum(vendorRequest.getContractType()));
        return vendorDto;
    }

    public static ContractTypeEnum toContractTypeEnum(VendorRequest.ContractTypeEnum contractTypeEnum) {
        return ContractTypeEnum.valueOf(contractTypeEnum.name());
    }

    public static void isValid(VendorRequest vendorRequest) {
        String document = vendorRequest.getDocument();
        VendorRequest.ContractTypeEnum contractType = vendorRequest.getContractType();

        switch (contractType) {
            case CLT, OUTSOURCING -> isValidCPF(document);
            case PJ -> isValidCNPJ(document);
            default -> {
            }
        }

    }

    public static void isValidCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}"))
            throw new InvalidDocumentException("CPF deve ser somente para CLT ou OUTSOURCING!");

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

        } catch (InputMismatchException e) {
            throw new InvalidDocumentException("CPF deve ser somente para CLT ou OUTSOURCING!");
        }
    }

    public static void isValidCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}"))
            throw new InvalidDocumentException("CNPJ deve ser somente para PJ!");

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

        } catch (InputMismatchException e) {
            throw new InvalidDocumentException("CNPJ deve ser somente para PJ!");
        }
    }


    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}