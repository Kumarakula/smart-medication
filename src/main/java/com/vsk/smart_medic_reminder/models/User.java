package com.vsk.smart_medic_reminder.models;


import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "users")  // 'user' is a reserved keyword in some DBs
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // One user can have many medicines
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Medicine> medicines;

    public enum Role {
        USER,
        ADMIN
    }
}

