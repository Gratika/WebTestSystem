package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.Set;

@Entity @Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NonNull
    @Column(name = "login", nullable = false,unique = true)
    private String login;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @NonNull
    @Column(name = "is_active", columnDefinition = "integer default 1")
    private Boolean isActive;

    @Nullable
    @Column(name = "name", nullable = true)
    private String name;

    @Nullable
    @Column(name = "surname", nullable = true)
    private String surname;

    @Nullable
    @Column(name = "birthday", nullable = true)
    private Date birthday;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> RoleSet;

    @OneToMany(mappedBy = "owner")
    private Set<Test> testSet;

}
