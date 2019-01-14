package com.datangliang.app.service.impl;

import com.datangliang.app.service.StaffService;
import com.datangliang.app.domain.Staff;
import com.datangliang.app.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Staff.
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    private final Logger log = LoggerFactory.getLogger(StaffServiceImpl.class);

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    /**
     * Save a staff.
     *
     * @param staff the entity to save
     * @return the persisted entity
     */
    @Override
    public Staff save(Staff staff) {
        log.debug("Request to save Staff : {}", staff);
        return staffRepository.save(staff);
    }

    /**
     * Get all the staff.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Staff> findAll() {
        log.debug("Request to get all Staff");
        return staffRepository.findAll();
    }


    /**
     * Get one staff by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Staff> findOne(Long id) {
        log.debug("Request to get Staff : {}", id);
        return staffRepository.findById(id);
    }

    /**
     * Delete the staff by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Staff : {}", id);
        staffRepository.deleteById(id);
    }
}
