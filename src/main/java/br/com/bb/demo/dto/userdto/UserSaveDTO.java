package br.com.bb.demo.dto.userdto;

import br.com.bb.demo.model.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserSaveDTO implements UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;

    @Override
    public User toUser() {
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
