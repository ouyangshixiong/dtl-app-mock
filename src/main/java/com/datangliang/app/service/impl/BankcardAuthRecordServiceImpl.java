package com.datangliang.app.service.impl;

import com.datangliang.app.service.BankcardAuthRecordService;
import com.datangliang.app.domain.BankcardAuthRecord;
import com.datangliang.app.repository.BankcardAuthRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing BankcardAuthRecord.
 */
@Service
@Transactional
public class BankcardAuthRecordServiceImpl implements BankcardAuthRecordService {

    private final Logger log = LoggerFactory.getLogger(BankcardAuthRecordServiceImpl.class);

    private final BankcardAuthRecordRepository bankcardAuthRecordRepository;

    public BankcardAuthRecordServiceImpl(BankcardAuthRecordRepository bankcardAuthRecordRepository) {
        this.bankcardAuthRecordRepository = bankcardAuthRecordRepository;
    }

    /**
     * Save a bankcardAuthRecord.
     *
     * @param bankcardAuthRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public BankcardAuthRecord save(BankcardAuthRecord bankcardAuthRecord) {
        log.debug("Request to save BankcardAuthRecord : {}", bankcardAuthRecord);
        return bankcardAuthRecordRepository.save(bankcardAuthRecord);
    }

    /**
     * Get all the bankcardAuthRecords.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankcardAuthRecord> findAll() {
        log.debug("Request to get all BankcardAuthRecords");
        return bankcardAuthRecordRepository.findAll();
    }


    /**
     * Get one bankcardAuthRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BankcardAuthRecord> findOne(Long id) {
        log.debug("Request to get BankcardAuthRecord : {}", id);
        return bankcardAuthRecordRepository.findById(id);
    }

    /**
     * Delete the bankcardAuthRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankcardAuthRecord : {}", id);
        bankcardAuthRecordRepository.deleteById(id);
    }
}
