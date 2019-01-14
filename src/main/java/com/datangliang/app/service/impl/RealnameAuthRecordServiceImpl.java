package com.datangliang.app.service.impl;

import com.datangliang.app.service.RealnameAuthRecordService;
import com.datangliang.app.domain.RealnameAuthRecord;
import com.datangliang.app.repository.RealnameAuthRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing RealnameAuthRecord.
 */
@Service
@Transactional
public class RealnameAuthRecordServiceImpl implements RealnameAuthRecordService {

    private final Logger log = LoggerFactory.getLogger(RealnameAuthRecordServiceImpl.class);

    private final RealnameAuthRecordRepository realnameAuthRecordRepository;

    public RealnameAuthRecordServiceImpl(RealnameAuthRecordRepository realnameAuthRecordRepository) {
        this.realnameAuthRecordRepository = realnameAuthRecordRepository;
    }

    /**
     * Save a realnameAuthRecord.
     *
     * @param realnameAuthRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public RealnameAuthRecord save(RealnameAuthRecord realnameAuthRecord) {
        log.debug("Request to save RealnameAuthRecord : {}", realnameAuthRecord);
        return realnameAuthRecordRepository.save(realnameAuthRecord);
    }

    /**
     * Get all the realnameAuthRecords.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RealnameAuthRecord> findAll() {
        log.debug("Request to get all RealnameAuthRecords");
        return realnameAuthRecordRepository.findAll();
    }


    /**
     * Get one realnameAuthRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RealnameAuthRecord> findOne(Long id) {
        log.debug("Request to get RealnameAuthRecord : {}", id);
        return realnameAuthRecordRepository.findById(id);
    }

    /**
     * Delete the realnameAuthRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RealnameAuthRecord : {}", id);
        realnameAuthRecordRepository.deleteById(id);
    }
}
