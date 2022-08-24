package br.com.bb.demo.controller;

import br.com.bb.demo.dto.messagedto.MessageDTO;
import br.com.bb.demo.dto.messagedto.MessageSaveDTO;
import br.com.bb.demo.exception.ValidationException;
import br.com.bb.demo.model.Message;
import br.com.bb.demo.service.MessageService;
import br.com.bb.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    //private final ModelMapper modelMapper;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
        //this.modelMapper = modelMapper;
    }

    @Operation(summary = "Criar ou editar uma mensagem")
    @PostMapping("/{senderId}/{receiverId}")
    public ResponseEntity<Message> save(@PathParam("senderId") Long senderId,
                                        @PathParam("receiverId") Long receiverId,
                                        @RequestBody @Valid String message) {
        try {
            //if(Objects.equals(senderId, receiverId)) {
            //    throw new ValidationException("Não pode enviar mensagem a si mesmo.");
            //}

            //MessageSaveDTO messageDTO = modelMapper.ma;
            MessageSaveDTO messageDTO = MessageSaveDTO.builder()
                    .message(message)
                            .sender(userService.getById(senderId))
                                    .receiver(userService.getById(receiverId))
                                            .build();
            log.debug("Got a request from message " + messageDTO.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(messageService.save(messageDTO));
        } catch (ValidationException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Operation(summary = "Listar todas as mensagens")
    @GetMapping
    public List<Message> getAll() {
        return messageService.getAll();
    }
}