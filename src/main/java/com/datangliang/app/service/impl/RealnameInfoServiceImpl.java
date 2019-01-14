package com.datangliang.app.service.impl;

import com.datangliang.app.service.RealnameInfoService;
import com.datangliang.app.domain.RealnameInfo;
import com.datangliang.app.repository.RealnameInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing RealnameInfo.
 */
@Service
@Transactional
public class RealnameInfoServiceImpl implements RealnameInfoService {

    private final Logger log = LoggerFactory.getLogger(RealnameInfoServiceImpl.class);

    private final RealnameInfoRepository realnameInfoRepository;

    public RealnameInfoServiceImpl(RealnameInfoRepository realnameInfoRepository) {
        this.realnameInfoRepository = realnameInfoRepository;
    }

    /**
     * Save a realnameInfo.
     *
     * @param realnameInfo the entity to save
     * @return the persisted entity
     */
    @Override
    public RealnameInfo save(RealnameInfo realnameInfo) {
        log.debug("Request to save RealnameInfo : {}", realnameInfo);
        return realnameInfoRepository.save(realnameInfo);
    }

    /**
     * Get all the realnameInfos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RealnameInfo> findAll() {
        log.debug("Request to get all RealnameInfos");
        return realnameInfoRepository.findAll();
    }


    /**
     * Get one realnameInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RealnameInfo> findOne(Long id) {
        log.debug("Request to get RealnameInfo : {}", id);
        return realnameInfoRepository.findById(id);
    }

    /**
     * Delete the realnameInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RealnameInfo : {}", id);
        realnameInfoRepository.deleteById(id);
    }
}
