package br.com.bb.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
@Entity
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String message;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @NotNull
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @NotNull
    private User receiver;
}
