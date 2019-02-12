package com.elmorabit.ensak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor

@AllArgsConstructor
@Data
public class RegisterForm {
    @Email
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatedPassword;
    @NotBlank
    private String type;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Min(1000000000)
    @Max(9999999999L)
    private Long cneOrSomme;
    @Min(1)
    private Long niveauOrDepartement;
}
