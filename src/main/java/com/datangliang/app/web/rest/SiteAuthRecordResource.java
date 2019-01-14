package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.SiteAuthRecord;
import com.datangliang.app.service.SiteAuthRecordService;
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
 * REST controller for managing SiteAuthRecord.
 */
@RestController
@RequestMapping("/api")
public class SiteAuthRecordResource {

    private final Logger log = LoggerFactory.getLogger(SiteAuthRecordResource.class);

    private static final String ENTITY_NAME = "siteAuthRecord";

    private final SiteAuthRecordService siteAuthRecordService;

    public SiteAuthRecordResource(SiteAuthRecordService siteAuthRecordService) {
        this.siteAuthRecordService = siteAuthRecordService;
    }

    /**
     * POST  /site-auth-records : Create a new siteAuthRecord.
     *
     * @param siteAuthRecord the siteAuthRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new siteAuthRecord, or with status 400 (Bad Request) if the siteAuthRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/site-auth-records")
    @Timed
    public ResponseEntity<SiteAuthRecord> createSiteAuthRecord(@RequestBody SiteAuthRecord siteAuthRecord) throws URISyntaxException {
        log.debug("REST request to save SiteAuthRecord : {}", siteAuthRecord);
        if (siteAuthRecord.getId() != null) {
            throw new BadRequestAlertException("A new siteAuthRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SiteAuthRecord result = siteAuthRecordService.save(siteAuthRecord);
        return ResponseEntity.created(new URI("/api/site-auth-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /site-auth-records : Updates an existing siteAuthRecord.
     *
     * @param siteAuthRecord the siteAuthRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated siteAuthRecord,
     * or with status 400 (Bad Request) if the siteAuthRecord is not valid,
     * or with status 500 (Internal Server Error) if the siteAuthRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/site-auth-records")
    @Timed
    public ResponseEntity<SiteAuthRecord> updateSiteAuthRecord(@RequestBody SiteAuthRecord siteAuthRecord) throws URISyntaxException {
        log.debug("REST request to update SiteAuthRecord : {}", siteAuthRecord);
        if (siteAuthRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SiteAuthRecord result = siteAuthRecordService.save(siteAuthRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, siteAuthRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /site-auth-records : get all the siteAuthRecords.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of siteAuthRecords in body
     */
    @GetMapping("/site-auth-records")
    @Timed
    public List<SiteAuthRecord> getAllSiteAuthRecords() {
        log.debug("REST request to get all SiteAuthRecords");
        return siteAuthRecordService.findAll();
    }

    /**
     * GET  /site-auth-records/:id : get the "id" siteAuthRecord.
     *
     * @param id the id of the siteAuthRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the siteAuthRecord, or with status 404 (Not Found)
     */
    @GetMapping("/site-auth-records/{id}")
    @Timed
    public ResponseEntity<SiteAuthRecord> getSiteAuthRecord(@PathVariable Long id) {
        log.debug("REST request to get SiteAuthRecord : {}", id);
        Optional<SiteAuthRecord> siteAuthRecord = siteAuthRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(siteAuthRecord);
    }

    /**
     * DELETE  /site-auth-records/:id : delete the "id" siteAuthRecord.
     *
     * @param id the id of the siteAuthRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/site-auth-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteSiteAuthRecord(@PathVariable Long id) {
        log.debug("REST request to delete SiteAuthRecord : {}", id);
        siteAuthRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
