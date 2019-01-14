package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.EnterpriseAuthRecord;
import com.datangliang.app.service.EnterpriseAuthRecordService;
import com.datangliang.app.web.rest.errors.BadRequestAlertException;
import com.datangliang.app.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EnterpriseAuthRecord.
 */
@RestController
@RequestMapping("/api")
public class EnterpriseAuthRecordResource {

    private final Logger log = LoggerFactory.getLogger(EnterpriseAuthRecordResource.class);

    private static final String ENTITY_NAME = "enterpriseAuthRecord";

    private final EnterpriseAuthRecordService enterpriseAuthRecordService;

    public EnterpriseAuthRecordResource(EnterpriseAuthRecordService enterpriseAuthRecordService) {
        this.enterpriseAuthRecordService = enterpriseAuthRecordService;
    }

    /**
     * POST  /enterprise-auth-records : Create a new enterpriseAuthRecord.
     *
     * @param enterpriseAuthRecord the enterpriseAuthRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new enterpriseAuthRecord, or with status 400 (Bad Request) if the enterpriseAuthRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/enterprise-auth-records")
    @Timed
    public ResponseEntity<EnterpriseAuthRecord> createEnterpriseAuthRecord(@RequestBody EnterpriseAuthRecord enterpriseAuthRecord) throws URISyntaxException {
        log.debug("REST request to save EnterpriseAuthRecord : {}", enterpriseAuthRecord);
        if (enterpriseAuthRecord.getId() != null) {
            throw new BadRequestAlertException("A new enterpriseAuthRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnterpriseAuthRecord result = enterpriseAuthRecordService.save(enterpriseAuthRecord);
        return ResponseEntity.created(new URI("/api/enterprise-auth-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /enterprise-auth-records : Updates an existing enterpriseAuthRecord.
     *
     * @param enterpriseAuthRecord the enterpriseAuthRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated enterpriseAuthRecord,
     * or with status 400 (Bad Request) if the enterpriseAuthRecord is not valid,
     * or with status 500 (Internal Server Error) if the enterpriseAuthRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/enterprise-auth-records")
    @Timed
    public ResponseEntity<EnterpriseAuthRecord> updateEnterpriseAuthRecord(@RequestBody EnterpriseAuthRecord enterpriseAuthRecord) throws URISyntaxException {
        log.debug("REST request to update EnterpriseAuthRecord : {}", enterpriseAuthRecord);
        if (enterpriseAuthRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnterpriseAuthRecord result = enterpriseAuthRecordService.save(enterpriseAuthRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, enterpriseAuthRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /enterprise-auth-records : get all the enterpriseAuthRecords.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of enterpriseAuthRecords in body
     */
    @GetMapping("/enterprise-auth-records")
    @Timed
    public List<EnterpriseAuthRecord> getAllEnterpriseAuthRecords() {
        log.debug("REST request to get all EnterpriseAuthRecords");
        return enterpriseAuthRecordService.findAll();
    }

    /**
     * GET  /enterprise-auth-records/:id : get the "id" enterpriseAuthRecord.
     *
     * @param id the id of the enterpriseAuthRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the enterpriseAuthRecord, or with status 404 (Not Found)
     */
    @GetMapping("/enterprise-auth-records/{id}")
    @Timed
    public ResponseEntity<EnterpriseAuthRecord> getEnterpriseAuthRecord(@PathVariable Long id) {
        log.debug("REST request to get EnterpriseAuthRecord : {}", id);
        Optional<EnterpriseAuthRecord> enterpriseAuthRecord = enterpriseAuthRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enterpriseAuthRecord);
    }

    /**
     * DELETE  /enterprise-auth-records/:id : delete the "id" enterpriseAuthRecord.
     *
     * @param id the id of the enterpriseAuthRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/enterprise-auth-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnterpriseAuthRecord(@PathVariable Long id) {
        log.debug("REST request to delete EnterpriseAuthRecord : {}", id);
        enterpriseAuthRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
