package com.datangliang.app.service;

import com.datangliang.app.domain.SiteAuthRecord;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SiteAuthRecord.
 */
public interface SiteAuthRecordService {

    /**
     * Save a siteAuthRecord.
     *
     * @param siteAuthRecord the entity to save
     * @return the persisted entity
     */
    SiteAuthRecord save(SiteAuthRecord siteAuthRecord);

    /**
     * Get all the siteAuthRecords.
     *
     * @return the list of entities
     */
    List<SiteAuthRecord> findAll();


    /**
     * Get the "id" siteAuthRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SiteAuthRecord> findOne(Long id);

    /**
     * Delete the "id" siteAuthRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
