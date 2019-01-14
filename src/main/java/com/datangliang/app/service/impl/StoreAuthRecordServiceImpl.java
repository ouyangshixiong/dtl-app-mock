package com.datangliang.app.service.impl;

import com.datangliang.app.service.StoreAuthRecordService;
import com.datangliang.app.domain.StoreAuthRecord;
import com.datangliang.app.repository.StoreAuthRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing StoreAuthRecord.
 */
@Service
@Transactional
public class StoreAuthRecordServiceImpl implements StoreAuthRecordService {

    private final Logger log = LoggerFactory.getLogger(StoreAuthRecordServiceImpl.class);

    private final StoreAuthRecordRepository storeAuthRecordRepository;

    public StoreAuthRecordServiceImpl(StoreAuthRecordRepository storeAuthRecordRepository) {
        this.storeAuthRecordRepository = storeAuthRecordRepository;
    }

    /**
     * Save a storeAuthRecord.
     *
     * @param storeAuthRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public StoreAuthRecord save(StoreAuthRecord storeAuthRecord) {
        log.debug("Request to save StoreAuthRecord : {}", storeAuthRecord);
        return storeAuthRecordRepository.save(storeAuthRecord);
    }

    /**
     * Get all the storeAuthRecords.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StoreAuthRecord> findAll() {
        log.debug("Request to get all StoreAuthRecords");
        return storeAuthRecordRepository.findAll();
    }


    /**
     * Get one storeAuthRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StoreAuthRecord> findOne(Long id) {
        log.debug("Request to get StoreAuthRecord : {}", id);
        return storeAuthRecordRepository.findById(id);
    }

    /**
     * Delete the storeAuthRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StoreAuthRecord : {}", id);
        storeAuthRecordRepository.deleteById(id);
    }
}
