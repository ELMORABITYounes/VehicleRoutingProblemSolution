package com.elmorabit.ensak.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser implements UserDetails {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled;
    private String lastName;
    private String firstName;
    private String telephone;
    @OneToOne(  mappedBy="user", fetch= FetchType.LAZY)
    @JsonIgnore
    private Profile profile;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppAuthority> authorities;

    @PreRemove
    public void removeUserFromAuthority() {
        for (AppAuthority authority:authorities)
        authority.getUsers().remove(this);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public AppUser(String username, String password,String firstName,String lastName,String tel) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.id=null;
        this.authorities=new ArrayList<>();
        this.username = username;
        this.password = password;
        this.enabled = false;
        this.telephone = tel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @JsonIgnore
    public void setAuthorities(Collection<AppAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonProperty
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void addAuthority(AppAuthority authority){
        this.authorities.add(authority);
    }
    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
