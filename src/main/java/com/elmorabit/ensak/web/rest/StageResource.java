package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.*;
import com.elmorabit.ensak.repository.StageRepository;
import com.elmorabit.ensak.repository.UserRepository;
import com.elmorabit.ensak.security.SecurityUtils;
import com.elmorabit.ensak.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing Stage.
 */
@RestController
@RequestMapping("/api")
public class StageResource {

    private final Logger log = LoggerFactory.getLogger(StageResource.class);

    private static final String ENTITY_NAME = "stage";

    private final StageRepository stageRepository;

    @Autowired
    private UserRepository userRepository;

    public StageResource(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    /**
     * POST  /stages : Create a new stage.
     *
     * @param stage the stage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stage, or with status 400 (Bad Request) if the stage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stages")
    @Timed
    public ResponseEntity<Stage> createStage(@Valid @RequestBody Stage stage) throws URISyntaxException {
        log.debug("REST request to save Stage : {}", stage);
        if (stage.getId() != null) {
            throw new BadRequestAlertException("A new stage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Stage result = stageRepository.save(stage);
        return ResponseEntity.created(new URI("/api/stages/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /stages : Updates an existing stage.
     *
     * @param stage the stage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stage,
     * or with status 400 (Bad Request) if the stage is not valid,
     * or with status 500 (Internal Server Error) if the stage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stages")
    @Timed
    public ResponseEntity<Stage> updateStage(@Valid @RequestBody Stage stage) throws URISyntaxException {
        log.debug("REST request to update Stage : {}", stage);
        if (stage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Stage result = stageRepository.save(stage);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /stages : get all the stages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stages in body
     */
    @GetMapping("/stages")
    @Timed
    public ResponseEntity<List<Stage>> getAllStages(Pageable pageable) {
        log.debug("REST request to get a page of Stages");
        Page<Stage> page = stageRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stages");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/stages/byUser")
    @Timed
    public ResponseEntity<List<Stage>> getAllStagesByProfesseur(Pageable pageable) {
        log.debug("REST request to get a page of Stages");
        Optional<String> username=SecurityUtils.getCurrentUserLogin();
        Page<Stage> page;
        Optional<AppUser> user=userRepository.findOneByUsername(username.get());
        Profile profile=user.get().getProfile();
        if(profile instanceof Professeur){
            page=stageRepository.findByProfesseurEncadrant((Professeur) profile,pageable);
        }else {
            page=stageRepository.findByEtudiant((Etudiant) profile,pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stages/byUser");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /stages/:id : get the "id" stage.
     *
     * @param id the id of the stage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stage, or with status 404 (Not Found)
     */
    @GetMapping("/stages/{id}")
    @Timed
    public ResponseEntity<Stage> getStage(@PathVariable Long id) {
        log.debug("REST request to get Stage : {}", id);
        Optional<Stage> stage = stageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(stage);
    }

    /**
     * DELETE  /stages/:id : delete the "id" stage.
     *
     * @param id the id of the stage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stages/{id}")
    @Timed
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        log.debug("REST request to delete Stage : {}", id);

        stageRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stages/count")
    @Timed
    public long count() {
        return stageRepository.count();
    }
}
