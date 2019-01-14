package com.datangliang.app.service.impl;

import com.datangliang.app.service.SiteInfoService;
import com.datangliang.app.domain.SiteInfo;
import com.datangliang.app.repository.SiteInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SiteInfo.
 */
@Service
@Transactional
public class SiteInfoServiceImpl implements SiteInfoService {

    private final Logger log = LoggerFactory.getLogger(SiteInfoServiceImpl.class);

    private final SiteInfoRepository siteInfoRepository;

    public SiteInfoServiceImpl(SiteInfoRepository siteInfoRepository) {
        this.siteInfoRepository = siteInfoRepository;
    }

    /**
     * Save a siteInfo.
     *
     * @param siteInfo the entity to save
     * @return the persisted entity
     */
    @Override
    public SiteInfo save(SiteInfo siteInfo) {
        log.debug("Request to save SiteInfo : {}", siteInfo);
        return siteInfoRepository.save(siteInfo);
    }

    /**
     * Get all the siteInfos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SiteInfo> findAll() {
        log.debug("Request to get all SiteInfos");
        return siteInfoRepository.findAll();
    }


    /**
     * Get one siteInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SiteInfo> findOne(Long id) {
        log.debug("Request to get SiteInfo : {}", id);
        return siteInfoRepository.findById(id);
    }

    /**
     * Delete the siteInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SiteInfo : {}", id);
        siteInfoRepository.deleteById(id);
    }
}
