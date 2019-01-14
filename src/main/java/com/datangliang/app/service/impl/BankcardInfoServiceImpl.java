package com.datangliang.app.service.impl;

import com.datangliang.app.service.BankcardInfoService;
import com.datangliang.app.domain.BankcardInfo;
import com.datangliang.app.repository.BankcardInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing BankcardInfo.
 */
@Service
@Transactional
public class BankcardInfoServiceImpl implements BankcardInfoService {

    private final Logger log = LoggerFactory.getLogger(BankcardInfoServiceImpl.class);

    private final BankcardInfoRepository bankcardInfoRepository;

    public BankcardInfoServiceImpl(BankcardInfoRepository bankcardInfoRepository) {
        this.bankcardInfoRepository = bankcardInfoRepository;
    }

    /**
     * Save a bankcardInfo.
     *
     * @param bankcardInfo the entity to save
     * @return the persisted entity
     */
    @Override
    public BankcardInfo save(BankcardInfo bankcardInfo) {
        log.debug("Request to save BankcardInfo : {}", bankcardInfo);
        return bankcardInfoRepository.save(bankcardInfo);
    }

    /**
     * Get all the bankcardInfos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankcardInfo> findAll() {
        log.debug("Request to get all BankcardInfos");
        return bankcardInfoRepository.findAll();
    }


    /**
     * Get one bankcardInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BankcardInfo> findOne(Long id) {
        log.debug("Request to get BankcardInfo : {}", id);
        return bankcardInfoRepository.findById(id);
    }

    /**
     * Delete the bankcardInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankcardInfo : {}", id);
        bankcardInfoRepository.deleteById(id);
    }
}
