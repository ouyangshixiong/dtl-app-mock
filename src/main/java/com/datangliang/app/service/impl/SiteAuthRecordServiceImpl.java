package com.datangliang.app.service.impl;

import com.datangliang.app.service.SiteAuthRecordService;
import com.datangliang.app.domain.SiteAuthRecord;
import com.datangliang.app.repository.SiteAuthRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SiteAuthRecord.
 */
@Service
@Transactional
public class SiteAuthRecordServiceImpl implements SiteAuthRecordService {

    private final Logger log = LoggerFactory.getLogger(SiteAuthRecordServiceImpl.class);

    private final SiteAuthRecordRepository siteAuthRecordRepository;

    public SiteAuthRecordServiceImpl(SiteAuthRecordRepository siteAuthRecordRepository) {
        this.siteAuthRecordRepository = siteAuthRecordRepository;
    }

    /**
     * Save a siteAuthRecord.
     *
     * @param siteAuthRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public SiteAuthRecord save(SiteAuthRecord siteAuthRecord) {
        log.debug("Request to save SiteAuthRecord : {}", siteAuthRecord);
        return siteAuthRecordRepository.save(siteAuthRecord);
    }

    /**
     * Get all the siteAuthRecords.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SiteAuthRecord> findAll() {
        log.debug("Request to get all SiteAuthRecords");
        return siteAuthRecordRepository.findAll();
    }


    /**
     * Get one siteAuthRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SiteAuthRecord> findOne(Long id) {
        log.debug("Request to get SiteAuthRecord : {}", id);
        return siteAuthRecordRepository.findById(id);
    }

    /**
     * Delete the siteAuthRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SiteAuthRecord : {}", id);
        siteAuthRecordRepository.deleteById(id);
    }
}
