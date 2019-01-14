package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.BankcardAuthRecord;
import com.datangliang.app.service.BankcardAuthRecordService;
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
 * REST controller for managing BankcardAuthRecord.
 */
@RestController
@RequestMapping("/api")
public class BankcardAuthRecordResource {

    private final Logger log = LoggerFactory.getLogger(BankcardAuthRecordResource.class);

    private static final String ENTITY_NAME = "bankcardAuthRecord";

    private final BankcardAuthRecordService bankcardAuthRecordService;

    public BankcardAuthRecordResource(BankcardAuthRecordService bankcardAuthRecordService) {
        this.bankcardAuthRecordService = bankcardAuthRecordService;
    }

    /**
     * POST  /bankcard-auth-records : Create a new bankcardAuthRecord.
     *
     * @param bankcardAuthRecord the bankcardAuthRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bankcardAuthRecord, or with status 400 (Bad Request) if the bankcardAuthRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bankcard-auth-records")
    @Timed
    public ResponseEntity<BankcardAuthRecord> createBankcardAuthRecord(@RequestBody BankcardAuthRecord bankcardAuthRecord) throws URISyntaxException {
        log.debug("REST request to save BankcardAuthRecord : {}", bankcardAuthRecord);
        if (bankcardAuthRecord.getId() != null) {
            throw new BadRequestAlertException("A new bankcardAuthRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankcardAuthRecord result = bankcardAuthRecordService.save(bankcardAuthRecord);
        return ResponseEntity.created(new URI("/api/bankcard-auth-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bankcard-auth-records : Updates an existing bankcardAuthRecord.
     *
     * @param bankcardAuthRecord the bankcardAuthRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bankcardAuthRecord,
     * or with status 400 (Bad Request) if the bankcardAuthRecord is not valid,
     * or with status 500 (Internal Server Error) if the bankcardAuthRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bankcard-auth-records")
    @Timed
    public ResponseEntity<BankcardAuthRecord> updateBankcardAuthRecord(@RequestBody BankcardAuthRecord bankcardAuthRecord) throws URISyntaxException {
        log.debug("REST request to update BankcardAuthRecord : {}", bankcardAuthRecord);
        if (bankcardAuthRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BankcardAuthRecord result = bankcardAuthRecordService.save(bankcardAuthRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bankcardAuthRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bankcard-auth-records : get all the bankcardAuthRecords.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bankcardAuthRecords in body
     */
    @GetMapping("/bankcard-auth-records")
    @Timed
    public List<BankcardAuthRecord> getAllBankcardAuthRecords() {
        log.debug("REST request to get all BankcardAuthRecords");
        return bankcardAuthRecordService.findAll();
    }

    /**
     * GET  /bankcard-auth-records/:id : get the "id" bankcardAuthRecord.
     *
     * @param id the id of the bankcardAuthRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bankcardAuthRecord, or with status 404 (Not Found)
     */
    @GetMapping("/bankcard-auth-records/{id}")
    @Timed
    public ResponseEntity<BankcardAuthRecord> getBankcardAuthRecord(@PathVariable Long id) {
        log.debug("REST request to get BankcardAuthRecord : {}", id);
        Optional<BankcardAuthRecord> bankcardAuthRecord = bankcardAuthRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankcardAuthRecord);
    }

    /**
     * DELETE  /bankcard-auth-records/:id : delete the "id" bankcardAuthRecord.
     *
     * @param id the id of the bankcardAuthRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bankcard-auth-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteBankcardAuthRecord(@PathVariable Long id) {
        log.debug("REST request to delete BankcardAuthRecord : {}", id);
        bankcardAuthRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
