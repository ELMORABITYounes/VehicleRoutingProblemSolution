package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Etudiant;
import com.elmorabit.ensak.domain.Professeur;
import com.elmorabit.ensak.domain.Stage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Stage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    public long count();
    public Page<Stage> findByProfesseurEncadrant(Professeur professeur, Pageable pageable);
    public Page<Stage> findByEtudiant(Etudiant etudiant, Pageable pageable);
}
