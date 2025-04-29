package org.waffiyyidev.clipron_todoapp.dtos;

import lombok.Builder;
import lombok.Data;
import org.waffiyyidev.clipron_todoapp.entity.User;

@Data
@Builder
public class AuthResponse {
    private String jwt;
    private User user;
    private String message;
    private Long generalTodoListId;
}
