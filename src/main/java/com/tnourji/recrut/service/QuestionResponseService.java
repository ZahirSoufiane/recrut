package com.tnourji.recrut.service;

import java.util.List;

import com.tnourji.recrut.model.QuestionResponse;

/**
 * Service Interface for managing QuestionResponse.
 */
public interface QuestionResponseService {

    /**
     * Save a questionResponse.
     *
     * @param questionResponse the entity to save
     * @return the persisted entity
     */
    QuestionResponse save(QuestionResponse questionResponse);

    /**
     * Get all the questionResponses.
     *
     * @return the list of entities
     */
    List<QuestionResponse> findAll();

    /**
     * Get the "id" questionResponse.
     *
     * @param id the id of the entity
     * @return the entity
     */
    QuestionResponse findOne(Long id);

    /**
     * Delete the "id" questionResponse.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
