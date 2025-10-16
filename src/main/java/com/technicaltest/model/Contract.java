package com.technicaltest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "contracts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contract {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;

    // Optimize filtering by clientId without loading the whole object
    @Column(name = "client_id", insertable = false, updatable = false)
    private Long clientId;
    
    @Column(name = "cost_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal costAmount;
    
    @Column(name = "start_date", nullable = false, updatable = false)
    @PastOrPresent(message = "Start date must be today or in the past")
    private LocalDateTime startDate = LocalDateTime.now();
    
    @Column(name = "end_date", nullable=true)
    private LocalDateTime endDate;
    
    @Column(name = "last_modified_date", nullable = false)
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

}