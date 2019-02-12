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
 * A Niveau.
 */
@Entity
@Table(name = "niveau")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Niveau implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("niveaux")
    private Filiere filiere;

    @OneToMany(mappedBy = "niveau")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Semestre> semestres = new HashSet<>();
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

    public Niveau nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public Niveau filiere(Filiere filiere) {
        this.filiere = filiere;
        return this;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Set<Semestre> getSemestres() {
        return semestres;
    }

    public Niveau semestres(Set<Semestre> semestres) {
        this.semestres = semestres;
        return this;
    }

    public Niveau addSemestres(Semestre semestre) {
        this.semestres.add(semestre);
        semestre.setNiveau(this);
        return this;
    }

    public Niveau removeSemestres(Semestre semestre) {
        this.semestres.remove(semestre);
        semestre.setNiveau(null);
        return this;
    }

    public void setSemestres(Set<Semestre> semestres) {
        this.semestres = semestres;
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
        Niveau niveau = (Niveau) o;
        if (niveau.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niveau.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Niveau{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
