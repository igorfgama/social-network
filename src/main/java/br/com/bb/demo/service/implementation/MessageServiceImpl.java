package br.com.bb.demo.service.implementation;

import br.com.bb.demo.dto.messagedto.MessageDTO;
import br.com.bb.demo.exception.ValidationException;
import br.com.bb.demo.model.Message;
import br.com.bb.demo.repository.MessageRepository;
import br.com.bb.demo.service.MessageService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    @Override
    public Message save(MessageDTO messageDTO) {
        return messageRepository.save(messageDTO.toMessage());
    }

    @Override
    public Message getById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new ValidationException("Mensagem com id " + messageId + " não encontrada."));
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long messageId) {
        messageRepository.deleteById(messageId);
    }
}