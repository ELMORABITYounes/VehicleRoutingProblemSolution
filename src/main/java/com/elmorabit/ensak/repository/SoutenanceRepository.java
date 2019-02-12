package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Soutenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Soutenance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoutenanceRepository extends JpaRepository<Soutenance, Long> {

}
