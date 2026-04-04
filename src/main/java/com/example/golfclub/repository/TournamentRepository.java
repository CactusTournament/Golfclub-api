package com.example.golfclub.repository;

import com.example.golfclub.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByNameContainingIgnoreCase(String name);
    List<Tournament> findByStartDate(LocalDate startDate);
    List<Tournament> findByLocationContainingIgnoreCase(String location);
    List<Tournament> findByMembers_Id(Long memberId);
}
