package com.elmorabit.ensak.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Soutenance.
 */
@Entity
@Table(name = "soutenance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Soutenance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private ZonedDateTime date;

    @OneToMany(mappedBy = "soutenance",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER,orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MembreJury> membresJuries = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Soutenance date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Set<MembreJury> getMembresJuries() {
        return membresJuries;
    }

    public Soutenance membresJuries(Set<MembreJury> membreJuries) {
        this.membresJuries = membreJuries;
        membreJuries.forEach(membreJury->membreJury.setSoutenance(this));
        return this;
    }

    public Soutenance addMembresJury(MembreJury membreJury) {
        this.membresJuries.add(membreJury);
        membreJury.setSoutenance(this);
        return this;
    }

    public Soutenance removeMembresJury(MembreJury membreJury) {
        this.membresJuries.remove(membreJury);
        membreJury.setSoutenance(null);
        return this;
    }

    public void setMembresJuries(Set<MembreJury> membreJuries) {
        membreJuries.forEach(membreJury->membreJury.setSoutenance(this));
        this.membresJuries = membreJuries;
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
        Soutenance soutenance = (Soutenance) o;
        if (soutenance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), soutenance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Soutenance{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
