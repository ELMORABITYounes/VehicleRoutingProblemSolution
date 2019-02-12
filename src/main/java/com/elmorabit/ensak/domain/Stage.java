package com.elmorabit.ensak.domain;

import com.elmorabit.ensak.domain.enumeration.TypeStage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Stage.
 */
@Entity
@Table(name = "stage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "sujet", nullable = false)
    private String sujet;

    @NotNull
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @NotNull
    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @NotNull
    @Column(name = "technologies", nullable = false)
    private String technologies;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type", nullable = false)
    private TypeStage type;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(unique = true)
    private Soutenance soutenance;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Etudiant etudiant;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Superviseur superviseur;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Professeur professeurEncadrant;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private Societe societe;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public Stage sujet(String sujet) {
        this.sujet = sujet;
        return this;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Stage dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Stage dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getTechnologies() {
        return technologies;
    }

    public Stage technologies(String technologies) {
        this.technologies = technologies;
        return this;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public TypeStage getType() {
        return type;
    }

    public Stage type(TypeStage type) {
        this.type = type;
        return this;
    }

    public void setType(TypeStage type) {
        this.type = type;
    }

    public Soutenance getSoutenance() {
        return soutenance;
    }

    public Stage soutenance(Soutenance soutenance) {
        this.soutenance = soutenance;
        return this;
    }

    public void setSoutenance(Soutenance soutenance) {
        this.soutenance = soutenance;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Stage etudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        return this;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Superviseur getSuperviseur() {
        return superviseur;
    }

    public Stage superviseur(Superviseur superviseur) {
        this.superviseur = superviseur;
        return this;
    }

    public void setSuperviseur(Superviseur superviseur) {
        this.superviseur = superviseur;
    }

    public Professeur getProfesseurEncadrant() {
        return professeurEncadrant;
    }

    public Stage professeurEncadrant(Professeur professeur) {
        this.professeurEncadrant = professeur;
        return this;
    }

    public void setProfesseurEncadrant(Professeur professeur) {
        this.professeurEncadrant = professeur;
    }

    public Societe getSociete() {
        return societe;
    }

    public Stage societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
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
        Stage stage = (Stage) o;
        if (stage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Stage{" +
            "id=" + getId() +
            ", sujet='" + getSujet() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", technologies='" + getTechnologies() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
