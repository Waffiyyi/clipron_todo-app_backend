package org.waffiyyidev.clipron_todoapp.service;

import org.springframework.http.ResponseEntity;
import org.waffiyyidev.clipron_todoapp.dtos.AuthResponse;
import org.waffiyyidev.clipron_todoapp.dtos.LoginRequestDTO;
import org.waffiyyidev.clipron_todoapp.entity.User;

public interface AuthService {
   ResponseEntity<AuthResponse> signup( User user);
   ResponseEntity<AuthResponse> signIn( LoginRequestDTO req);
   User findUserByJWTToken(String jwt);


}
