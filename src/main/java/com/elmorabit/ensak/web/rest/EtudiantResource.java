package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.Etudiant;
import com.elmorabit.ensak.dto.ExcelUserRow;
import com.elmorabit.ensak.dto.ImportEtudiantList;
import com.elmorabit.ensak.repository.EtudiantRepository;
import com.elmorabit.ensak.services.EtudiantService;
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
 * REST controller for managing Etudiant.
 */
@RestController
@RequestMapping("/api")
public class EtudiantResource {

    private final Logger log = LoggerFactory.getLogger(EtudiantResource.class);

    private static final String ENTITY_NAME = "etudiant";

    private final EtudiantRepository etudiantRepository;
    @Autowired
    private EtudiantService etudiantService;

    public EtudiantResource(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    /**
     * POST  /etudiants : Create a new etudiant.
     *
     * @param etudiant the etudiant to create
     * @return the ResponseEntity with status 201 (Created) and with body the new etudiant, or with status 400 (Bad Request) if the etudiant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/etudiants")
    @Timed
    public ResponseEntity<Etudiant> createEtudiant(@Valid @RequestBody Etudiant etudiant) throws URISyntaxException {
        log.debug("REST request to save Etudiant : {}", etudiant);
        if (etudiant.getId() != null) {
            throw new BadRequestAlertException("A new etudiant cannot already have an ID", ENTITY_NAME, "idexists");
        }

        if(etudiant.getUser()==null){
            throw new RuntimeException("requete erroné");
        }

        System.out.println(etudiant.getUser());

        Etudiant result = this.etudiantService.save(etudiant);
        return ResponseEntity.created(new URI("/api/etudiants/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /etudiants : Updates an existing etudiant.
     *
     * @param etudiant the etudiant to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated etudiant,
     * or with status 400 (Bad Request) if the etudiant is not valid,
     * or with status 500 (Internal Server Error) if the etudiant couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/etudiants")
    @Timed
    public ResponseEntity<Etudiant> updateEtudiant(@Valid @RequestBody Etudiant etudiant) throws URISyntaxException {
        log.debug("REST request to update Etudiant : {}", etudiant);
        if (etudiant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Etudiant result = etudiantService.update(etudiant);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /etudiants : get all the etudiants.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of etudiants in body
     */
    @GetMapping("/etudiants")
    @Timed
    public ResponseEntity<List<Etudiant>> getAllEtudiants(Pageable pageable) {
        log.debug("REST request to get a page of Etudiants");
        Page<Etudiant> page = etudiantRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/etudiants");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /etudiants/:id : get the "id" etudiant.
     *
     * @param id the id of the etudiant to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the etudiant, or with status 404 (Not Found)
     */
    @GetMapping("/etudiants/{id}")
    @Timed
    public ResponseEntity<Etudiant> getEtudiant(@PathVariable Long id) {
        log.debug("REST request to get Etudiant : {}", id);
        Optional<Etudiant> etudiant = etudiantRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(etudiant);
    }

    /**
     * DELETE  /etudiants/:id : delete the "id" etudiant.
     *
     * @param id the id of the etudiant to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/etudiants/{id}")
    @Timed
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        log.debug("REST request to delete Etudiant : {}", id);

        etudiantRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/etudiants/count")
    @Timed
    public long count() {
        return etudiantRepository.count();
    }

    @GetMapping("/checkCne")
    public boolean checkCne(@RequestParam("value") Long value) {
        return this.etudiantRepository.findEtudiantByCne(value)!=null;
    }

    @PostMapping("/etudiants/import")
    @Timed
    public void importEtudiants(@Valid @RequestBody ImportEtudiantList list){
        this.etudiantService.importEtudiants(list);
    }
}
