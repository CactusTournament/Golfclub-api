package com.example.golfclub.service;

import com.example.golfclub.model.Tournament;
import com.example.golfclub.model.Member;
import com.example.golfclub.repository.TournamentRepository;
import com.example.golfclub.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private MemberRepository memberRepository;

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public Tournament saveTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        if (tournament != null) {
            // Remove this tournament from all members
            for (Member member : tournament.getMembers()) {
                member.getTournaments().remove(tournament);
            }
            tournament.getMembers().clear();
            tournamentRepository.delete(tournament);
        }
    }


    public List<Tournament> searchByName(String name) {
        return tournamentRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Tournament> searchByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public List<Tournament> searchByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Tournament> searchByMember(Long memberId) {
        return tournamentRepository.findByMembers_Id(memberId);
    }

    public Tournament addMember(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();
        tournament.getMembers().add(member);
        return tournamentRepository.save(tournament);
    }

    public Tournament removeMember(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow();
        Member member = memberRepository.findById(memberId).orElseThrow();
        tournament.getMembers().remove(member);
        return tournamentRepository.save(tournament);
    }
}
