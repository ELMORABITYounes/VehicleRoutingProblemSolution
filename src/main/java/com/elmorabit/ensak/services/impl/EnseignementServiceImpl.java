package com.elmorabit.ensak.services.impl;

import com.elmorabit.ensak.domain.Enseignement;
import com.elmorabit.ensak.repository.EnseignementRepository;
import com.elmorabit.ensak.services.EnseignementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Enseignement.
 */
@Service
@Transactional
public class EnseignementServiceImpl implements EnseignementService {

    private final Logger log = LoggerFactory.getLogger(EnseignementServiceImpl.class);

    private final EnseignementRepository enseignementRepository;

    public EnseignementServiceImpl(EnseignementRepository enseignementRepository) {
        this.enseignementRepository = enseignementRepository;
    }

    /**
     * Save a enseignement.
     *
     * @param enseignement the entity to save
     * @return the persisted entity
     */
    @Override
    public Enseignement save(Enseignement enseignement) {
        log.debug("Request to save Enseignement : {}", enseignement);
        return enseignementRepository.save(enseignement);
    }

    /**
     * Get all the enseignements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Enseignement> findAll(Pageable pageable) {
        log.debug("Request to get all Enseignements");
        return enseignementRepository.findAll(pageable);
    }


    /**
     * Get one enseignement by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Enseignement> findOne(Long id) {
        log.debug("Request to get Enseignement : {}", id);
        return enseignementRepository.findById(id);
    }

    /**
     * Delete the enseignement by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Enseignement : {}", id);
        enseignementRepository.deleteById(id);
    }
}
