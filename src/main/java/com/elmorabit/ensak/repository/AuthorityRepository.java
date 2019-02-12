package com.elmorabit.ensak.repository;

import com.elmorabit.ensak.domain.AppAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AppAuthority,Long> {
    public AppAuthority findByAuthority(String roleName);
}
