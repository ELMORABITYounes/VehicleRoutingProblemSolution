package com.elmorabit.ensak.services;

import com.elmorabit.ensak.domain.Enseignement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Enseignement.
 */
public interface EnseignementService {

    /**
     * Save a enseignement.
     *
     * @param enseignement the entity to save
     * @return the persisted entity
     */
    Enseignement save(Enseignement enseignement);

    /**
     * Get all the enseignements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Enseignement> findAll(Pageable pageable);


    /**
     * Get the "id" enseignement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Enseignement> findOne(Long id);

    /**
     * Delete the "id" enseignement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
