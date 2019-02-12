package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.MembreJury;
import com.elmorabit.ensak.repository.MembreJuryRepository;
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
 * REST controller for managing MembreJury.
 */
@RestController
@RequestMapping("/api")
public class MembreJuryResource {

    private final Logger log = LoggerFactory.getLogger(MembreJuryResource.class);

    private static final String ENTITY_NAME = "membreJury";

    private final MembreJuryRepository membreJuryRepository;

    public MembreJuryResource(MembreJuryRepository membreJuryRepository) {
        this.membreJuryRepository = membreJuryRepository;
    }

    /**
     * POST  /membre-juries : Create a new membreJury.
     *
     * @param membreJury the membreJury to create
     * @return the ResponseEntity with status 201 (Created) and with body the new membreJury, or with status 400 (Bad Request) if the membreJury has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/membre-juries")
    @Timed
    public ResponseEntity<MembreJury> createMembreJury(@Valid @RequestBody MembreJury membreJury) throws URISyntaxException {
        log.debug("REST request to save MembreJury : {}", membreJury);
        if (membreJury.getId() != null) {
            throw new BadRequestAlertException("A new membreJury cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MembreJury result = membreJuryRepository.save(membreJury);
        return ResponseEntity.created(new URI("/api/membre-juries/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /membre-juries : Updates an existing membreJury.
     *
     * @param membreJury the membreJury to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated membreJury,
     * or with status 400 (Bad Request) if the membreJury is not valid,
     * or with status 500 (Internal Server Error) if the membreJury couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/membre-juries")
    @Timed
    public ResponseEntity<MembreJury> updateMembreJury(@Valid @RequestBody MembreJury membreJury) throws URISyntaxException {
        log.debug("REST request to update MembreJury : {}", membreJury);
        if (membreJury.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MembreJury result = membreJuryRepository.save(membreJury);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /membre-juries : get all the membreJuries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of membreJuries in body
     */
    @GetMapping("/membre-juries")
    @Timed
    public ResponseEntity<List<MembreJury>> getAllMembreJuries(Pageable pageable) {
        log.debug("REST request to get a page of MembreJuries");
        Page<MembreJury> page = membreJuryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/membre-juries");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /membre-juries/:id : get the "id" membreJury.
     *
     * @param id the id of the membreJury to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the membreJury, or with status 404 (Not Found)
     */
    @GetMapping("/membre-juries/{id}")
    @Timed
    public ResponseEntity<MembreJury> getMembreJury(@PathVariable Long id) {
        log.debug("REST request to get MembreJury : {}", id);
        Optional<MembreJury> membreJury = membreJuryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(membreJury);
    }

    /**
     * DELETE  /membre-juries/:id : delete the "id" membreJury.
     *
     * @param id the id of the membreJury to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/membre-juries/{id}")
    @Timed
    public ResponseEntity<Void> deleteMembreJury(@PathVariable Long id) {
        log.debug("REST request to delete MembreJury : {}", id);

        membreJuryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
