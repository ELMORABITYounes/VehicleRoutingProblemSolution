package com.elmorabit.ensak.dto;

import com.elmorabit.ensak.domain.Departement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImportProfesseurList {
    @NotNull
    private ArrayList<ExcelUserRow> rows;
    @NotNull
    private Departement departement;
}
