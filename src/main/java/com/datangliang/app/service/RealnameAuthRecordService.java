package com.datangliang.app.service;

import com.datangliang.app.domain.RealnameAuthRecord;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RealnameAuthRecord.
 */
public interface RealnameAuthRecordService {

    /**
     * Save a realnameAuthRecord.
     *
     * @param realnameAuthRecord the entity to save
     * @return the persisted entity
     */
    RealnameAuthRecord save(RealnameAuthRecord realnameAuthRecord);

    /**
     * Get all the realnameAuthRecords.
     *
     * @return the list of entities
     */
    List<RealnameAuthRecord> findAll();


    /**
     * Get the "id" realnameAuthRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RealnameAuthRecord> findOne(Long id);

    /**
     * Delete the "id" realnameAuthRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
