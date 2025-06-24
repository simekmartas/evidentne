package cz.financniporadce.crm.repository;

import cz.financniporadce.crm.entity.Meeting;
import cz.financniporadce.crm.entity.User;
import cz.financniporadce.crm.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByUser(User user);
    List<Meeting> findByClient(Client client);
    List<Meeting> findByUserAndStartTimeBetween(User user, LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT m FROM Meeting m WHERE m.user = :user AND m.startTime >= :from ORDER BY m.startTime")
    List<Meeting> findUpcomingMeetings(@Param("user") User user, @Param("from") LocalDateTime from);
    
    @Query("SELECT m FROM Meeting m WHERE m.startTime >= :from ORDER BY m.startTime")
    List<Meeting> findAllUpcomingMeetings(@Param("from") LocalDateTime from);
} 