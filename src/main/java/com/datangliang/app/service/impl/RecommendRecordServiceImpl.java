package com.datangliang.app.service.impl;

import com.datangliang.app.service.RecommendRecordService;
import com.datangliang.app.domain.RecommendRecord;
import com.datangliang.app.repository.RecommendRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing RecommendRecord.
 */
@Service
@Transactional
public class RecommendRecordServiceImpl implements RecommendRecordService {

    private final Logger log = LoggerFactory.getLogger(RecommendRecordServiceImpl.class);

    private final RecommendRecordRepository recommendRecordRepository;

    public RecommendRecordServiceImpl(RecommendRecordRepository recommendRecordRepository) {
        this.recommendRecordRepository = recommendRecordRepository;
    }

    /**
     * Save a recommendRecord.
     *
     * @param recommendRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public RecommendRecord save(RecommendRecord recommendRecord) {
        log.debug("Request to save RecommendRecord : {}", recommendRecord);
        return recommendRecordRepository.save(recommendRecord);
    }

    /**
     * Get all the recommendRecords.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RecommendRecord> findAll() {
        log.debug("Request to get all RecommendRecords");
        return recommendRecordRepository.findAll();
    }


    /**
     * Get one recommendRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RecommendRecord> findOne(Long id) {
        log.debug("Request to get RecommendRecord : {}", id);
        return recommendRecordRepository.findById(id);
    }

    /**
     * Delete the recommendRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RecommendRecord : {}", id);
        recommendRecordRepository.deleteById(id);
    }
}
