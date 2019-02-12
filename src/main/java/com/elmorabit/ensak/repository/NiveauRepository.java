package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Niveau entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {
}
