package com.elmorabit.ensak.services.impl;

import com.elmorabit.ensak.domain.Soutenance;
import com.elmorabit.ensak.repository.SoutenanceRepository;
import com.elmorabit.ensak.services.SoutenanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Soutenance.
 */
@Service
@Transactional
public class SoutenanceServiceImpl implements SoutenanceService {

    private final Logger log = LoggerFactory.getLogger(SoutenanceServiceImpl.class);

    private final SoutenanceRepository soutenanceRepository;

    public SoutenanceServiceImpl(SoutenanceRepository soutenanceRepository) {
        this.soutenanceRepository = soutenanceRepository;
    }

    /**
     * Save a soutenance.
     *
     * @param soutenance the entity to save
     * @return the persisted entity
     */
    @Override
    public Soutenance save(Soutenance soutenance) {
        log.debug("Request to save Soutenance : {}", soutenance);
        return soutenanceRepository.save(soutenance);
    }

    /**
     * Get all the soutenances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Soutenance> findAll(Pageable pageable) {
        log.debug("Request to get all Soutenances");
        return soutenanceRepository.findAll(pageable);
    }


    /**
     * Get one soutenance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Soutenance> findOne(Long id) {
        log.debug("Request to get Soutenance : {}", id);
        return soutenanceRepository.findById(id);
    }

    /**
     * Delete the soutenance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Soutenance : {}", id);
        soutenanceRepository.deleteById(id);
    }
}
