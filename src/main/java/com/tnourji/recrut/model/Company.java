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
 * A Company.
 */
@Entity
@DiscriminatorValue("CP")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Company extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "locality")
    private String locality;

    @Column(name = "nb_employee")
    private Integer nbEmployee;

    @Column(name = "vat_number")
    private String vatNumber;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ad> ads = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Company name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocality() {
        return locality;
    }

    public Company locality(String locality) {
        this.locality = locality;
        return this;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Integer getNbEmployee() {
        return nbEmployee;
    }

    public Company nbEmployee(Integer nbEmployee) {
        this.nbEmployee = nbEmployee;
        return this;
    }

    public void setNbEmployee(Integer nbEmployee) {
        this.nbEmployee = nbEmployee;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public Company vatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public Set<Ad> getAds() {
        return ads;
    }

    public Company ads(Set<Ad> ads) {
        this.ads = ads;
        return this;
    }

    public Company addAd(Ad ad) {
        this.ads.add(ad);
        ad.setCompany(this);
        return this;
    }

    public Company removeAd(Ad ad) {
        this.ads.remove(ad);
        ad.setCompany(null);
        return this;
    }

    public void setAds(Set<Ad> ads) {
        this.ads = ads;
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
        Company company = (Company) o;
        if (company.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), company.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", locality='" + getLocality() + "'" +
            ", nbEmployee=" + getNbEmployee() +
            ", vatNumber='" + getVatNumber() + "'" +
            "}";
    }
}
