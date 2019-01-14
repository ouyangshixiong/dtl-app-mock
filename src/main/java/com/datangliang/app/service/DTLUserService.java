package com.datangliang.app.service;

import com.datangliang.app.domain.DTLUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DTLUser.
 */
public interface DTLUserService {

    /**
     * Save a dTLUser.
     *
     * @param dTLUser the entity to save
     * @return the persisted entity
     */
    DTLUser save(DTLUser dTLUser);

    /**
     * Get all the dTLUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DTLUser> findAll(Pageable pageable);


    /**
     * Get the "id" dTLUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DTLUser> findOne(Long id);

    /**
     * Delete the "id" dTLUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
