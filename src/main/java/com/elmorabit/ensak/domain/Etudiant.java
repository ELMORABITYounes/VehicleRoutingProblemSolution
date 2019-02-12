package com.elmorabit.ensak.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Etudiant.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Etudiant extends Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1000000000L)
    @Max(value = 9999999999L)
    @Column(name = "cne",unique = true)
    private Long cne;

    @ManyToOne()
    @JsonIgnoreProperties("")
    private Niveau niveau;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCne() {
        return cne;
    }

    public Etudiant cne(Long cne) {
        this.cne = cne;
        return this;
    }

    public void setCne(Long cne) {
        this.cne = cne;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public Etudiant niveau(Niveau niveau) {
        this.niveau = niveau;
        return this;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
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
        Etudiant etudiant = (Etudiant) o;
        if (etudiant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etudiant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Etudiant{" +
            "id=" + getId() +
            ", cne=" + getCne() +
            "}";
    }
}
