package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.Niveau;
import com.elmorabit.ensak.repository.NiveauRepository;
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
 * REST controller for managing Niveau.
 */
@RestController
@RequestMapping("/api")
public class NiveauResource {

    private final Logger log = LoggerFactory.getLogger(NiveauResource.class);

    private static final String ENTITY_NAME = "niveau";

    private final NiveauRepository niveauRepository;

    public NiveauResource(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    /**
     * POST  /niveaus : Create a new niveau.
     *
     * @param niveau the niveau to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niveau, or with status 400 (Bad Request) if the niveau has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niveaus")
    @Timed
    public ResponseEntity<Niveau> createNiveau(@Valid @RequestBody Niveau niveau) throws URISyntaxException {
        log.debug("REST request to save Niveau : {}", niveau);
        if (niveau.getId() != null) {
            throw new BadRequestAlertException("A new niveau cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Niveau result = niveauRepository.save(niveau);
        return ResponseEntity.created(new URI("/api/niveaus/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /niveaus : Updates an existing niveau.
     *
     * @param niveau the niveau to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated niveau,
     * or with status 400 (Bad Request) if the niveau is not valid,
     * or with status 500 (Internal Server Error) if the niveau couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/niveaus")
    @Timed
    public ResponseEntity<Niveau> updateNiveau(@Valid @RequestBody Niveau niveau) throws URISyntaxException {
        log.debug("REST request to update Niveau : {}", niveau);
        if (niveau.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Niveau result = niveauRepository.save(niveau);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /niveaus : get all the niveaus.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of niveaus in body
     */
    @GetMapping("/niveaus")
    @Timed
    public ResponseEntity<List<Niveau>> getAllNiveaus(Pageable pageable) {
        log.debug("REST request to get a page of Niveaus");
        Page<Niveau> page = niveauRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/niveaus");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /niveaus/:id : get the "id" niveau.
     *
     * @param id the id of the niveau to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the niveau, or with status 404 (Not Found)
     */
    @GetMapping("/niveaus/{id}")
    @Timed
    public ResponseEntity<Niveau> getNiveau(@PathVariable Long id) {
        log.debug("REST request to get Niveau : {}", id);
        Optional<Niveau> niveau = niveauRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(niveau);
    }

    /**
     * DELETE  /niveaus/:id : delete the "id" niveau.
     *
     * @param id the id of the niveau to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/niveaus/{id}")
    @Timed
    public ResponseEntity<Void> deleteNiveau(@PathVariable Long id) {
        log.debug("REST request to delete Niveau : {}", id);

        niveauRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
