package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.MembreJury;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MembreJury entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MembreJuryRepository extends JpaRepository<MembreJury, Long> {

}
