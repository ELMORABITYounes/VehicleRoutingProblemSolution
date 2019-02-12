package com.elmorabit.ensak.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppAuthority implements GrantedAuthority {
    @Id @GeneratedValue @JsonIgnore
    private Long id;
    private String authority;
    @ManyToMany(mappedBy="authorities",fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<AppUser> users;

    public Collection<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<AppUser> users) {
        this.users = users;
    }

    public AppAuthority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
