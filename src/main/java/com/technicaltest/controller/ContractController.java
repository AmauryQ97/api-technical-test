package com.technicaltest.controller;

import com.technicaltest.model.Contract;
import com.technicaltest.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
@Validated
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contract createContract(
            @Valid @RequestBody Contract contract) {
        return contractService.create(contract);
    }

    @PutMapping("/{contractId}")
    public Contract updateContract(
            @PathVariable Long contractId,
            @Valid @RequestBody Contract contract) {
        return contractService.update(contractId, contract);
    }

    @GetMapping("/{contractId}")
    public Contract getContract(
            @PathVariable Long contractId) {
        return contractService.getContract(contractId);
    }

    @GetMapping("/active/{clientId}")
    public List<Contract> getClientActiveContracts(
            @PathVariable Long clientId,
            @RequestParam(required = false) LocalDate lastModifiedDate) {
        return contractService.getClientActiveContracts(clientId, lastModifiedDate);
    }

    @GetMapping("/active/cost-sum/{clientId}")
    public BigDecimal getClientTotalCostActiveContracts(
            @PathVariable Long clientId) {
        return contractService.getClientTotalCostActiveContracts(clientId);
    }
}
