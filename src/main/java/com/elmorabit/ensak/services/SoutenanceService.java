package com.elmorabit.ensak.services;

import com.elmorabit.ensak.domain.Soutenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Soutenance.
 */
public interface SoutenanceService {

    /**
     * Save a soutenance.
     *
     * @param soutenance the entity to save
     * @return the persisted entity
     */
    Soutenance save(Soutenance soutenance);

    /**
     * Get all the soutenances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Soutenance> findAll(Pageable pageable);


    /**
     * Get the "id" soutenance.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Soutenance> findOne(Long id);

    /**
     * Delete the "id" soutenance.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
