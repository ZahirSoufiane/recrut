package com.tnourji.recrut.service;

import java.util.List;

import com.tnourji.recrut.model.Quizz;

/**
 * Service Interface for managing Quizz.
 */
public interface QuizzService {

    /**
     * Save a quizz.
     *
     * @param quizz the entity to save
     * @return the persisted entity
     */
    Quizz save(Quizz quizz);

    /**
     * Get all the quizzes.
     *
     * @return the list of entities
     */
    List<Quizz> findAll();

    /**
     * Get the "id" quizz.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Quizz findOne(Long id);

    /**
     * Delete the "id" quizz.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
