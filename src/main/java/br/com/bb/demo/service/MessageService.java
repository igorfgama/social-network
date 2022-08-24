package br.com.bb.demo.service;

import br.com.bb.demo.dto.messagedto.MessageDTO;
import br.com.bb.demo.model.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService extends SNService<Message, MessageDTO, Long> {
}