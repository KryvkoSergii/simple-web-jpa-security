package ua.ksa.didactic.simplewebjpasecurity.core.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.ksa.didactic.simplewebjpasecurity.core.dao.UserRepository;
import ua.ksa.didactic.simplewebjpasecurity.core.model.User;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserController {
    public static final String URL = "/users";
    private final UserRepository users;

    public UserController(UserRepository users) {
        this.users = users;
    }

    @GetMapping(path = URL)
    public ResponseEntity getUser(@PathVariable(name = "id", required = false) UUID id) {
        if (Objects.nonNull(id)) return ResponseEntity.ok(users.findById(id));
        return ResponseEntity.ok(StreamSupport
                .stream(users.findAll().spliterator(), false)
                .collect(Collectors.toList()));
    }

    @PostMapping(path = URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(users.save(user));
    }

}
