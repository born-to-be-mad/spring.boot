package by.dma.crudwebapp.controller.controller;

import by.dma.crudwebapp.controller.business.UserService;
import by.dma.crudwebapp.controller.dto.UserRequestDTO;
import by.dma.crudwebapp.controller.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    ResponseEntity<Void> createUser(@Valid @RequestBody UserRequestDTO request, UriComponentsBuilder builder) {
        Long entityId = service.create(request);

        URI uri = builder.path("/api/users/{id}").buildAndExpand(entityId).toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<User>> getAllCards() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getCardById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(service.getById(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(service.update(userId, request));
    }
}
