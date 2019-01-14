package com.datangliang.app.service;

import com.datangliang.app.domain.Isp;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Isp.
 */
public interface IspService {

    /**
     * Save a isp.
     *
     * @param isp the entity to save
     * @return the persisted entity
     */
    Isp save(Isp isp);

    /**
     * Get all the isps.
     *
     * @return the list of entities
     */
    List<Isp> findAll();


    /**
     * Get the "id" isp.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Isp> findOne(Long id);

    /**
     * Delete the "id" isp.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
