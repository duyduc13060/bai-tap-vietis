package com.example.baitapvietis.model.entity;

import com.example.baitapvietis.contants.RoleEnum;
import lombok.*;
import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "create_date")
    private String dateOfBrithday;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
