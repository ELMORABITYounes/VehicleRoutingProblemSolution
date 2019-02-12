package com.elmorabit.ensak.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Filiere.
 */
@Entity
@Table(name = "filiere")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Filiere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @OneToMany(mappedBy = "filiere")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Niveau> niveaux = new HashSet<>();
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

    public Filiere nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Niveau> getNiveaux() {
        return niveaux;
    }

    public Filiere niveaux(Set<Niveau> niveaus) {
        this.niveaux = niveaus;
        return this;
    }

    public Filiere addNiveaux(Niveau niveau) {
        this.niveaux.add(niveau);
        niveau.setFiliere(this);
        return this;
    }

    public Filiere removeNiveaux(Niveau niveau) {
        this.niveaux.remove(niveau);
        niveau.setFiliere(null);
        return this;
    }

    public void setNiveaux(Set<Niveau> niveaus) {
        this.niveaux = niveaus;
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
        Filiere filiere = (Filiere) o;
        if (filiere.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filiere.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Filiere{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
