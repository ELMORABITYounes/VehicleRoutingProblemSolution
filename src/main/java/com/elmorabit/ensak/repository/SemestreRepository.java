package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Semestre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {

}
