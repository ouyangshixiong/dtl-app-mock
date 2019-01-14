package com.datangliang.app.service;

import com.datangliang.app.domain.RecommendRecord;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RecommendRecord.
 */
public interface RecommendRecordService {

    /**
     * Save a recommendRecord.
     *
     * @param recommendRecord the entity to save
     * @return the persisted entity
     */
    RecommendRecord save(RecommendRecord recommendRecord);

    /**
     * Get all the recommendRecords.
     *
     * @return the list of entities
     */
    List<RecommendRecord> findAll();


    /**
     * Get the "id" recommendRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RecommendRecord> findOne(Long id);

    /**
     * Delete the "id" recommendRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
