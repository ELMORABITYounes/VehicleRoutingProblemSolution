package com.elmorabit.ensak.services.impl;

import com.elmorabit.ensak.domain.Filiere;
import com.elmorabit.ensak.domain.Niveau;
import com.elmorabit.ensak.domain.Semestre;
import com.elmorabit.ensak.repository.FiliereRepository;
import com.elmorabit.ensak.repository.NiveauRepository;
import com.elmorabit.ensak.repository.SemestreRepository;
import com.elmorabit.ensak.services.FiliereService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Filiere.
 */
@Service
@Transactional
public class FiliereServiceImpl implements FiliereService {

    private final Logger log = LoggerFactory.getLogger(FiliereServiceImpl.class);

    private final FiliereRepository filiereRepository;
    @Autowired
    private SemestreRepository semestreRepository;
    @Autowired
    private NiveauRepository niveauRepository;

    public FiliereServiceImpl(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    /**
     * Save a filiere.
     *
     * @param filiere the entity to save
     * @return the persisted entity
     */
    @Override
    public Filiere save(Filiere filiere) {
        log.debug("Request to save Filiere : {}", filiere);
        filiere=filiereRepository.save(filiere);
        for (int i=1;i<=3;i++){
            Niveau niveau=new Niveau();
            niveau.setNom(i+"ere/eme année "+filiere.getNom());
            niveau.setFiliere(filiere);
            niveau=niveauRepository.save(niveau);
            for (int j=1;j<=2;j++){
                Semestre semestre=new Semestre();
                if(i==2 && j==1){
                    semestre.setNom("S3 "+filiere.getNom());
                }else if(i==3 && j==1){
                    semestre.setNom("S5 "+filiere.getNom());
                }else{
                    semestre.setNom("S"+(i*j) +" "+filiere.getNom());
                }
                semestre.setNiveau(niveau);
                semestreRepository.save(semestre);
                niveau.addSemestres(semestre);
            }
            niveauRepository.save(niveau);
            filiere.addNiveaux(niveau);
        }
        return filiereRepository.save(filiere);
    }

    /**
     * Get all the filieres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Filiere> findAll(Pageable pageable) {
        log.debug("Request to get all Filieres");
        return filiereRepository.findAll(pageable);
    }


    /**
     * Get one filiere by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Filiere> findOne(Long id) {
        log.debug("Request to get Filiere : {}", id);
        return filiereRepository.findById(id);
    }

    /**
     * Delete the filiere by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Filiere : {}", id);
        filiereRepository.deleteById(id);
    }
}
