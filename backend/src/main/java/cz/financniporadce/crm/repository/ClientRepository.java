package cz.financniporadce.crm.repository;

import cz.financniporadce.crm.entity.Client;
import cz.financniporadce.crm.entity.User;
import cz.financniporadce.crm.entity.WorkflowStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByAdvisor(User advisor);
    List<Client> findByWorkflowStage(WorkflowStage stage);
    List<Client> findByAdvisorAndWorkflowStage(User advisor, WorkflowStage stage);
    
    @Query("SELECT c FROM Client c WHERE " +
           "(LOWER(c.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Client> searchClients(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT c FROM Client c WHERE c.advisor = :advisor AND " +
           "(LOWER(c.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Client> searchClientsByAdvisor(@Param("advisor") User advisor, @Param("searchTerm") String searchTerm);
} 