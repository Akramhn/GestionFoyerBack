package com.example.esprit.gestionfoyerback.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {

  String accessToken;
  String refreshToken;
  User userDetails;

}
