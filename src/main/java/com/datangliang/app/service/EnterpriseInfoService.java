package com.datangliang.app.service;

import com.datangliang.app.domain.EnterpriseInfo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EnterpriseInfo.
 */
public interface EnterpriseInfoService {

    /**
     * Save a enterpriseInfo.
     *
     * @param enterpriseInfo the entity to save
     * @return the persisted entity
     */
    EnterpriseInfo save(EnterpriseInfo enterpriseInfo);

    /**
     * Get all the enterpriseInfos.
     *
     * @return the list of entities
     */
    List<EnterpriseInfo> findAll();


    /**
     * Get the "id" enterpriseInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnterpriseInfo> findOne(Long id);

    /**
     * Delete the "id" enterpriseInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
