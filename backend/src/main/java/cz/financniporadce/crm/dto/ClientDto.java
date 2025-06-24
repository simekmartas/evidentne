package cz.financniporadce.crm.dto;

import cz.financniporadce.crm.entity.WorkflowStage;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String postalCode;
    private String notes;
    private WorkflowStage workflowStage;
    private Long advisorId;
    private String advisorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean hasNeedsAnalysis;
    private int documentsCount;
    private int meetingsCount;
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
} 