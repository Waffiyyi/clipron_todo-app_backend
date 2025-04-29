package org.waffiyyidev.clipron_todoapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.waffiyyidev.clipron_todoapp.entity.User;
import org.waffiyyidev.clipron_todoapp.service.AuthService;

@RestController
@RequestMapping("/api/vi/user")
@RequiredArgsConstructor
public class UserController {
   private final AuthService authService;

   @GetMapping("/profile")
   public User profile(@RequestHeader("Authorization") String jwt) {
      return authService.findUserByJWTToken(jwt);
   }
}
