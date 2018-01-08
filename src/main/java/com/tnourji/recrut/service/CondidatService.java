package com.tnourji.recrut.service;

import java.util.List;

import com.tnourji.recrut.model.Condidat;

/**
 * Service Interface for managing Condidat.
 */
public interface CondidatService {

    /**
     * Save a condidat.
     *
     * @param condidat the entity to save
     * @return the persisted entity
     */
    Condidat save(Condidat condidat);

    /**
     * Get all the condidats.
     *
     * @return the list of entities
     */
    List<Condidat> findAll();

    /**
     * Get the "id" condidat.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Condidat findOne(Long id);

    /**
     * Delete the "id" condidat.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
