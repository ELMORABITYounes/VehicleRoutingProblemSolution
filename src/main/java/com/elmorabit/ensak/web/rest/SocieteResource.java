package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.Societe;
import com.elmorabit.ensak.repository.SocieteRepository;
import com.elmorabit.ensak.web.rest.errors.BadRequestAlertException;
import com.elmorabit.ensak.web.rest.util.HeaderUtil;
import com.elmorabit.ensak.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing Societe.
 */
@RestController
@RequestMapping("/api")
public class SocieteResource {

    private final Logger log = LoggerFactory.getLogger(SocieteResource.class);

    private static final String ENTITY_NAME = "societe";

    private final SocieteRepository societeRepository;

    public SocieteResource(SocieteRepository societeRepository) {
        this.societeRepository = societeRepository;
    }

    /**
     * POST  /societes : Create a new societe.
     *
     * @param societe the societe to create
     * @return the ResponseEntity with status 201 (Created) and with body the new societe, or with status 400 (Bad Request) if the societe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/societes")
    @Timed
    public ResponseEntity<Societe> createSociete(@Valid @RequestBody Societe societe) throws URISyntaxException {
        log.debug("REST request to save Societe : {}", societe);
        if (societe.getId() != null) {
            throw new BadRequestAlertException("A new societe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Societe result = societeRepository.save(societe);
        return ResponseEntity.created(new URI("/api/societes/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /societes : Updates an existing societe.
     *
     * @param societe the societe to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated societe,
     * or with status 400 (Bad Request) if the societe is not valid,
     * or with status 500 (Internal Server Error) if the societe couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/societes")
    @Timed
    public ResponseEntity<Societe> updateSociete(@Valid @RequestBody Societe societe) throws URISyntaxException {
        log.debug("REST request to update Societe : {}", societe);
        if (societe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Societe result = societeRepository.save(societe);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /societes : get all the societes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of societes in body
     */
    @GetMapping("/societes")
    @Timed
    public ResponseEntity<List<Societe>> getAllSocietes(Pageable pageable) {
        log.debug("REST request to get a page of Societes");
        Page<Societe> page = societeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/societes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /societes/:id : get the "id" societe.
     *
     * @param id the id of the societe to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the societe, or with status 404 (Not Found)
     */
    @GetMapping("/societes/{id}")
    @Timed
    public ResponseEntity<Societe> getSociete(@PathVariable Long id) {
        log.debug("REST request to get Societe : {}", id);
        Optional<Societe> societe = societeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(societe);
    }

    /**
     * DELETE  /societes/:id : delete the "id" societe.
     *
     * @param id the id of the societe to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/societes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSociete(@PathVariable Long id) {
        log.debug("REST request to delete Societe : {}", id);

        societeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/societes/count")
    @Timed
    public long count() {
        return societeRepository.count();
    }
}
