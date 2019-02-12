package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.*;
import com.elmorabit.ensak.repository.EnseignementRepository;
import com.elmorabit.ensak.repository.UserRepository;
import com.elmorabit.ensak.security.SecurityUtils;
import com.elmorabit.ensak.services.EnseignementService;
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
 * REST controller for managing Enseignement.
 */
@RestController
@RequestMapping("/api")
public class EnseignementResource {

    private final Logger log = LoggerFactory.getLogger(EnseignementResource.class);

    private static final String ENTITY_NAME = "enseignement";

    private final EnseignementService enseignementService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnseignementRepository enseignementRepository;

    public EnseignementResource(EnseignementService enseignementService) {
        this.enseignementService = enseignementService;
    }

    /**
     * POST  /enseignements : Create a new enseignement.
     *
     * @param enseignement the enseignement to create
     * @return the ResponseEntity with status 201 (Created) and with body the new enseignement, or with status 400 (Bad Request) if the enseignement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/enseignements")
    @Timed
    public ResponseEntity<Enseignement> createEnseignement(@Valid @RequestBody Enseignement enseignement) throws URISyntaxException {
        log.debug("REST request to save Enseignement : {}", enseignement);
        if (enseignement.getId() != null) {
            throw new BadRequestAlertException("A new enseignement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Enseignement result = enseignementService.save(enseignement);
        return ResponseEntity.created(new URI("/api/enseignements/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /enseignements : Updates an existing enseignement.
     *
     * @param enseignement the enseignement to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated enseignement,
     * or with status 400 (Bad Request) if the enseignement is not valid,
     * or with status 500 (Internal Server Error) if the enseignement couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/enseignements")
    @Timed
    public ResponseEntity<Enseignement> updateEnseignement(@Valid @RequestBody Enseignement enseignement) throws URISyntaxException {
        log.debug("REST request to update Enseignement : {}", enseignement);
        if (enseignement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Enseignement result = enseignementService.save(enseignement);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /enseignements : get all the enseignements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of enseignements in body
     */
    @GetMapping("/enseignements")
    @Timed
    public ResponseEntity<List<Enseignement>> getAllEnseignements(Pageable pageable) {
        log.debug("REST request to get a page of Enseignements");
        Page<Enseignement> page = enseignementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/enseignements");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/enseignements/byUser")
    @Timed
    public ResponseEntity<List<Enseignement>> getAllEnseignementsByUser(Pageable pageable) {
        log.debug("REST request to get a page of Enseignements");
        Optional<String> username= SecurityUtils.getCurrentUserLogin();
        Optional<AppUser> user=userRepository.findOneByUsername(username.get());
        Profile profile=user.get().getProfile();
        Page<Enseignement> page = enseignementRepository.findByProfesseur((Professeur)profile,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/enseignements/byUser");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /enseignements/:id : get the "id" enseignement.
     *
     * @param id the id of the enseignement to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the enseignement, or with status 404 (Not Found)
     */
    @GetMapping("/enseignements/{id}")
    @Timed
    public ResponseEntity<Enseignement> getEnseignement(@PathVariable Long id) {
        log.debug("REST request to get Enseignement : {}", id);
        Optional<Enseignement> enseignement = enseignementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enseignement);
    }

    /**
     * DELETE  /enseignements/:id : delete the "id" enseignement.
     *
     * @param id the id of the enseignement to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/enseignements/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnseignement(@PathVariable Long id) {
        log.debug("REST request to delete Enseignement : {}", id);
        enseignementService.delete(id);
        return ResponseEntity.ok().build();
    }
}
