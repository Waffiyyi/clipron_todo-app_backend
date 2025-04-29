package org.waffiyyidev.clipron_todoapp.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.waffiyyidev.clipron_todoapp.config.JwtProvider;
import org.waffiyyidev.clipron_todoapp.dtos.AuthResponse;
import org.waffiyyidev.clipron_todoapp.dtos.LoginRequestDTO;
import org.waffiyyidev.clipron_todoapp.entity.TodoList;
import org.waffiyyidev.clipron_todoapp.entity.User;
import org.waffiyyidev.clipron_todoapp.exception.BadRequestException;
import org.waffiyyidev.clipron_todoapp.exception.ResourceNotFoundException;
import org.waffiyyidev.clipron_todoapp.repository.TodoListRepository;
import org.waffiyyidev.clipron_todoapp.repository.UserRepository;
import org.waffiyyidev.clipron_todoapp.service.CustomUserDetailsService;
import org.waffiyyidev.clipron_todoapp.service.AuthService;
import org.waffiyyidev.clipron_todoapp.validations.EmailValidator;
import org.waffiyyidev.clipron_todoapp.validations.PasswordValidator;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
   private final UserRepository userRepository;
   private final CustomUserDetailsService customUserDetailsService;
   private final JwtProvider jwtProvider;
   private final PasswordEncoder passwordEncoder;
   private final TodoListRepository todoListRepository;


   @Override
   public ResponseEntity<AuthResponse> signup(User user) {
      if (!EmailValidator.isValid(user.getEmail())) {
         throw new BadRequestException("Invalid email format", HttpStatus.BAD_REQUEST);
      }

      if (!PasswordValidator.isValid(user.getPassword())) {
         throw new BadRequestException("Invalid password format, password must include at " +
                                         "least one uppercase letter, one lowercase letter, and one digit ",
                                       HttpStatus.BAD_REQUEST);
      }

      User isEmailExist = userRepository.findByEmail(user.getEmail());
      if (isEmailExist != null) {
         throw new BadRequestException("Email is already used with another account",
                                       HttpStatus.BAD_REQUEST);
      }
      if (user.getUsername() == null) {
         throw new BadRequestException("Please enter your username",
                                       HttpStatus.BAD_REQUEST);

      }
      User createdUser = User.builder()
                             .username(user.getUsername())
                             .email(user.getEmail())
                             .password(passwordEncoder.encode(user.getPassword()))
                             .build();

      User savedUser = userRepository.save(createdUser);

      TodoList todoList = new TodoList();

      todoList.setName("General");
      todoList.setUser(savedUser);
      TodoList savedTodoList = todoListRepository.save(todoList);


      UserDetails userDetails = customUserDetailsService.loadUserByUsername(
        savedUser.getEmail());

      Authentication authentication = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = jwtProvider.generateToken(authentication);

      AuthResponse response = AuthResponse.builder()
                                          .jwt(jwt)
                                          .message("Signup success")
                                          .user(savedUser)
                                          .generalTodoListId(savedTodoList.getId())
                                          .build();
      return new ResponseEntity<>(response, HttpStatus.CREATED);
   }

   @Override
   public ResponseEntity<AuthResponse> signIn(LoginRequestDTO req) {
      String username = req.getUsername();
      String password = req.getPassword();

      User user = userRepository.findByEmail(username);

      if (user == null) {
         throw new UsernameNotFoundException("User not found with email " + username);
      }

      TodoList todoList =
        todoListRepository.findByUserAndName(user, "General").orElseThrow(
          () -> new ResourceNotFoundException("Todo List Not Found", HttpStatus.NOT_FOUND));


      Authentication authentication = authenticate(username, password);

      String jwt = jwtProvider.generateToken(authentication);

      AuthResponse response = AuthResponse.builder()
                                          .jwt(jwt)
                                          .message("Login success")
                                          .user(user)
                                          .generalTodoListId(todoList.getId())
                                          .build();
      return new ResponseEntity<>(response, HttpStatus.OK);
   }


   private Authentication authenticate(String username, String password) {
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

      if (userDetails == null) {
         throw new BadCredentialsException("Invalid username....");
      }
      if (!passwordEncoder.matches(password, userDetails.getPassword())) {
         throw new BadCredentialsException("Invalid password....");
      }
      return new UsernamePasswordAuthenticationToken(userDetails, null,
                                                     userDetails.getAuthorities());
   }

   @Override
   public User findUserByJWTToken(String jwt) {
      String email = jwtProvider.getEmailFromJwtToken(jwt);
      return userRepository.findByEmail(email);
   }
}
