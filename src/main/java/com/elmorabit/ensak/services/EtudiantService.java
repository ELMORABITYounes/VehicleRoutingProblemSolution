package com.elmorabit.ensak.services;

import com.elmorabit.ensak.domain.Etudiant;
import com.elmorabit.ensak.dto.ImportEtudiantList;

public interface EtudiantService {
    public Etudiant save(Etudiant etudiant);
    public Etudiant update(Etudiant etudiant);
    public void importEtudiants(ImportEtudiantList list);
}
