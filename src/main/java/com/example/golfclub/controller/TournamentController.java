package com.example.golfclub.controller;

import com.example.golfclub.model.Tournament;
import com.example.golfclub.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

        @GetMapping("/search/name")
        public List<Tournament> searchByName(@RequestParam String name) {
            return tournamentService.searchByName(name);
        }

        @GetMapping("/search/startDate")
        public List<Tournament> searchByStartDate(@RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate startDate) {
            return tournamentService.searchByStartDate(startDate);
        }

        @GetMapping("/search/location")
        public List<Tournament> searchByLocation(@RequestParam String location) {
            return tournamentService.searchByLocation(location);
        }

        @GetMapping("/search/member")
        public List<Tournament> searchByMember(@RequestParam Long memberId) {
            return tournamentService.searchByMember(memberId);
        }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.saveTournament(tournament);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return tournamentService.getTournamentById(id)
                .map(existing -> {
                    // tournament.setId(id); // Removed: Lombok @Builder does not generate setId()
                    return ResponseEntity.ok(tournamentService.saveTournament(tournament));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        if (tournamentService.getTournamentById(id).isPresent()) {
            tournamentService.deleteTournament(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Removed old /search endpoint to avoid calling non-existent searchByDate. Use the new /search/startDate, /search/name, etc. endpoints instead.

    @PostMapping("/{tournamentId}/members/{memberId}")
    public Tournament addMember(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        return tournamentService.addMember(tournamentId, memberId);
    }

    @DeleteMapping("/{tournamentId}/members/{memberId}")
    public Tournament removeMember(@PathVariable Long tournamentId, @PathVariable Long memberId) {
        return tournamentService.removeMember(tournamentId, memberId);
    }
}
