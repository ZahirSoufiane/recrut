package com.tnourji.recrut.service.impl;

import com.tnourji.recrut.model.Ad;
import com.tnourji.recrut.repository.AdRepository;
import com.tnourji.recrut.service.AdService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Ad.
 */
@Service
@Transactional
public class AdServiceImpl implements AdService{

    private final Logger log = LoggerFactory.getLogger(AdServiceImpl.class);

    private final AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    /**
     * Save a ad.
     *
     * @param ad the entity to save
     * @return the persisted entity
     */
    @Override
    public Ad save(Ad ad) {
        log.debug("Request to save Ad : {}", ad);
        return adRepository.save(ad);
    }

    /**
     * Get all the ads.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Ad> findAll() {
        log.debug("Request to get all Ads");
        return adRepository.findAll();
    }

    /**
     * Get one ad by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Ad findOne(Long id) {
        log.debug("Request to get Ad : {}", id);
        return adRepository.findOne(id);
    }

    /**
     * Delete the ad by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ad : {}", id);
        adRepository.delete(id);
    }
}
