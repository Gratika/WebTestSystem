package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity @Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    Long id;

    @NonNull
    @Column(name = "login", nullable = false,unique = true)
    String login;

    @NonNull
    @Column(name = "password", nullable = false)
    String password;

    @NonNull
    @Column(name = "email", nullable = false,unique = true)
    String email;

    @NonNull
    @Column(name = "is_active", columnDefinition = "integer default 1")
    Boolean isActive;

    @Nullable
    @Column(name = "name", nullable = true)
    String name;

    @Nullable
    @Column(name = "surname", nullable = true)
    String surname;

    @Nullable
    @Column(name = "birthday", nullable = true)
    Date birthday;

}
