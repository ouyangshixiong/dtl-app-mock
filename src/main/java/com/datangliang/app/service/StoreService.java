package com.datangliang.app.service;

import com.datangliang.app.domain.Store;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Store.
 */
public interface StoreService {

    /**
     * Save a store.
     *
     * @param store the entity to save
     * @return the persisted entity
     */
    Store save(Store store);

    /**
     * Get all the stores.
     *
     * @return the list of entities
     */
    List<Store> findAll();


    /**
     * Get the "id" store.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Store> findOne(Long id);

    /**
     * Delete the "id" store.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
