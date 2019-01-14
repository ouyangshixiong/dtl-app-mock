package com.datangliang.app.service;

import com.datangliang.app.domain.StoreUser;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StoreUser.
 */
public interface StoreUserService {

    /**
     * Save a storeUser.
     *
     * @param storeUser the entity to save
     * @return the persisted entity
     */
    StoreUser save(StoreUser storeUser);

    /**
     * Get all the storeUsers.
     *
     * @return the list of entities
     */
    List<StoreUser> findAll();


    /**
     * Get the "id" storeUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StoreUser> findOne(Long id);

    /**
     * Delete the "id" storeUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
