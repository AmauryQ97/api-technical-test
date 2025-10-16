package com.technicaltest.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "clients")
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Birthday is mandatory")
    @Past(message = "Birthday must be in the past")
    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.PROTECTED) 
    private LocalDate birthday;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email isn't valid")
    @Column(nullable = false, unique = true)
    private String email;
    
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", 
         message = "Invalid phone number format, must follow international E.164 (ex: +33612345678)")
    private String phone;
    
    @Size(min = 3, max = 20, message = "Company identifier must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Company identifier can only contain letters, numbers and hyphens")
    @Column(name = "company_identifier", nullable = false, updatable = false)
    @Setter(AccessLevel.PROTECTED) 
    private String companyIdentifier;
    
    @NotNull(message = "Client type is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Schema(description = "Type de client")
    public enum Type {
        @Schema(description = "Client entreprise")
        COMPANY,
        @Schema(description = "Client particulier")
        PERSON
    }
}


