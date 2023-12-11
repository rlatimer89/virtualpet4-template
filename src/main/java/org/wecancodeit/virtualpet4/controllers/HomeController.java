package org.wecancodeit.virtualpet4.controllers;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.virtualpet4.models.UserModel;
import org.wecancodeit.virtualpet4.repositories.UserRepository;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class HomeController extends BaseController {

    @Resource
    private UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@RequestBody UserModel model, HttpServletResponse response) {
        if (this.login(model.getUserId(), model.getPassword(), response)) {
            return ResponseEntity.ok("Login Complete");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid login");
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletResponse response) {
        this.logout(response);
        return ResponseEntity.ok("Logout complete");
    }

    @GetMapping()
    public String getMethodName() {
        return "Hello World";
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok(userRepository.findAll());
    }

}
