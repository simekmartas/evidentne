package cz.financniporadce.crm.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "life_milestones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LifeMilestone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String type; // svatba, dítě, důchod, změna práce atd.
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private LocalDate eventDate;
    
    @Column
    private LocalDate reminderDate; // kdy připomenout
    
    @Column
    private boolean reminderSent = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
} 