package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Societe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocieteRepository extends JpaRepository<Societe, Long> {
    public long count();
}
