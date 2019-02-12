package com.elmorabit.ensak.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.elmorabit.ensak.domain.*;
import com.elmorabit.ensak.repository.EtudiantRepository;
import com.elmorabit.ensak.repository.ModuleRepository;
import com.elmorabit.ensak.repository.NiveauRepository;
import com.elmorabit.ensak.repository.UserRepository;
import com.elmorabit.ensak.security.SecurityUtils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing Module.
 */
@RestController
@RequestMapping("/api")
public class ModuleResource {

    private final Logger log = LoggerFactory.getLogger(ModuleResource.class);

    private static final String ENTITY_NAME = "module";

    private final ModuleRepository moduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NiveauRepository niveauRepository;
    public ModuleResource(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    /**
     * POST  /modules : Create a new module.
     *
     * @param module the module to create
     * @return the ResponseEntity with status 201 (Created) and with body the new module, or with status 400 (Bad Request) if the module has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/modules")
    @Timed
    public ResponseEntity<Module> createModule(@Valid @RequestBody Module module) throws URISyntaxException {
        log.debug("REST request to save Module : {}", module);
        if (module.getId() != null) {
            throw new BadRequestAlertException("A new module cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Module result = moduleRepository.save(module);
        return ResponseEntity.created(new URI("/api/modules/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /modules : Updates an existing module.
     *
     * @param module the module to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated module,
     * or with status 400 (Bad Request) if the module is not valid,
     * or with status 500 (Internal Server Error) if the module couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/modules")
    @Timed
    public ResponseEntity<Module> updateModule(@Valid @RequestBody Module module) throws URISyntaxException {
        log.debug("REST request to update Module : {}", module);
        if (module.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Module result = moduleRepository.save(module);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /modules : get all the modules.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of modules in body
     */
    @GetMapping("/modules")
    @Timed
    public ResponseEntity<List<Module>> getAllModules(Pageable pageable) {
        log.debug("REST request to get a page of Modules");
        Page<Module> page = moduleRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/modules");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/modules/byUser")
    @Timed
    public List<Module> getAllModulesByUser() {
        log.debug("REST request to get a page of Modules");
        List<Module> list=new ArrayList<Module>();
        Optional<String> username= SecurityUtils.getCurrentUserLogin();
        Optional<AppUser> user=userRepository.findOneByUsername(username.get());
        Profile profile=user.get().getProfile();
        Niveau niveau=((Etudiant)profile).getNiveau();
        Set<Semestre> semestres=niveau.getSemestres();
        for (Semestre semestre:semestres){
            Set<Enseignement> enseignements=semestre.getEnseignements();
            for (Enseignement enseignement:enseignements){
                list.add(enseignement.getModule());
            }
        }
        return list;
    }

    /**
     * GET  /modules/:id : get the "id" module.
     *
     * @param id the id of the module to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the module, or with status 404 (Not Found)
     */
    @GetMapping("/modules/{id}")
    @Timed
    public ResponseEntity<Module> getModule(@PathVariable Long id) {
        log.debug("REST request to get Module : {}", id);
        Optional<Module> module = moduleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(module);
    }

    /**
     * DELETE  /modules/:id : delete the "id" module.
     *
     * @param id the id of the module to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/modules/{id}")
    @Timed
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        log.debug("REST request to delete Module : {}", id);
        moduleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/modules/count")
    @Timed
    public long count() {
        return moduleRepository.count();
    }
}
