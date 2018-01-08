package com.tnourji.recrut.service;

import java.util.List;

import com.tnourji.recrut.model.User;

/**
 * Service Interface for managing Utilisateur.
 */
public interface UserService {

    /**
     * Save a utilisateur.
     *
     * @param utilisateur the entity to save
     * @return the persisted entity
     */
    User save(User utilisateur);

    /**
     * Get all the utilisateurs.
     *
     * @return the list of entities
     */
    List<User> findAll();

    /**
     * Get the "id" utilisateur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    User findOne(Long id);

    /**
     * Delete the "id" utilisateur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
