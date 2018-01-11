package com.tnourji.recrut.service;

import java.util.List;

import com.tnourji.recrut.model.Condidate;

/**
 * Service Interface for managing Condidat.
 */
public interface CondidateService {

    /**
     * Save a condidat.
     *
     * @param condidat the entity to save
     * @return the persisted entity
     */
    Condidate save(Condidate condidat);

    /**
     * Get all the condidats.
     *
     * @return the list of entities
     */
    List<Condidate> findAll();

    /**
     * Get the "id" condidat.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Condidate findOne(Long id);

    /**
     * Delete the "id" condidat.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
