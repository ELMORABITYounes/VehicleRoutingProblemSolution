package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.Soutenance;
import com.elmorabit.ensak.services.SoutenanceService;
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
 * REST controller for managing Soutenance.
 */
@RestController
@RequestMapping("/api")
public class SoutenanceResource {

    private final Logger log = LoggerFactory.getLogger(SoutenanceResource.class);

    private static final String ENTITY_NAME = "soutenance";

    private final SoutenanceService soutenanceService;

    public SoutenanceResource(SoutenanceService soutenanceService) {
        this.soutenanceService = soutenanceService;
    }

    /**
     * POST  /soutenances : Create a new soutenance.
     *
     * @param soutenance the soutenance to create
     * @return the ResponseEntity with status 201 (Created) and with body the new soutenance, or with status 400 (Bad Request) if the soutenance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soutenances")
    @Timed
    public ResponseEntity<Soutenance> createSoutenance(@Valid @RequestBody Soutenance soutenance) throws URISyntaxException {
        log.debug("REST request to save Soutenance : {}", soutenance);
        if (soutenance.getId() != null) {
            throw new BadRequestAlertException("A new soutenance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Soutenance result = soutenanceService.save(soutenance);
        return ResponseEntity.created(new URI("/api/soutenances/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /soutenances : Updates an existing soutenance.
     *
     * @param soutenance the soutenance to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated soutenance,
     * or with status 400 (Bad Request) if the soutenance is not valid,
     * or with status 500 (Internal Server Error) if the soutenance couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/soutenances")
    @Timed
    public ResponseEntity<Soutenance> updateSoutenance(@Valid @RequestBody Soutenance soutenance) throws URISyntaxException {
        log.debug("REST request to update Soutenance : {}", soutenance);
        if (soutenance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Soutenance result = soutenanceService.save(soutenance);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /soutenances : get all the soutenances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of soutenances in body
     */
    @GetMapping("/soutenances")
    @Timed
    public ResponseEntity<List<Soutenance>> getAllSoutenances(Pageable pageable) {
        log.debug("REST request to get a page of Soutenances");
        Page<Soutenance> page = soutenanceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/soutenances");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /soutenances/:id : get the "id" soutenance.
     *
     * @param id the id of the soutenance to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the soutenance, or with status 404 (Not Found)
     */
    @GetMapping("/soutenances/{id}")
    @Timed
    public ResponseEntity<Soutenance> getSoutenance(@PathVariable Long id) {
        log.debug("REST request to get Soutenance : {}", id);
        Optional<Soutenance> soutenance = soutenanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soutenance);
    }

    /**
     * DELETE  /soutenances/:id : delete the "id" soutenance.
     *
     * @param id the id of the soutenance to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/soutenances/{id}")
    @Timed
    public ResponseEntity<Void> deleteSoutenance(@PathVariable Long id) {
        log.debug("REST request to delete Soutenance : {}", id);
        soutenanceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
