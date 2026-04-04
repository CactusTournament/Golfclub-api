package com.example.golfclub.repository;

import java.time.LocalDate;
import com.example.golfclub.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByEmailContainingIgnoreCase(String email);
    List<Member> findByMembershipTypeContainingIgnoreCase(String membershipType);
    List<Member> findByPhoneNumberContainingIgnoreCase(String phoneNumber);
    List<Member> findByTournaments_StartDate(LocalDate startDate);
}
