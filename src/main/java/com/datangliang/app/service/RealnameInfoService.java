package com.datangliang.app.service;

import com.datangliang.app.domain.RealnameInfo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RealnameInfo.
 */
public interface RealnameInfoService {

    /**
     * Save a realnameInfo.
     *
     * @param realnameInfo the entity to save
     * @return the persisted entity
     */
    RealnameInfo save(RealnameInfo realnameInfo);

    /**
     * Get all the realnameInfos.
     *
     * @return the list of entities
     */
    List<RealnameInfo> findAll();


    /**
     * Get the "id" realnameInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RealnameInfo> findOne(Long id);

    /**
     * Delete the "id" realnameInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
