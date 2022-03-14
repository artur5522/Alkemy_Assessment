package com.restApi.security.jwt.Api.Controllers;

import com.restApi.security.jwt.Api.dto.LoginDTO;
import com.restApi.security.jwt.Api.dto.RegisterDTO;
import com.restApi.security.jwt.Api.entities.Rol;
import com.restApi.security.jwt.Api.entities.Users;
import com.restApi.security.jwt.Api.Helpers.ObjectResponse;
import com.restApi.security.jwt.Api.repository.RolRepository;
import com.restApi.security.jwt.Api.repository.UserRepository;
import com.restApi.security.jwt.Api.security.JWTAuthResonseDTO;
import com.restApi.security.jwt.Api.security.JwtTokenProvider;
import com.restApi.security.jwt.Api.service.EmailService;
import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/auth")
public class AuthController {

    //been in SecurityConfig
    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.status(HttpStatus.OK).body(new JWTAuthResonseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registroDTO) throws IOException {
        ObjectResponse response;
        //check is there is an user or email with same name
        if (userRepository.existsByUsername(registroDTO.getUsername())) {
            response = new ObjectResponse("User name already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (userRepository.existsByEmail(registroDTO.getEmail())) {
            response = new ObjectResponse("User email already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Users user = new Users();
        user.setName(registroDTO.getName());
        user.setUsername(registroDTO.getUsername());
        user.setEmail(registroDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        //the new user has a ROLE_USER unless the admin says otherwise
        Rol rols = rolRepository.findByName("ROLE_USER").get();
        user.setRols(Collections.singleton(rols));

        userRepository.save(user);
        
        //send welcome email
        emailService.sendEmail(registroDTO.getEmail());
        String responseString = "User registered correctly. UserName->".concat(user.getUsername()).concat(", user email-> ").concat(user.getEmail()).concat(". An email has been sent to you, make sure you check your spam folder");
        response = new ObjectResponse(responseString);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
