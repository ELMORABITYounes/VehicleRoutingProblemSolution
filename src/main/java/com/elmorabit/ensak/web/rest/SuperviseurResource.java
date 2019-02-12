package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.Societe;
import com.elmorabit.ensak.domain.Superviseur;
import com.elmorabit.ensak.repository.SocieteRepository;
import com.elmorabit.ensak.repository.SuperviseurRepository;
import com.elmorabit.ensak.web.rest.errors.BadRequestAlertException;
import com.elmorabit.ensak.web.rest.util.HeaderUtil;
import com.elmorabit.ensak.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Superviseur.
 */
@RestController
@RequestMapping("/api")
public class SuperviseurResource {

    private final Logger log = LoggerFactory.getLogger(SuperviseurResource.class);

    private static final String ENTITY_NAME = "superviseur";

    private final SuperviseurRepository superviseurRepository;

    @Autowired
    private SocieteRepository societeRepository;
    public SuperviseurResource(SuperviseurRepository superviseurRepository) {
        this.superviseurRepository = superviseurRepository;
    }

    /**
     * POST  /superviseurs : Create a new superviseur.
     *
     * @param superviseur the superviseur to create
     * @return the ResponseEntity with status 201 (Created) and with body the new superviseur, or with status 400 (Bad Request) if the superviseur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/superviseurs")
    @Timed
    public ResponseEntity<Superviseur> createSuperviseur(@Valid @RequestBody Superviseur superviseur) throws URISyntaxException {
        log.debug("REST request to save Superviseur : {}", superviseur);
        if (superviseur.getId() != null) {
            throw new BadRequestAlertException("A new superviseur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Superviseur result = superviseurRepository.save(superviseur);
        return ResponseEntity.created(new URI("/api/superviseurs/" + result.getId()))
            .body(result);
    }

    @PostMapping("/superviseurs/bySociete")
    @Timed
    public List<Superviseur> getBySociete(@Valid @RequestBody Societe societe) {
        return superviseurRepository.findBySociete(societe);
    }

    /**
     * PUT  /superviseurs : Updates an existing superviseur.
     *
     * @param superviseur the superviseur to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated superviseur,
     * or with status 400 (Bad Request) if the superviseur is not valid,
     * or with status 500 (Internal Server Error) if the superviseur couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/superviseurs")
    @Timed
    public ResponseEntity<Superviseur> updateSuperviseur(@Valid @RequestBody Superviseur superviseur) throws URISyntaxException {
        log.debug("REST request to update Superviseur : {}", superviseur);
        if (superviseur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Superviseur result = superviseurRepository.save(superviseur);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /superviseurs : get all the superviseurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of superviseurs in body
     */
    @GetMapping("/superviseurs")
    @Timed
    public ResponseEntity<List<Superviseur>> getAllSuperviseurs(Pageable pageable) {
        log.debug("REST request to get a page of Superviseurs");
        Page<Superviseur> page = superviseurRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/superviseurs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /superviseurs/:id : get the "id" superviseur.
     *
     * @param id the id of the superviseur to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the superviseur, or with status 404 (Not Found)
     */
    @GetMapping("/superviseurs/{id}")
    @Timed
    public ResponseEntity<Superviseur> getSuperviseur(@PathVariable Long id) {
        log.debug("REST request to get Superviseur : {}", id);
        Optional<Superviseur> superviseur = superviseurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(superviseur);
    }

    @GetMapping("/superviseurs/bySocieteId/{id}")
    @Timed
    public ResponseEntity<List<Superviseur>> getSuperviseursBySocieteId(@PathVariable Long id) {
        Optional<Societe> societe = societeRepository.findById(id);
        List<Superviseur> superviseurs = superviseurRepository.findBySociete(societe.get());
        return ResponseEntity.ok().body(superviseurs);
    }


    /**
     * DELETE  /superviseurs/:id : delete the "id" superviseur.
     *
     * @param id the id of the superviseur to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/superviseurs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSuperviseur(@PathVariable Long id) {
        log.debug("REST request to delete Superviseur : {}", id);

        superviseurRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
