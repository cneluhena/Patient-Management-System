package com.cn.authservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Auth Service";
    }
//
//    @Operation(summary="Generate token on user login")
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
//
//        Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);
//
//        if (tokenOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        String token = tokenOptional.get();
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//
//    }
//
//    @Operation(summary = "Validate Token")
//    @GetMapping("/validate")
//    public ResponseEntity<Void> validateToken(@RequestHeader("authorization") String authHeader) {
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        return authService.validateToken(authHeader.substring(7)) ? ResponseEntity.ok().build()
//                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
}
