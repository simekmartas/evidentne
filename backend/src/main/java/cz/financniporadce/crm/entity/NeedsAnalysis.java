package cz.financniporadce.crm.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "needs_analyses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeedsAnalysis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
    // Finanční informace
    @Column
    private BigDecimal monthlyIncome;
    
    @Column
    private BigDecimal monthlyExpenses;
    
    @Column
    private BigDecimal savings;
    
    @Column
    private BigDecimal debts;
    
    // Rodinná situace
    @Column
    private String maritalStatus;
    
    @Column
    private Integer numberOfChildren;
    
    @Column
    private Integer dependentPersons;
    
    // Cíle
    @Column(columnDefinition = "TEXT")
    private String shortTermGoals;
    
    @Column(columnDefinition = "TEXT")
    private String longTermGoals;
    
    // Rizika a pojištění
    @Column
    private boolean hasLifeInsurance;
    
    @Column
    private boolean hasHealthInsurance;
    
    @Column
    private boolean hasPropertyInsurance;
    
    @Column
    private boolean hasLiabilityInsurance;
    
    // Investice
    @Column
    private String investmentExperience;
    
    @Column
    private String riskTolerance;
    
    // Důchodové zabezpečení
    @Column
    private boolean hasPensionSavings;
    
    @Column
    private BigDecimal pensionMonthlyContribution;
    
    // Další poznámky
    @Column(columnDefinition = "TEXT")
    private String additionalNotes;
    
    @Column(nullable = false)
    private boolean completed = false;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
} 