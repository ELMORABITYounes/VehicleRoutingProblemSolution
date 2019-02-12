package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {
    String USERS_BY_USERNAME_CACHE = "usersByUsername";

    public AppUser findByUsername(String username);
    Optional<AppUser> findOneByUsername(String username);
}
