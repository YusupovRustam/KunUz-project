package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.dto.Registrationdto;
import com.company.dto.auth.AuthorizationDTO;
import com.company.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthorizationDTO dto) {
        ProfileDTO response = authService.authorization(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody Registrationdto dto) {
        authService.registration(dto);
//        ProfileDTO response = authService.authorization(dto);
//        return ResponseEntity.ok(response);
        return ResponseEntity.ok("successfully!!");
    }

    @GetMapping("/verification/{id}")
public ResponseEntity<ProfileDTO>verification(@PathVariable Integer id){
        authService.verification(id);
        return ResponseEntity.ok().build();
    }
}
