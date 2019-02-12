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
 * A Semestre.
 */
@Entity
@Table(name = "semestre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Semestre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("semestres")
    private Niveau niveau;

    @OneToMany(mappedBy = "semestre")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Enseignement> enseignements = new HashSet<>();
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

    public Semestre nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public Semestre niveau(Niveau niveau) {
        this.niveau = niveau;
        return this;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Set<Enseignement> getEnseignements() {
        return enseignements;
    }

    public Semestre enseignements(Set<Enseignement> enseignements) {
        this.enseignements = enseignements;
        return this;
    }

    public Semestre addEnseignements(Enseignement enseignement) {
        this.enseignements.add(enseignement);
        enseignement.setSemestre(this);
        return this;
    }

    public Semestre removeEnseignements(Enseignement enseignement) {
        this.enseignements.remove(enseignement);
        enseignement.setSemestre(null);
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
        Semestre semestre = (Semestre) o;
        if (semestre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), semestre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Semestre{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
