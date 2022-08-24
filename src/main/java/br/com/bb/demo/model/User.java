package br.com.bb.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Builder
public class User {
    @Id @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ToString.Include
    @Column(name = "name", length = 100)
    private String name;
    @ToString.Include @Email
    @Column(name = "email", length = 50)
    private String email;
    @ToString.Include
    @Column(name = "password", length = 20)
    private String password;
}
