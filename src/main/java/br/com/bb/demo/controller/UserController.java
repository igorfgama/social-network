package br.com.bb.demo.controller;

import br.com.bb.demo.dto.userdto.UserSaveDTO;
import br.com.bb.demo.exception.ValidationException;
import br.com.bb.demo.model.User;
import br.com.bb.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Criar ou editar um usuário")
    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid @NotNull UserSaveDTO userDTO) {
        try {
            log.debug("Got a request from user " + userDTO.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDTO));
        } catch (ValidationException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Operation(summary = "Listar todos usuários")
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @Operation(summary = "Encontrar usuário por ID")
    @GetMapping("/{id}")
    public User getOne(@PathParam("id") Long id) {
        return userService.getById(id);
    }
}