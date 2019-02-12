package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Etudiant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    public long count();
    public Etudiant findEtudiantByCne(Long cne);
}
