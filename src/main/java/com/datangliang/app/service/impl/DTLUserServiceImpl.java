package com.datangliang.app.service.impl;

import com.datangliang.app.service.DTLUserService;
import com.datangliang.app.domain.DTLUser;
import com.datangliang.app.repository.DTLUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing DTLUser.
 */
@Service
@Transactional
public class DTLUserServiceImpl implements DTLUserService {

    private final Logger log = LoggerFactory.getLogger(DTLUserServiceImpl.class);

    private final DTLUserRepository dTLUserRepository;

    public DTLUserServiceImpl(DTLUserRepository dTLUserRepository) {
        this.dTLUserRepository = dTLUserRepository;
    }

    /**
     * Save a dTLUser.
     *
     * @param dTLUser the entity to save
     * @return the persisted entity
     */
    @Override
    public DTLUser save(DTLUser dTLUser) {
        log.debug("Request to save DTLUser : {}", dTLUser);
        return dTLUserRepository.save(dTLUser);
    }

    /**
     * Get all the dTLUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DTLUser> findAll(Pageable pageable) {
        log.debug("Request to get all DTLUsers");
        return dTLUserRepository.findAll(pageable);
    }


    /**
     * Get one dTLUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DTLUser> findOne(Long id) {
        log.debug("Request to get DTLUser : {}", id);
        return dTLUserRepository.findById(id);
    }

    /**
     * Delete the dTLUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DTLUser : {}", id);
        dTLUserRepository.deleteById(id);
    }
}
