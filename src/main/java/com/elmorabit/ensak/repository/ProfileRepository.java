package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.AppUser;
import com.elmorabit.ensak.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Profile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(AppUser User);
}
