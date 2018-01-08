package com.tnourji.recrut.controller;

import com.codahale.metrics.annotation.Timed;
import com.tnourji.recrut.util.HeaderUtil;
import com.tnourji.recrut.exception.BadRequestAlertException;
import com.tnourji.recrut.model.Condidat;
import com.tnourji.recrut.service.CondidatService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

/**
 * REST controller for managing Condidat.
 */
@RestController
@RequestMapping("/api")
public class CondidatResource {

    private final Logger log = LoggerFactory.getLogger(CondidatResource.class);

    private static final String ENTITY_NAME = "condidat";

    private final CondidatService condidatService;

    public CondidatResource(CondidatService condidatService) {
        this.condidatService = condidatService;
    }

    
   
    /**
     * POST  /condidats : Create a new condidat.
     *
     * @param condidat the condidat to create
     * @return the ResponseEntity with status 201 (Created) and with body the new condidat, or with status 400 (Bad Request) if the condidat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/condidats")
    @Timed
    public ResponseEntity<Condidat> createCondidat(@RequestBody Condidat condidat) throws URISyntaxException {
        log.debug("REST request to save Condidat : {}", condidat);
        if (condidat.getId() != null) {
            throw new BadRequestAlertException("A new condidat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Condidat result = condidatService.save(condidat);
        return ResponseEntity.created(new URI("/api/condidats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /condidats : Updates an existing condidat.
     *
     * @param condidat the condidat to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated condidat,
     * or with status 400 (Bad Request) if the condidat is not valid,
     * or with status 500 (Internal Server Error) if the condidat couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/condidats")
    @Timed
    public ResponseEntity<Condidat> updateCondidat(@RequestBody Condidat condidat) throws URISyntaxException {
        log.debug("REST request to update Condidat : {}", condidat);
        if (condidat.getId() == null) {
            return createCondidat(condidat);
        }
        Condidat result = condidatService.save(condidat);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, condidat.getId().toString()))
            .body(result);
    }

    /**
     * GET  /condidats : get all the condidats.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of condidats in body
     */
    @GetMapping("/condidats")
    @Timed
    public List<Condidat> getAllCondidats() {
        log.debug("REST request to get all Condidats");
        return condidatService.findAll();
        }

    /**
     * GET  /condidats/:id : get the "id" condidat.
     *
     * @param id the id of the condidat to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the condidat, or with status 404 (Not Found)
     */
    @GetMapping("/condidats/{id}")
    @Timed
    public Condidat getCondidat(@PathVariable Long id) {
        log.debug("REST request to get Condidat : {}", id);
        Condidat condidat = condidatService.findOne(id);
        if(condidat!=null){
        return condidat;//ResponseUtil.wrapOrNotFound(Optional.ofNullable(condidat));
        }
        else throw new RuntimeException();
    }

    /**
     * DELETE  /condidats/:id : delete the "id" condidat.
     *
     * @param id the id of the condidat to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/condidats/{id}")
    @Timed
    public ResponseEntity<Void> deleteCondidat(@PathVariable Long id) {
        log.debug("REST request to delete Condidat : {}", id);
        condidatService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
