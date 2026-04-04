package com.example.golfclub.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private Double entryFee;
    private Double cashPrizeAmount;

    @ManyToMany(mappedBy = "tournaments", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Member> members = new HashSet<>();

    public Set<Member> getMembers() {
        return members;
    }
}
