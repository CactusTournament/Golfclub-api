package com.example.golfclub.controller;

import com.example.golfclub.model.Member;
import com.example.golfclub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
        @PostMapping
        public Member createMember(@RequestBody Member member) {
            return memberService.saveMember(member);
        }
    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

        @GetMapping("/search/name")
        public List<Member> searchByName(@RequestParam String name) {
            return memberService.searchByName(name);
        }

        @GetMapping("/search/email")
        public List<Member> searchByEmail(@RequestParam String email) {
            return memberService.searchByEmail(email);
        }

        @GetMapping("/search/membershipType")
        public List<Member> searchByMembershipType(@RequestParam String membershipType) {
            return memberService.searchByMembershipType(membershipType);
        }

        @GetMapping("/search/phoneNumber")
        public List<Member> searchByPhoneNumber(@RequestParam String phoneNumber) {
            return memberService.searchByPhoneNumber(phoneNumber);
        }

        @GetMapping("/search/tournamentStartDate")
        public List<Member> searchByTournamentStartDate(@RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate startDate) {
            return memberService.searchByTournamentStartDate(startDate);
        }



    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        return memberService.getMemberById(id)
                .map(existing -> {
                    // member.setId(id); // Removed: Lombok @Builder does not generate setId()
                    return ResponseEntity.ok(memberService.saveMember(member));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        if (memberService.getMemberById(id).isPresent()) {
            memberService.deleteMember(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Member> searchMembers(@RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        if (name != null) {
            return memberService.searchByName(name);
        } else if (email != null) {
            return memberService.searchByEmail(email);
        }
        return memberService.getAllMembers();
    }

    @PostMapping("/{memberId}/tournaments/{tournamentId}")
    public Member addTournament(@PathVariable Long memberId, @PathVariable Long tournamentId) {
        return memberService.addTournament(memberId, tournamentId);
    }

    @DeleteMapping("/{memberId}/tournaments/{tournamentId}")
    public Member removeTournament(@PathVariable Long memberId, @PathVariable Long tournamentId) {
        return memberService.removeTournament(memberId, tournamentId);
    }
}
