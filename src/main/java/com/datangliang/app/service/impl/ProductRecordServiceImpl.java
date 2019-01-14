package com.datangliang.app.service.impl;

import com.datangliang.app.service.ProductRecordService;
import com.datangliang.app.domain.ProductRecord;
import com.datangliang.app.repository.ProductRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ProductRecord.
 */
@Service
@Transactional
public class ProductRecordServiceImpl implements ProductRecordService {

    private final Logger log = LoggerFactory.getLogger(ProductRecordServiceImpl.class);

    private final ProductRecordRepository productRecordRepository;

    public ProductRecordServiceImpl(ProductRecordRepository productRecordRepository) {
        this.productRecordRepository = productRecordRepository;
    }

    /**
     * Save a productRecord.
     *
     * @param productRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductRecord save(ProductRecord productRecord) {
        log.debug("Request to save ProductRecord : {}", productRecord);
        return productRecordRepository.save(productRecord);
    }

    /**
     * Get all the productRecords.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductRecord> findAll() {
        log.debug("Request to get all ProductRecords");
        return productRecordRepository.findAll();
    }


    /**
     * Get one productRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductRecord> findOne(Long id) {
        log.debug("Request to get ProductRecord : {}", id);
        return productRecordRepository.findById(id);
    }

    /**
     * Delete the productRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductRecord : {}", id);
        productRecordRepository.deleteById(id);
    }
}
