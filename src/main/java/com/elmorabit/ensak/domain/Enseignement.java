package com.elmorabit.ensak.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Enseignement.
 */
@Entity
@Table(name = "enseignement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Enseignement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("enseignements")
    private Semestre semestre;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("enseignements")
    private Module module;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("enseignements")
    private Professeur professeur;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public Enseignement semestre(Semestre semestre) {
        this.semestre = semestre;
        return this;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Module getModule() {
        return module;
    }

    public Enseignement module(Module module) {
        this.module = module;
        return this;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public Enseignement professeur(Professeur professeur) {
        this.professeur = professeur;
        return this;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
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
        Enseignement enseignement = (Enseignement) o;
        if (enseignement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enseignement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Enseignement{" +
            "id=" + getId() +
            "}";
    }
}
