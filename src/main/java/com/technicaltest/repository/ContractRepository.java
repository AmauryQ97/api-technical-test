package com.technicaltest.repository;

import com.technicaltest.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByClientId(Long clientId);
    
    @Query("SELECT c FROM Contract c WHERE c.clientId = :clientId AND c.endDate > CURRENT_DATE")
    List<Contract> findActiveByClientId(@Param("clientId") Long clientId);
}
