package com.datangliang.app.service.impl;

import com.datangliang.app.service.UserLevelService;
import com.datangliang.app.domain.UserLevel;
import com.datangliang.app.repository.UserLevelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing UserLevel.
 */
@Service
@Transactional
public class UserLevelServiceImpl implements UserLevelService {

    private final Logger log = LoggerFactory.getLogger(UserLevelServiceImpl.class);

    private final UserLevelRepository userLevelRepository;

    public UserLevelServiceImpl(UserLevelRepository userLevelRepository) {
        this.userLevelRepository = userLevelRepository;
    }

    /**
     * Save a userLevel.
     *
     * @param userLevel the entity to save
     * @return the persisted entity
     */
    @Override
    public UserLevel save(UserLevel userLevel) {
        log.debug("Request to save UserLevel : {}", userLevel);
        return userLevelRepository.save(userLevel);
    }

    /**
     * Get all the userLevels.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserLevel> findAll() {
        log.debug("Request to get all UserLevels");
        return userLevelRepository.findAll();
    }


    /**
     * Get one userLevel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserLevel> findOne(Long id) {
        log.debug("Request to get UserLevel : {}", id);
        return userLevelRepository.findById(id);
    }

    /**
     * Delete the userLevel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserLevel : {}", id);
        userLevelRepository.deleteById(id);
    }
}
