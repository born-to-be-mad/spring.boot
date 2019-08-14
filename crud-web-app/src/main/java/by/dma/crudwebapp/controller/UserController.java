package by.dma.crudwebapp.controller;

import by.dma.crudwebapp.business.UserService;
import by.dma.crudwebapp.dto.UserRequestDTO;
import by.dma.crudwebapp.model.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User API")
/*@CrossOrigin(origins = "http://localhost:4100")*/
//The annotation enables Cross-Origin Resource Sharing (CORS) on the server. This step isn’t always necessary.
// Since we are deploying our Angular frontend to http://localhost:4100 and our Boot backend to http://localhost:8100,
// the browser would otherwise deny requests from one to the other.
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

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(service.update(userId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long userId) {
        service.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(service.getById(userId));
    }

}
