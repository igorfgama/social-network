package br.com.bb.demo.dto.messagedto;

import br.com.bb.demo.model.Message;
import br.com.bb.demo.model.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MessageSaveDTO implements MessageDTO {
    private Long id;
    private String message;
    private User sender;
    private User receiver;

    @Override
    public Message toMessage() {
        return Message.builder()
                .id(id)
                .message(message)
                .sender(sender)
                .receiver(receiver)
                .build();
    }
}