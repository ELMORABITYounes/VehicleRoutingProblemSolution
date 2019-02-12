package com.elmorabit.ensak.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.elmorabit.ensak.domain.enumeration.RoleMembreJury;

/**
 * A MembreJury.
 */
@Entity
@Table(name = "membre_jury")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MembreJury implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_membre", nullable = false)
    private RoleMembreJury roleMembre;

    @ManyToOne
    @JsonIgnoreProperties("membresJuries")
    private Soutenance soutenance;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public MembreJury nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public MembreJury prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public MembreJury email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleMembreJury getRoleMembre() {
        return roleMembre;
    }

    public MembreJury roleMembre(RoleMembreJury roleMembre) {
        this.roleMembre = roleMembre;
        return this;
    }

    public void setRoleMembre(RoleMembreJury roleMembre) {
        this.roleMembre = roleMembre;
    }

    public Soutenance getSoutenance() {
        return soutenance;
    }

    public MembreJury soutenance(Soutenance soutenance) {
        this.soutenance = soutenance;
        return this;
    }

    public void setSoutenance(Soutenance soutenance) {
        this.soutenance = soutenance;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MembreJury membreJury = (MembreJury) o;
        if (membreJury.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), membreJury.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MembreJury{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", email='" + getEmail() + "'" +
            ", roleMembre='" + getRoleMembre() + "'" +
            "}";
    }
}
