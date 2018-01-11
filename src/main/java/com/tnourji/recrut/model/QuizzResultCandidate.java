package com.tnourji.recrut.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A QuizzResultCandidate.
 */
@Entity
@Table(name = "quizz_result_candidate")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuizzResultCandidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result")
    private Integer result;

    @ManyToOne
    private Quizz quizz;

    @ManyToOne
    private Condidate condidat;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getResult() {
        return result;
    }

    public QuizzResultCandidate result(Integer result) {
        this.result = result;
        return this;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Quizz getQuizz() {
        return quizz;
    }

    public QuizzResultCandidate quizz(Quizz quizz) {
        this.quizz = quizz;
        return this;
    }

    public void setQuizz(Quizz quizz) {
        this.quizz = quizz;
    }

    public Condidate getCondidat() {
        return condidat;
    }

    public QuizzResultCandidate condidat(Condidate condidat) {
        this.condidat = condidat;
        return this;
    }

    public void setCondidat(Condidate condidat) {
        this.condidat = condidat;
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
        QuizzResultCandidate quizzResultCandidate = (QuizzResultCandidate) o;
        if (quizzResultCandidate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quizzResultCandidate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuizzResultCandidate{" +
            "id=" + getId() +
            ", result=" + getResult() +
            "}";
    }
}
