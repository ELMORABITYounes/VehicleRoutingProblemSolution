package com.elmorabit.ensak.dto;

import com.elmorabit.ensak.domain.Niveau;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor

@AllArgsConstructor
@Data
public class ExcelUserRow {
    @Email
    private String username;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Min(1000000000)
    @Max(9999999999L)
    private Long cneOrSomme;
    @NotBlank
    private String tel;
}
