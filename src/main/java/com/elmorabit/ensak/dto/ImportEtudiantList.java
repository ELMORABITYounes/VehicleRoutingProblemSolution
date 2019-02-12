package com.elmorabit.ensak.dto;

import com.elmorabit.ensak.domain.Niveau;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.ArrayList;

@NoArgsConstructor

@AllArgsConstructor
@Data
public class ImportEtudiantList {
    @NotNull
    private ArrayList<ExcelUserRow> rows;
    @NotNull
    private Niveau niveau;
}
