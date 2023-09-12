package com.example.aplicacionwebfilmotokio.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    String username;
    String password;
}
