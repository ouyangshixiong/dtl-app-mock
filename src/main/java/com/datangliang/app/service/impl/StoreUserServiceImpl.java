package com.datangliang.app.service.impl;

import com.datangliang.app.service.StoreUserService;
import com.datangliang.app.domain.StoreUser;
import com.datangliang.app.repository.StoreUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing StoreUser.
 */
@Service
@Transactional
public class StoreUserServiceImpl implements StoreUserService {

    private final Logger log = LoggerFactory.getLogger(StoreUserServiceImpl.class);

    private final StoreUserRepository storeUserRepository;

    public StoreUserServiceImpl(StoreUserRepository storeUserRepository) {
        this.storeUserRepository = storeUserRepository;
    }

    /**
     * Save a storeUser.
     *
     * @param storeUser the entity to save
     * @return the persisted entity
     */
    @Override
    public StoreUser save(StoreUser storeUser) {
        log.debug("Request to save StoreUser : {}", storeUser);
        return storeUserRepository.save(storeUser);
    }

    /**
     * Get all the storeUsers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StoreUser> findAll() {
        log.debug("Request to get all StoreUsers");
        return storeUserRepository.findAll();
    }


    /**
     * Get one storeUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StoreUser> findOne(Long id) {
        log.debug("Request to get StoreUser : {}", id);
        return storeUserRepository.findById(id);
    }

    /**
     * Delete the storeUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StoreUser : {}", id);
        storeUserRepository.deleteById(id);
    }
}
