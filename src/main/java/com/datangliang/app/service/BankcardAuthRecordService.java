package com.datangliang.app.service;

import com.datangliang.app.domain.BankcardAuthRecord;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BankcardAuthRecord.
 */
public interface BankcardAuthRecordService {

    /**
     * Save a bankcardAuthRecord.
     *
     * @param bankcardAuthRecord the entity to save
     * @return the persisted entity
     */
    BankcardAuthRecord save(BankcardAuthRecord bankcardAuthRecord);

    /**
     * Get all the bankcardAuthRecords.
     *
     * @return the list of entities
     */
    List<BankcardAuthRecord> findAll();


    /**
     * Get the "id" bankcardAuthRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BankcardAuthRecord> findOne(Long id);

    /**
     * Delete the "id" bankcardAuthRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
