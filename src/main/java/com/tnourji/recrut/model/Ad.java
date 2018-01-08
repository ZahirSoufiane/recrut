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
 * A Ad.
 */
@Entity
@Table(name = "ad")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "contact")
    private String contact;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "ad")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Quizz> quizzes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Ad title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public Ad jobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
        return this;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getContact() {
        return contact;
    }

    public Ad contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Company getCompany() {
        return company;
    }

    public Ad company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Quizz> getQuizzes() {
        return quizzes;
    }

    public Ad quizzes(Set<Quizz> quizzes) {
        this.quizzes = quizzes;
        return this;
    }

    public Ad addQuizz(Quizz quizz) {
        this.quizzes.add(quizz);
        quizz.setAd(this);
        return this;
    }

    public Ad removeQuizz(Quizz quizz) {
        this.quizzes.remove(quizz);
        quizz.setAd(null);
        return this;
    }

    public void setQuizzes(Set<Quizz> quizzes) {
        this.quizzes = quizzes;
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
        Ad ad = (Ad) o;
        if (ad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ad{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", jobDescription='" + getJobDescription() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
}
