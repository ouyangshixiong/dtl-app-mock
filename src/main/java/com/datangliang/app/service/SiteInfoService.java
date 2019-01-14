package com.datangliang.app.service;

import com.datangliang.app.domain.SiteInfo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SiteInfo.
 */
public interface SiteInfoService {

    /**
     * Save a siteInfo.
     *
     * @param siteInfo the entity to save
     * @return the persisted entity
     */
    SiteInfo save(SiteInfo siteInfo);

    /**
     * Get all the siteInfos.
     *
     * @return the list of entities
     */
    List<SiteInfo> findAll();


    /**
     * Get the "id" siteInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SiteInfo> findOne(Long id);

    /**
     * Delete the "id" siteInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
