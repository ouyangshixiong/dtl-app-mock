package com.datangliang.app.service;

import com.datangliang.app.domain.BankcardInfo;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BankcardInfo.
 */
public interface BankcardInfoService {

    /**
     * Save a bankcardInfo.
     *
     * @param bankcardInfo the entity to save
     * @return the persisted entity
     */
    BankcardInfo save(BankcardInfo bankcardInfo);

    /**
     * Get all the bankcardInfos.
     *
     * @return the list of entities
     */
    List<BankcardInfo> findAll();


    /**
     * Get the "id" bankcardInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BankcardInfo> findOne(Long id);

    /**
     * Delete the "id" bankcardInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
