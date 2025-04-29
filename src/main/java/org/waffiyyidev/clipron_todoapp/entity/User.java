package org.waffiyyidev.clipron_todoapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Schema(accessMode = Schema.AccessMode.READ_ONLY)
   private Long id;

   private String username;
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
   private String password;
   private String email;
}
