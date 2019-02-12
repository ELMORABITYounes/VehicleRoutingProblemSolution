package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Departement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

}
