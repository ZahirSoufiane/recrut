package com.tnourji.recrut.controller;

import com.codahale.metrics.annotation.Timed;
import com.tnourji.recrut.util.HeaderUtil;
import com.tnourji.recrut.exception.BadRequestAlertException;
import com.tnourji.recrut.model.Ad;
import com.tnourji.recrut.service.AdService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Ad.
 */
@RestController
@RequestMapping("/api")
public class AdResource {

    private final Logger log = LoggerFactory.getLogger(AdResource.class);

    private static final String ENTITY_NAME = "ad";

    private final AdService adService;

    public AdResource(AdService adService) {
        this.adService = adService;
    }

    /**
     * POST  /ads : Create a new ad.
     *
     * @param ad the ad to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ad, or with status 400 (Bad Request) if the ad has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ads")
    @Timed
    public ResponseEntity<Ad> createAd(@RequestBody Ad ad) throws URISyntaxException {
        log.debug("REST request to save Ad : {}", ad);
        if (ad.getId() != null) {
            throw new BadRequestAlertException("A new ad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ad result = adService.save(ad);
        return ResponseEntity.created(new URI("/api/ads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    

    /**
     * PUT  /ads : Updates an existing ad.
     *
     * @param ad the ad to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ad,
     * or with status 400 (Bad Request) if the ad is not valid,
     * or with status 500 (Internal Server Error) if the ad couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ads")
    @Timed
    public ResponseEntity<Ad> updateAd(@RequestBody Ad ad) throws URISyntaxException {
        log.debug("REST request to update Ad : {}", ad);
        if (ad.getId() == null) {
            return createAd(ad);
        }
        Ad result = adService.save(ad);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ad.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ads : get all the ads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ads in body
     */
    @GetMapping("/ads")
    @Timed
    public List<Ad> getAllAds() {
        log.debug("REST request to get all Ads");
        return adService.findAll();
        }

    /**
     * GET  /ads/:id : get the "id" ad.
     *
     * @param id the id of the ad to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ad, or with status 404 (Not Found)
     */
    @GetMapping("/ads/{id}")
    @Timed
    public ResponseEntity<Ad> getAd(@PathVariable Long id) {
        log.debug("REST request to get Ad : {}", id);
        Ad ad = adService.findOne(id);
        return null;//ResponseUtil.wrapOrNotFound(Optional.ofNullable(ad));
    }

    /**
     * DELETE  /ads/:id : delete the "id" ad.
     *
     * @param id the id of the ad to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ads/{id}")
    @Timed
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        log.debug("REST request to delete Ad : {}", id);
        adService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
