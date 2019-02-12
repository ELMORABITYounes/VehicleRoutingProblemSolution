package com.elmorabit.ensak.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Professeur.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Professeur extends Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    @Column(name = "somme",unique = true)
    private Long somme;

    @ManyToOne()
    @JsonIgnoreProperties("")
    private Departement departement;

    @OneToMany(mappedBy = "professeur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Enseignement> enseignements = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSomme() {
        return somme;
    }

    public Professeur somme(Long somme) {
        this.somme = somme;
        return this;
    }

    public void setSomme(Long somme) {
        this.somme = somme;
    }

    public Departement getDepartement() {
        return departement;
    }

    public Professeur departement(Departement departement) {
        this.departement = departement;
        return this;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Set<Enseignement> getEnseignements() {
        return enseignements;
    }

    public Professeur enseignements(Set<Enseignement> enseignements) {
        this.enseignements = enseignements;
        return this;
    }

    public Professeur addEnseignements(Enseignement enseignement) {
        this.enseignements.add(enseignement);
        enseignement.setProfesseur(this);
        return this;
    }

    public Professeur removeEnseignements(Enseignement enseignement) {
        this.enseignements.remove(enseignement);
        enseignement.setProfesseur(null);
        return this;
    }

    public void setEnseignements(Set<Enseignement> enseignements) {
        this.enseignements = enseignements;
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
        Professeur professeur = (Professeur) o;
        if (professeur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), professeur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Professeur{" +
            "id=" + getId() +
            ", somme=" + getSomme() +
            "}";
    }
}
