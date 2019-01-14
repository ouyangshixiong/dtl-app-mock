package com.datangliang.app.service.impl;

import com.datangliang.app.service.IspService;
import com.datangliang.app.domain.Isp;
import com.datangliang.app.repository.IspRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Isp.
 */
@Service
@Transactional
public class IspServiceImpl implements IspService {

    private final Logger log = LoggerFactory.getLogger(IspServiceImpl.class);

    private final IspRepository ispRepository;

    public IspServiceImpl(IspRepository ispRepository) {
        this.ispRepository = ispRepository;
    }

    /**
     * Save a isp.
     *
     * @param isp the entity to save
     * @return the persisted entity
     */
    @Override
    public Isp save(Isp isp) {
        log.debug("Request to save Isp : {}", isp);
        return ispRepository.save(isp);
    }

    /**
     * Get all the isps.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Isp> findAll() {
        log.debug("Request to get all Isps");
        return ispRepository.findAll();
    }


    /**
     * Get one isp by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Isp> findOne(Long id) {
        log.debug("Request to get Isp : {}", id);
        return ispRepository.findById(id);
    }

    /**
     * Delete the isp by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Isp : {}", id);
        ispRepository.deleteById(id);
    }
}
