package com.example.golfclub.service;

import com.example.golfclub.model.Member;
import com.example.golfclub.model.Tournament;
import com.example.golfclub.repository.MemberRepository;
import com.example.golfclub.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member != null) {
            // Remove this member from all tournaments
            for (Tournament tournament : member.getTournaments()) {
                tournament.getMembers().remove(member);
            }
            member.getTournaments().clear();
            memberRepository.delete(member);
        }
    }


    public List<Member> searchByName(String name) {
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Member> searchByEmail(String email) {
        return memberRepository.findByEmailContainingIgnoreCase(email);
    }

    public List<Member> searchByMembershipType(String membershipType) {
        return memberRepository.findByMembershipTypeContainingIgnoreCase(membershipType);
    }

    public List<Member> searchByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumberContainingIgnoreCase(phoneNumber);
    }

    public List<Member> searchByTournamentStartDate(java.time.LocalDate startDate) {
        return memberRepository.findByTournaments_StartDate(startDate);
    }

    public Member addTournament(Long memberId, Long tournamentId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow();
        member.getTournaments().add(tournament);
        return memberRepository.save(member);
    }

    public Member removeTournament(Long memberId, Long tournamentId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow();
        member.getTournaments().remove(tournament);
        return memberRepository.save(member);
    }
}
