package com.elmorabit.ensak.services;

import com.elmorabit.ensak.domain.Professeur;
import com.elmorabit.ensak.dto.ImportProfesseurList;

public interface ProfesseurService {
    public Professeur save(Professeur professeur);
    public Professeur update(Professeur professeur);
    public void importProfesseurs(ImportProfesseurList list);

}
