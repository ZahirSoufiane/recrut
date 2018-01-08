package com.tnourji.recrut.service;

import java.util.List;

import com.tnourji.recrut.model.QuizzResultCandidate;

/**
 * Service Interface for managing QuizzResultCandidate.
 */
public interface QuizzResultCandidateService {

    /**
     * Save a quizzResultCandidate.
     *
     * @param quizzResultCandidate the entity to save
     * @return the persisted entity
     */
    QuizzResultCandidate save(QuizzResultCandidate quizzResultCandidate);

    /**
     * Get all the quizzResultCandidates.
     *
     * @return the list of entities
     */
    List<QuizzResultCandidate> findAll();

    /**
     * Get the "id" quizzResultCandidate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuizzResultCandidate findOne(Long id);

    /**
     * Delete the "id" quizzResultCandidate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
