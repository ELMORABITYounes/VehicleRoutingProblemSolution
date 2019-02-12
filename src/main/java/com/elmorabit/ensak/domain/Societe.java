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
 * A Societe.
 */
@Entity
@Table(name = "societe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Societe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "ville", nullable = false)
    private String ville;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "secteurs_activite")
    private String secteursActivite;

    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @OneToMany(mappedBy = "societe",fetch = FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Superviseur> superviseurs = new HashSet<>();
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

    public Societe nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public Societe ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public Societe adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSecteursActivite() {
        return secteursActivite;
    }

    public Societe secteursActivite(String secteursActivite) {
        this.secteursActivite = secteursActivite;
        return this;
    }

    public void setSecteursActivite(String secteursActivite) {
        this.secteursActivite = secteursActivite;
    }

    public Set<Superviseur> getSuperviseurs() {
        return superviseurs;
    }

    public Societe superviseurs(Set<Superviseur> superviseurs) {
        this.superviseurs = superviseurs;
        return this;
    }

    public Societe addSuperviseurs(Superviseur superviseur) {
        this.superviseurs.add(superviseur);
        superviseur.setSociete(this);
        return this;
    }

    public Societe removeSuperviseurs(Superviseur superviseur) {
        this.superviseurs.remove(superviseur);
        superviseur.setSociete(null);
        return this;
    }

    public void setSuperviseurs(Set<Superviseur> superviseurs) {
        this.superviseurs = superviseurs;
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
        Societe societe = (Societe) o;
        if (societe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), societe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Societe{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", ville='" + getVille() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", secteursActivite='" + getSecteursActivite() + "'" +
            "}";
    }
}
