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
 * A Quizz.
 */
@Entity
@Table(name = "quizz")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Quizz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_of_question")
    private Integer numberOfQuestion;

    @OneToMany(mappedBy = "quizz")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "quizz")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuizzResultCandidate> quizzResultCandidates = new HashSet<>();

    @ManyToOne
    private Ad ad;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public Quizz numberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
        return this;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Quizz questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Quizz addQuestion(Question question) {
        this.questions.add(question);
        question.setQuizz(this);
        return this;
    }

    public Quizz removeQuestion(Question question) {
        this.questions.remove(question);
        question.setQuizz(null);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<QuizzResultCandidate> getQuizzResultCandidates() {
        return quizzResultCandidates;
    }

    public Quizz quizzResultCandidates(Set<QuizzResultCandidate> quizzResultCandidates) {
        this.quizzResultCandidates = quizzResultCandidates;
        return this;
    }

    public Quizz addQuizzResultCandidate(QuizzResultCandidate quizzResultCandidate) {
        this.quizzResultCandidates.add(quizzResultCandidate);
        quizzResultCandidate.setQuizz(this);
        return this;
    }

    public Quizz removeQuizzResultCandidate(QuizzResultCandidate quizzResultCandidate) {
        this.quizzResultCandidates.remove(quizzResultCandidate);
        quizzResultCandidate.setQuizz(null);
        return this;
    }

    public void setQuizzResultCandidates(Set<QuizzResultCandidate> quizzResultCandidates) {
        this.quizzResultCandidates = quizzResultCandidates;
    }

    public Ad getAd() {
        return ad;
    }

    public Quizz ad(Ad ad) {
        this.ad = ad;
        return this;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
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
        Quizz quizz = (Quizz) o;
        if (quizz.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quizz.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Quizz{" +
            "id=" + getId() +
            ", numberOfQuestion=" + getNumberOfQuestion() +
            "}";
    }
}
