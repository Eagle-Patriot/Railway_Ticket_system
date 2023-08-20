package com.mainproject.demo;

import lombok.*;

@Getter
@AllArgsConstructor
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
