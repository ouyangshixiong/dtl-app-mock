package com.datangliang.app.service.impl;

import com.datangliang.app.service.EnterpriseAuthRecordService;
import com.datangliang.app.domain.EnterpriseAuthRecord;
import com.datangliang.app.repository.EnterpriseAuthRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing EnterpriseAuthRecord.
 */
@Service
@Transactional
public class EnterpriseAuthRecordServiceImpl implements EnterpriseAuthRecordService {

    private final Logger log = LoggerFactory.getLogger(EnterpriseAuthRecordServiceImpl.class);

    private final EnterpriseAuthRecordRepository enterpriseAuthRecordRepository;

    public EnterpriseAuthRecordServiceImpl(EnterpriseAuthRecordRepository enterpriseAuthRecordRepository) {
        this.enterpriseAuthRecordRepository = enterpriseAuthRecordRepository;
    }

    /**
     * Save a enterpriseAuthRecord.
     *
     * @param enterpriseAuthRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public EnterpriseAuthRecord save(EnterpriseAuthRecord enterpriseAuthRecord) {
        log.debug("Request to save EnterpriseAuthRecord : {}", enterpriseAuthRecord);
        return enterpriseAuthRecordRepository.save(enterpriseAuthRecord);
    }

    /**
     * Get all the enterpriseAuthRecords.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EnterpriseAuthRecord> findAll() {
        log.debug("Request to get all EnterpriseAuthRecords");
        return enterpriseAuthRecordRepository.findAll();
    }


    /**
     * Get one enterpriseAuthRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnterpriseAuthRecord> findOne(Long id) {
        log.debug("Request to get EnterpriseAuthRecord : {}", id);
        return enterpriseAuthRecordRepository.findById(id);
    }

    /**
     * Delete the enterpriseAuthRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnterpriseAuthRecord : {}", id);
        enterpriseAuthRecordRepository.deleteById(id);
    }
}
