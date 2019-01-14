package com.datangliang.app.service;

import com.datangliang.app.domain.UserLevel;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing UserLevel.
 */
public interface UserLevelService {

    /**
     * Save a userLevel.
     *
     * @param userLevel the entity to save
     * @return the persisted entity
     */
    UserLevel save(UserLevel userLevel);

    /**
     * Get all the userLevels.
     *
     * @return the list of entities
     */
    List<UserLevel> findAll();


    /**
     * Get the "id" userLevel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserLevel> findOne(Long id);

    /**
     * Delete the "id" userLevel.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
