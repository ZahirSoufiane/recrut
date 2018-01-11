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
import com.tnourji.recrut.bean.CondidateBean;
import com.tnourji.recrut.exception.BadRequestAlertException;
import com.tnourji.recrut.model.Address;
import com.tnourji.recrut.model.Condidate;
import com.tnourji.recrut.service.AddressService;
import com.tnourji.recrut.service.CondidateService;
import com.tnourji.recrut.util.HeaderUtil;

/**
 * REST controller for managing Condidate.
 */
@RestController
@RequestMapping("/api")
public class CondidateResource {
    
    private final Logger log = LoggerFactory.getLogger(CondidateResource.class);
    
    private static final String ENTITY_NAME = "Condidate";
    
    private final CondidateService CondidateService;
    
    private final AddressService addressService;
    
    public CondidateResource(CondidateService CondidateService, AddressService addressService) {
        this.CondidateService = CondidateService;
        this.addressService = addressService;
    }
    
    /**
     * POST /Condidates : Create a new Condidate.
     *
     * @param Condidate
     *            the Condidate to create
     * @return the ResponseEntity with status 201 (Created) and with body the new Condidate, or with status 400 (Bad
     *         Request) if the Condidate has already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/condidates")
    @Timed
    public ResponseEntity<Condidate> createCondidate(@RequestBody CondidateBean condidate) throws URISyntaxException {
        log.debug("REST request to save Condidate : {}", condidate);
        if (condidate.getId() != null) {
            throw new BadRequestAlertException("A new Condidate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        Condidate cd = new Condidate();
        Address ad = new Address();
        Condidate result;
        
        cd.setEmail(condidate.getEmail());
        cd.setLastName(condidate.getLastName());
        cd.setFirstName(condidate.getFirstName());
        cd.setPassword(condidate.getPassword());
        cd.setPhone(condidate.getPhone());
        
        // ad.setCity(condidate.getCity());
        // ad.setCountry(condidate.getCountry());
        // ad.setPostcode(condidate.getPostcode());
        // ad.setAddress1(condidate.getAddress1());
        // ad.setAddress2(condidate.getAddress2());
        //
        // cd.setAddress(ad);
        
        addressService.save(ad);
        result = CondidateService.save(cd);
        
        // Condidate result = CondidateService.save(Condidate);
        return ResponseEntity.created(new URI("/api/Condidates/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    /**
     * PUT /Condidates : Updates an existing Condidate.
     *
     * @param Condidate
     *            the Condidate to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated Condidate, or with status 400 (Bad
     *         Request) if the Condidate is not valid, or with status 500 (Internal Server Error) if the Condidate
     *         couldn't be updated
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PutMapping("/condidates")
    @Timed
    public ResponseEntity<Condidate> updateCondidate(@RequestBody Condidate Condidate) throws URISyntaxException {
        log.debug("REST request to update Condidate : {}", Condidate);
        if (Condidate.getId() == null) {
            // return createCondidate(Condidate);
        }
        Condidate result = CondidateService.save(Condidate);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Condidate.getId().toString()))
                .body(result);
    }
    
    /**
     * GET /Condidates : get all the Condidates.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of Condidates in body
     */
    @GetMapping("/condidates")
    @Timed
    public List<Condidate> getAllCondidates() {
        log.debug("REST request to get all Condidates");
        return CondidateService.findAll();
    }
    
    /**
     * GET /Condidates/:id : get the "id" Condidate.
     *
     * @param id
     *            the id of the Condidate to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the Condidate, or with status 404 (Not Found)
     */
    @GetMapping("/Condidates/{id}")
    @Timed
    public Condidate getCondidate(@PathVariable Long id) {
        log.debug("REST request to get Condidate : {}", id);
        Condidate Condidate = CondidateService.findOne(id);
        if (Condidate != null) {
            return Condidate;// ResponseUtil.wrapOrNotFound(Optional.ofNullable(Condidate));
        } else
            throw new RuntimeException();
    }
    
    /**
     * DELETE /Condidates/:id : delete the "id" Condidate.
     *
     * @param id
     *            the id of the Condidate to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/condidates/{id}")
    @Timed
    public ResponseEntity<Void> deleteCondidate(@PathVariable Long id) {
        log.debug("REST request to delete Condidate : {}", id);
        CondidateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
