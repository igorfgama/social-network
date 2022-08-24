package br.com.bb.demo.service;

import br.com.bb.demo.dto.userdto.UserDTO;
import br.com.bb.demo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends SNService<User, UserDTO, Long> {
}