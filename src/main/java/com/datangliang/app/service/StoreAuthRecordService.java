package com.datangliang.app.service;

import com.datangliang.app.domain.StoreAuthRecord;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StoreAuthRecord.
 */
public interface StoreAuthRecordService {

    /**
     * Save a storeAuthRecord.
     *
     * @param storeAuthRecord the entity to save
     * @return the persisted entity
     */
    StoreAuthRecord save(StoreAuthRecord storeAuthRecord);

    /**
     * Get all the storeAuthRecords.
     *
     * @return the list of entities
     */
    List<StoreAuthRecord> findAll();


    /**
     * Get the "id" storeAuthRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StoreAuthRecord> findOne(Long id);

    /**
     * Delete the "id" storeAuthRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
