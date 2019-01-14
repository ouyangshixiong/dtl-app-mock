package com.datangliang.app.service.impl;

import com.datangliang.app.service.EnterpriseInfoService;
import com.datangliang.app.domain.EnterpriseInfo;
import com.datangliang.app.repository.EnterpriseInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing EnterpriseInfo.
 */
@Service
@Transactional
public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {

    private final Logger log = LoggerFactory.getLogger(EnterpriseInfoServiceImpl.class);

    private final EnterpriseInfoRepository enterpriseInfoRepository;

    public EnterpriseInfoServiceImpl(EnterpriseInfoRepository enterpriseInfoRepository) {
        this.enterpriseInfoRepository = enterpriseInfoRepository;
    }

    /**
     * Save a enterpriseInfo.
     *
     * @param enterpriseInfo the entity to save
     * @return the persisted entity
     */
    @Override
    public EnterpriseInfo save(EnterpriseInfo enterpriseInfo) {
        log.debug("Request to save EnterpriseInfo : {}", enterpriseInfo);
        return enterpriseInfoRepository.save(enterpriseInfo);
    }

    /**
     * Get all the enterpriseInfos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EnterpriseInfo> findAll() {
        log.debug("Request to get all EnterpriseInfos");
        return enterpriseInfoRepository.findAll();
    }


    /**
     * Get one enterpriseInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnterpriseInfo> findOne(Long id) {
        log.debug("Request to get EnterpriseInfo : {}", id);
        return enterpriseInfoRepository.findById(id);
    }

    /**
     * Delete the enterpriseInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnterpriseInfo : {}", id);
        enterpriseInfoRepository.deleteById(id);
    }
}
