package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.Professeur;
import com.elmorabit.ensak.domain.Professeur;
import com.elmorabit.ensak.dto.ImportProfesseurList;
import com.elmorabit.ensak.dto.ImportProfesseurList;
import com.elmorabit.ensak.repository.ProfesseurRepository;
import com.elmorabit.ensak.services.ProfesseurService;
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
 * REST controller for managing Professeur.
 */
@RestController
@RequestMapping("/api")
public class ProfesseurResource {

    private final Logger log = LoggerFactory.getLogger(ProfesseurResource.class);

    private static final String ENTITY_NAME = "professeur";

    @Autowired
    private ProfesseurService professeurService;
    private final ProfesseurRepository professeurRepository;

    public ProfesseurResource(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    /**
     * POST  /professeurs : Create a new professeur.
     *
     * @param professeur the professeur to create
     * @return the ResponseEntity with status 201 (Created) and with body the new professeur, or with status 400 (Bad Request) if the professeur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/professeurs")
    @Timed
    public ResponseEntity<Professeur> createProfesseur(@Valid @RequestBody Professeur professeur) throws URISyntaxException {
        log.debug("REST request to save Professeur : {}", professeur);
        if (professeur.getId() != null) {
            throw new BadRequestAlertException("A new professeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if(professeur.getUser()==null){
            throw new RuntimeException("requete erroné");
        }

        System.out.println(professeur.getUser());

        Professeur result = this.professeurService.save(professeur);
        return ResponseEntity.created(new URI("/api/professeurs/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /professeurs : Updates an existing professeur.
     *
     * @param professeur the professeur to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated professeur,
     * or with status 400 (Bad Request) if the professeur is not valid,
     * or with status 500 (Internal Server Error) if the professeur couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/professeurs")
    @Timed
    public ResponseEntity<Professeur> updateProfesseur(@Valid @RequestBody Professeur professeur) throws URISyntaxException {
        log.debug("REST request to update Professeur : {}", professeur);
        if (professeur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Professeur result = professeurService.update(professeur);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /professeurs : get all the professeurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of professeurs in body
     */
    @GetMapping("/professeurs")
    @Timed
    public ResponseEntity<List<Professeur>> getAllProfesseurs(Pageable pageable) {
        log.debug("REST request to get a page of Professeurs");
        Page<Professeur> page = professeurRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/professeurs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /professeurs/:id : get the "id" professeur.
     *
     * @param id the id of the professeur to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the professeur, or with status 404 (Not Found)
     */
    @GetMapping("/professeurs/{id}")
    @Timed
    public ResponseEntity<Professeur> getProfesseur(@PathVariable Long id) {
        log.debug("REST request to get Professeur : {}", id);
        Optional<Professeur> professeur = professeurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(professeur);
    }

    /**
     * DELETE  /professeurs/:id : delete the "id" professeur.
     *
     * @param id the id of the professeur to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/professeurs/{id}")
    @Timed
    public ResponseEntity<Void> deleteProfesseur(@PathVariable Long id) {
        log.debug("REST request to delete Professeur : {}", id);

        professeurRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/professeurs/count")
    @Timed
    public long count() {
        return professeurRepository.count();
    }

    @GetMapping("/checkSomme")
    public boolean checkSomme(@RequestParam("value") Long value) {
        return this.professeurRepository.findBySomme(value)!=null;
    }

    @PostMapping("/professeurs/import")
    @Timed
    public void importProfesseurs(@Valid @RequestBody ImportProfesseurList list){
        this.professeurService.importProfesseurs(list);
    }
}
