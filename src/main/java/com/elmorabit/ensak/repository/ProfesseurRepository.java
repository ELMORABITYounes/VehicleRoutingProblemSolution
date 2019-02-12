package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Professeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    public long count();
    public Professeur findBySomme(Long somme);
}
