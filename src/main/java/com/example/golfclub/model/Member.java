package com.example.golfclub.model;

import jakarta.persistence.*;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String membershipType;
    private String phoneNumber;
    private java.time.LocalDate membershipStartDate;
    private Integer membershipDurationMonths;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "member_tournament",
        joinColumns = @JoinColumn(name = "member_id"),
        inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )

    private Set<Tournament> tournaments = new HashSet<>();

    public Set<Tournament> getTournaments() {
        return tournaments;
    }
}
