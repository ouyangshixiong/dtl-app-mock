package com.datangliang.app.service;

import com.datangliang.app.domain.EnterpriseAuthRecord;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EnterpriseAuthRecord.
 */
public interface EnterpriseAuthRecordService {

    /**
     * Save a enterpriseAuthRecord.
     *
     * @param enterpriseAuthRecord the entity to save
     * @return the persisted entity
     */
    EnterpriseAuthRecord save(EnterpriseAuthRecord enterpriseAuthRecord);

    /**
     * Get all the enterpriseAuthRecords.
     *
     * @return the list of entities
     */
    List<EnterpriseAuthRecord> findAll();


    /**
     * Get the "id" enterpriseAuthRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnterpriseAuthRecord> findOne(Long id);

    /**
     * Delete the "id" enterpriseAuthRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
