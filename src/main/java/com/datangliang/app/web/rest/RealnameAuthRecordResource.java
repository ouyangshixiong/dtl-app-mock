package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.RealnameAuthRecord;
import com.datangliang.app.service.RealnameAuthRecordService;
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
 * REST controller for managing RealnameAuthRecord.
 */
@RestController
@RequestMapping("/api")
public class RealnameAuthRecordResource {

    private final Logger log = LoggerFactory.getLogger(RealnameAuthRecordResource.class);

    private static final String ENTITY_NAME = "realnameAuthRecord";

    private final RealnameAuthRecordService realnameAuthRecordService;

    public RealnameAuthRecordResource(RealnameAuthRecordService realnameAuthRecordService) {
        this.realnameAuthRecordService = realnameAuthRecordService;
    }

    /**
     * POST  /realname-auth-records : Create a new realnameAuthRecord.
     *
     * @param realnameAuthRecord the realnameAuthRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new realnameAuthRecord, or with status 400 (Bad Request) if the realnameAuthRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/realname-auth-records")
    @Timed
    public ResponseEntity<RealnameAuthRecord> createRealnameAuthRecord(@RequestBody RealnameAuthRecord realnameAuthRecord) throws URISyntaxException {
        log.debug("REST request to save RealnameAuthRecord : {}", realnameAuthRecord);
        if (realnameAuthRecord.getId() != null) {
            throw new BadRequestAlertException("A new realnameAuthRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RealnameAuthRecord result = realnameAuthRecordService.save(realnameAuthRecord);
        return ResponseEntity.created(new URI("/api/realname-auth-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /realname-auth-records : Updates an existing realnameAuthRecord.
     *
     * @param realnameAuthRecord the realnameAuthRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated realnameAuthRecord,
     * or with status 400 (Bad Request) if the realnameAuthRecord is not valid,
     * or with status 500 (Internal Server Error) if the realnameAuthRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/realname-auth-records")
    @Timed
    public ResponseEntity<RealnameAuthRecord> updateRealnameAuthRecord(@RequestBody RealnameAuthRecord realnameAuthRecord) throws URISyntaxException {
        log.debug("REST request to update RealnameAuthRecord : {}", realnameAuthRecord);
        if (realnameAuthRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RealnameAuthRecord result = realnameAuthRecordService.save(realnameAuthRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, realnameAuthRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /realname-auth-records : get all the realnameAuthRecords.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of realnameAuthRecords in body
     */
    @GetMapping("/realname-auth-records")
    @Timed
    public List<RealnameAuthRecord> getAllRealnameAuthRecords() {
        log.debug("REST request to get all RealnameAuthRecords");
        return realnameAuthRecordService.findAll();
    }

    /**
     * GET  /realname-auth-records/:id : get the "id" realnameAuthRecord.
     *
     * @param id the id of the realnameAuthRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the realnameAuthRecord, or with status 404 (Not Found)
     */
    @GetMapping("/realname-auth-records/{id}")
    @Timed
    public ResponseEntity<RealnameAuthRecord> getRealnameAuthRecord(@PathVariable Long id) {
        log.debug("REST request to get RealnameAuthRecord : {}", id);
        Optional<RealnameAuthRecord> realnameAuthRecord = realnameAuthRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(realnameAuthRecord);
    }

    /**
     * DELETE  /realname-auth-records/:id : delete the "id" realnameAuthRecord.
     *
     * @param id the id of the realnameAuthRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/realname-auth-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteRealnameAuthRecord(@PathVariable Long id) {
        log.debug("REST request to delete RealnameAuthRecord : {}", id);
        realnameAuthRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
