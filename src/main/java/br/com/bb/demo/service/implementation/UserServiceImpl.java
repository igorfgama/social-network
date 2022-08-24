package br.com.bb.demo.service.implementation;

import br.com.bb.demo.dto.userdto.UserDTO;
import br.com.bb.demo.exception.ValidationException;
import br.com.bb.demo.model.User;
import br.com.bb.demo.repository.UserRepository;
import br.com.bb.demo.service.UserService;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User save(UserDTO userDTO) {
        return userRepository.save(userDTO.toUser());
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ValidationException("Usuário com id " + userId + " não encontrado."));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
