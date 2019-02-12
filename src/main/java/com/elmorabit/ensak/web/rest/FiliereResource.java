package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.Filiere;
import com.elmorabit.ensak.repository.FiliereRepository;
import com.elmorabit.ensak.services.FiliereService;
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
 * REST controller for managing Filiere.
 */
@RestController
@RequestMapping("/api")
public class FiliereResource {

    private final Logger log = LoggerFactory.getLogger(FiliereResource.class);

    private static final String ENTITY_NAME = "filiere";

    private final FiliereService filiereService;
    @Autowired
    private FiliereRepository filiereRepository;

    public FiliereResource(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    /**
     * POST  /filieres : Create a new filiere.
     *
     * @param filiere the filiere to create
     * @return the ResponseEntity with status 201 (Created) and with body the new filiere, or with status 400 (Bad Request) if the filiere has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/filieres")
    @Timed
    public ResponseEntity<Filiere> createFiliere(@Valid @RequestBody Filiere filiere) throws URISyntaxException {
        log.debug("REST request to save Filiere : {}", filiere);
        if (filiere.getId() != null) {
            throw new BadRequestAlertException("A new filiere cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Filiere result = filiereService.save(filiere);
        return ResponseEntity.created(new URI("/api/filieres/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /filieres : Updates an existing filiere.
     *
     * @param filiere the filiere to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated filiere,
     * or with status 400 (Bad Request) if the filiere is not valid,
     * or with status 500 (Internal Server Error) if the filiere couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/filieres")
    @Timed
    public ResponseEntity<Filiere> updateFiliere(@Valid @RequestBody Filiere filiere) throws URISyntaxException {
        log.debug("REST request to update Filiere : {}", filiere);
        if (filiere.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Filiere result = filiereService.save(filiere);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /filieres : get all the filieres.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of filieres in body
     */
    @GetMapping("/filieres")
    @Timed
    public ResponseEntity<List<Filiere>> getAllFilieres(Pageable pageable) {
        log.debug("REST request to get a page of Filieres");
        Page<Filiere> page = filiereService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/filieres");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /filieres/:id : get the "id" filiere.
     *
     * @param id the id of the filiere to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the filiere, or with status 404 (Not Found)
     */
    @GetMapping("/filieres/{id}")
    @Timed
    public ResponseEntity<Filiere> getFiliere(@PathVariable Long id) {
        log.debug("REST request to get Filiere : {}", id);
        Optional<Filiere> filiere = filiereService.findOne(id);
        return ResponseUtil.wrapOrNotFound(filiere);
    }

    /**
     * DELETE  /filieres/:id : delete the "id" filiere.
     *
     * @param id the id of the filiere to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/filieres/{id}")
    @Timed
    public ResponseEntity<Void> deleteFiliere(@PathVariable Long id) {
        log.debug("REST request to delete Filiere : {}", id);
        filiereService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filieres/count")
    @Timed
    public long count() {
        return filiereRepository.count();
    }
}
