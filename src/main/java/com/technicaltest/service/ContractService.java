package com.technicaltest.service;

import com.technicaltest.exception.ResourceNotFoundException;
import com.technicaltest.model.Contract;
import com.technicaltest.repository.ContractRepository;
import com.technicaltest.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;

    public Contract create(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract update(Long id, Contract newContract) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + id));
        
        if (newContract.getEndDate() != null) {
            if (newContract.getEndDate().isBefore(contract.getStartDate())) {
                throw new IllegalArgumentException("End date must be after start date");
            }
        }
        contract.setEndDate(newContract.getEndDate());
        contract.setCostAmount(newContract.getCostAmount());
        
        return contractRepository.save(contract);
    }

    public List<Contract> getClientActiveContracts(Long clientId, LocalDate lastModifiedDate) {
        List<Contract> activeContracts = this.getClientActiveContracts(clientId);
        
        if (!activeContracts.isEmpty() && lastModifiedDate != null) {
            activeContracts = activeContracts.stream()
                .filter(contract -> contract.getLastModifiedDate().toLocalDate().isEqual(lastModifiedDate))
                .toList();
        }
        
        return activeContracts;
    }

    public BigDecimal getClientTotalCostActiveContracts(Long clientId) {
        List<Contract> activeContracts = this.getClientActiveContracts(clientId);

        return activeContracts.stream()
            .map(Contract::getCostAmount)
            .filter(Objects::nonNull)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Contract> getClientActiveContracts(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Client not found with id: " + clientId);
        }

        return contractRepository.findActiveByClientId(clientId);
    }

    public Contract getContract(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found with id: " + id));
    }
}
