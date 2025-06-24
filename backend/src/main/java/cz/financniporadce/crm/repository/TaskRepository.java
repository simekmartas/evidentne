package cz.financniporadce.crm.repository;

import cz.financniporadce.crm.entity.Task;
import cz.financniporadce.crm.entity.User;
import cz.financniporadce.crm.entity.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedTo(User user);
    List<Task> findByCreatedBy(User user);
    List<Task> findByAssignedToAndCompletedFalse(User user);
    List<Task> findByAssignedToAndPriority(User user, TaskPriority priority);
    
    @Query("SELECT t FROM Task t WHERE t.assignedTo = :user AND t.dueDate <= :date AND t.completed = false")
    List<Task> findOverdueTasks(@Param("user") User user, @Param("date") LocalDate date);
    
    @Query("SELECT t FROM Task t WHERE t.dueDate <= :date AND t.completed = false")
    List<Task> findAllOverdueTasks(@Param("date") LocalDate date);
} 