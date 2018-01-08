package com.tnourji.recrut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Condidat.
 */
@Entity
@DiscriminatorValue("CD")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Condidat extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    
    private Long id;

    @OneToMany(mappedBy = "condidat")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuizzResultCandidate> quizzResultCandidates = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<QuizzResultCandidate> getQuizzResultCandidates() {
        return quizzResultCandidates;
    }

    public Condidat quizzResultCandidates(Set<QuizzResultCandidate> quizzResultCandidates) {
        this.quizzResultCandidates = quizzResultCandidates;
        return this;
    }

    public Condidat addQuizzResultCandidate(QuizzResultCandidate quizzResultCandidate) {
        this.quizzResultCandidates.add(quizzResultCandidate);
        quizzResultCandidate.setCondidat(this);
        return this;
    }

    public Condidat removeQuizzResultCandidate(QuizzResultCandidate quizzResultCandidate) {
        this.quizzResultCandidates.remove(quizzResultCandidate);
        quizzResultCandidate.setCondidat(null);
        return this;
    }

    public void setQuizzResultCandidates(Set<QuizzResultCandidate> quizzResultCandidates) {
        this.quizzResultCandidates = quizzResultCandidates;
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
        Condidat condidat = (Condidat) o;
        if (condidat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), condidat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Condidat{" +
            "id=" + getId() +
            "}";
    }
}
