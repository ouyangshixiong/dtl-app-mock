package com.datangliang.app.service;

import com.datangliang.app.domain.ProductRecord;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ProductRecord.
 */
public interface ProductRecordService {

    /**
     * Save a productRecord.
     *
     * @param productRecord the entity to save
     * @return the persisted entity
     */
    ProductRecord save(ProductRecord productRecord);

    /**
     * Get all the productRecords.
     *
     * @return the list of entities
     */
    List<ProductRecord> findAll();


    /**
     * Get the "id" productRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProductRecord> findOne(Long id);

    /**
     * Delete the "id" productRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
