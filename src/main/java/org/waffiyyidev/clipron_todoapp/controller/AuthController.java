package org.waffiyyidev.clipron_todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.waffiyyidev.clipron_todoapp.dtos.AuthResponse;
import org.waffiyyidev.clipron_todoapp.dtos.LoginRequestDTO;
import org.waffiyyidev.clipron_todoapp.entity.User;
import org.waffiyyidev.clipron_todoapp.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
   private final AuthService authService;

   @PostMapping("/signup")
   public ResponseEntity<AuthResponse> signup(@RequestBody User user) {
      return authService.signup(user);
   }

   @PostMapping("/signin")
   public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequestDTO req) {
      return authService.signIn(req);
   }


}
