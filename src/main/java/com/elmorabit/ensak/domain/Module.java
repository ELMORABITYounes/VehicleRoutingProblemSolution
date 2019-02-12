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
 * A Module.
 */
@Entity
@Table(name = "module")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Max(value = 46)
    @Column(name = "nbr_heures_cours", nullable = false)
    private Integer nbrHeuresCours;

    @NotNull
    @Max(value = 46)
    @Column(name = "nbr_heures_td", nullable = false)
    private Integer nbrHeuresTD;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Departement departement;

    @OneToMany(mappedBy = "module")
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

    public Module nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNbrHeuresCours() {
        return nbrHeuresCours;
    }

    public Module nbrHeuresCours(Integer nbrHeuresCours) {
        this.nbrHeuresCours = nbrHeuresCours;
        return this;
    }

    public void setNbrHeuresCours(Integer nbrHeuresCours) {
        this.nbrHeuresCours = nbrHeuresCours;
    }

    public Integer getNbrHeuresTD() {
        return nbrHeuresTD;
    }

    public Module nbrHeuresTD(Integer nbrHeuresTD) {
        this.nbrHeuresTD = nbrHeuresTD;
        return this;
    }

    public void setNbrHeuresTD(Integer nbrHeuresTD) {
        this.nbrHeuresTD = nbrHeuresTD;
    }

    public Departement getDepartement() {
        return departement;
    }

    public Module departement(Departement departement) {
        this.departement = departement;
        return this;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Set<Enseignement> getEnseignements() {
        return enseignements;
    }

    public Module enseignements(Set<Enseignement> enseignements) {
        this.enseignements = enseignements;
        return this;
    }

    public Module addEnseignements(Enseignement enseignement) {
        this.enseignements.add(enseignement);
        enseignement.setModule(this);
        return this;
    }

    public Module removeEnseignements(Enseignement enseignement) {
        this.enseignements.remove(enseignement);
        enseignement.setModule(null);
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
        Module module = (Module) o;
        if (module.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), module.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Module{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", nbrHeuresCours=" + getNbrHeuresCours() +
            ", nbrHeuresTD=" + getNbrHeuresTD() +
            "}";
    }
}
