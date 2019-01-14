package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.StoreAuthRecord;
import com.datangliang.app.service.StoreAuthRecordService;
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
 * REST controller for managing StoreAuthRecord.
 */
@RestController
@RequestMapping("/api")
public class StoreAuthRecordResource {

    private final Logger log = LoggerFactory.getLogger(StoreAuthRecordResource.class);

    private static final String ENTITY_NAME = "storeAuthRecord";

    private final StoreAuthRecordService storeAuthRecordService;

    public StoreAuthRecordResource(StoreAuthRecordService storeAuthRecordService) {
        this.storeAuthRecordService = storeAuthRecordService;
    }

    /**
     * POST  /store-auth-records : Create a new storeAuthRecord.
     *
     * @param storeAuthRecord the storeAuthRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new storeAuthRecord, or with status 400 (Bad Request) if the storeAuthRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/store-auth-records")
    @Timed
    public ResponseEntity<StoreAuthRecord> createStoreAuthRecord(@RequestBody StoreAuthRecord storeAuthRecord) throws URISyntaxException {
        log.debug("REST request to save StoreAuthRecord : {}", storeAuthRecord);
        if (storeAuthRecord.getId() != null) {
            throw new BadRequestAlertException("A new storeAuthRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StoreAuthRecord result = storeAuthRecordService.save(storeAuthRecord);
        return ResponseEntity.created(new URI("/api/store-auth-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /store-auth-records : Updates an existing storeAuthRecord.
     *
     * @param storeAuthRecord the storeAuthRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated storeAuthRecord,
     * or with status 400 (Bad Request) if the storeAuthRecord is not valid,
     * or with status 500 (Internal Server Error) if the storeAuthRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/store-auth-records")
    @Timed
    public ResponseEntity<StoreAuthRecord> updateStoreAuthRecord(@RequestBody StoreAuthRecord storeAuthRecord) throws URISyntaxException {
        log.debug("REST request to update StoreAuthRecord : {}", storeAuthRecord);
        if (storeAuthRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StoreAuthRecord result = storeAuthRecordService.save(storeAuthRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, storeAuthRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /store-auth-records : get all the storeAuthRecords.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of storeAuthRecords in body
     */
    @GetMapping("/store-auth-records")
    @Timed
    public List<StoreAuthRecord> getAllStoreAuthRecords() {
        log.debug("REST request to get all StoreAuthRecords");
        return storeAuthRecordService.findAll();
    }

    /**
     * GET  /store-auth-records/:id : get the "id" storeAuthRecord.
     *
     * @param id the id of the storeAuthRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the storeAuthRecord, or with status 404 (Not Found)
     */
    @GetMapping("/store-auth-records/{id}")
    @Timed
    public ResponseEntity<StoreAuthRecord> getStoreAuthRecord(@PathVariable Long id) {
        log.debug("REST request to get StoreAuthRecord : {}", id);
        Optional<StoreAuthRecord> storeAuthRecord = storeAuthRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(storeAuthRecord);
    }

    /**
     * DELETE  /store-auth-records/:id : delete the "id" storeAuthRecord.
     *
     * @param id the id of the storeAuthRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/store-auth-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteStoreAuthRecord(@PathVariable Long id) {
        log.debug("REST request to delete StoreAuthRecord : {}", id);
        storeAuthRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
