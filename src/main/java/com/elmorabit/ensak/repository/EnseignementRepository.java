package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Enseignement;
import com.elmorabit.ensak.domain.Professeur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Enseignement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnseignementRepository extends JpaRepository<Enseignement, Long> {
    Page<Enseignement> findByProfesseur(Professeur professeur, Pageable pageable);
}
