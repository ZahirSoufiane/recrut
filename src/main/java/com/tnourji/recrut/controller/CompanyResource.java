package com.tnourji.recrut.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.tnourji.recrut.bean.CompanyBean;
import com.tnourji.recrut.exception.BadRequestAlertException;
import com.tnourji.recrut.model.Address;
import com.tnourji.recrut.model.Company;
import com.tnourji.recrut.service.AddressService;
import com.tnourji.recrut.service.CompanyService;
import com.tnourji.recrut.util.HeaderUtil;

/**
 * REST controller for managing Company.
 */
@RestController
@RequestMapping("/api")
public class CompanyResource {
    
    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);
    
    private static final String ENTITY_NAME = "company";
    
    private final CompanyService companyService;
    
    private final AddressService addressService;
    
    public CompanyResource(CompanyService companyService, AddressService addressService) {
        this.companyService = companyService;
        this.addressService = addressService;
    }
    
    /**
     * POST /companies : Create a new company.
     *
     * @param company
     *            the company to create
     * @return the ResponseEntity with status 201 (Created) and with body the new company, or with status 400 (Bad
     *         Request) if the company has already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/companies")
    @Timed
    public ResponseEntity<Company> createCompany(@RequestBody CompanyBean company) throws URISyntaxException {
        log.debug("REST request to save Company : {}", company);
        if (company.getId() != null) {
            throw new BadRequestAlertException("A new company cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        Company cp = new Company();
        Address ad = new Address();
        Company result;
        
        cp.setName(company.getName());
        cp.setEmail(company.getEmail());
        cp.setPhone(company.getPhone());
        cp.setNbEmployee(company.getNbEmployee());
        cp.setVatNumber(company.getVatNumber());
        cp.setPassword(company.getPassword());
        
        ad.setCity(company.getCity());
        ad.setCountry(company.getCountry());
        ad.setPostcode(company.getPostcode());
        ad.setAddress1(company.getAddress1());
        ad.setAddress2(company.getAddress2());
        
        
        addressService.save(ad);
        cp.setAddress(ad);
        result = companyService.save(cp);
        
        return ResponseEntity.created(new URI("/api/companies/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    /**
     * PUT /companies : Updates an existing company.
     *
     * @param company
     *            the company to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated company, or with status 400 (Bad
     *         Request) if the company is not valid, or with status 500 (Internal Server Error) if the company couldn't
     *         be updated
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PutMapping("/companies")
    @Timed
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) throws URISyntaxException {
        log.debug("REST request to update Company : {}", company);
        if (company.getId() == null) {
            // return createCompany(company);
        }
        
        Company result = companyService.save(company);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, company.getId().toString()))
                .body(result);
    }
    
    /**
     * GET /companies : get all the companies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of companies in body
     */
    @GetMapping("/companies")
    @Timed
    public List<Company> getAllCompanies() {
        log.debug("REST request to get all Companies");
        return companyService.findAll();
    }
    
    /**
     * GET /companies/:id : get the "id" company.
     *
     * @param id
     *            the id of the company to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the company, or with status 404 (Not Found)
     */
    @GetMapping("/companies/{id}")
    @Timed
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        log.debug("REST request to get Company : {}", id);
        Company company = companyService.findOne(id);
        return null;// ResponseUtil.wrapOrNotFound(Optional.ofNullable(company));
    }
    
    /**
     * DELETE /companies/:id : delete the "id" company.
     *
     * @param id
     *            the id of the company to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/companies/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        log.debug("REST request to delete Company : {}", id);
        companyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
