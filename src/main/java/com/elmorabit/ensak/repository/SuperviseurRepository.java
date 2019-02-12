package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Societe;
import com.elmorabit.ensak.domain.Superviseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Superviseur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuperviseurRepository extends JpaRepository<Superviseur, Long> {
    List<Superviseur> findBySociete(Societe societe);
}
