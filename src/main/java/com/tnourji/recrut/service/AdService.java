package com.tnourji.recrut.service;

import java.util.List;

import com.tnourji.recrut.model.Ad;

/**
 * Service Interface for managing Ad.
 */
public interface AdService {

    /**
     * Save a ad.
     *
     * @param ad the entity to save
     * @return the persisted entity
     */
    Ad save(Ad ad);

    /**
     * Get all the ads.
     *
     * @return the list of entities
     */
    List<Ad> findAll();

    /**
     * Get the "id" ad.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Ad findOne(Long id);

    /**
     * Delete the "id" ad.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
