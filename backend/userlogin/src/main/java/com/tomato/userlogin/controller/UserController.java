package com.tomato.userlogin.controller;

import com.tomato.userlogin.exception.EmailAlreadyExistException;
import com.tomato.userlogin.exception.ResourceNotFoundException;
import com.tomato.userlogin.model.UserData;
import com.tomato.userlogin.repository.UserRepository;
import com.tomato.userlogin.service.SequenceGeneratorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @PostMapping("/register")
    public UserData createUser(@Valid @RequestBody UserData userData) {
        userData.setId(sequenceGeneratorService.generateSequence(UserData.SEQUENCE_NAME));
        Optional<UserData> optionalUserData = userRepository.findByEmail(userData.getEmail());
        if(optionalUserData.isPresent()) {
            LOGGER.error("Email Already Exists");
            throw new EmailAlreadyExistException("Email Already Exist");
        }
        LOGGER.info("User Details Stored");
        return userRepository.save(userData);
    }

    @GetMapping("/getUser/{email}")
    public ResponseEntity<UserData> getUserDetails(@PathVariable(value = "email") String email) throws ResourceNotFoundException {
        UserData userData = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        LOGGER.info("User Details Retrieved");
        return ResponseEntity.ok().body(userData);

    }
}
