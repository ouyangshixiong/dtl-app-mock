package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.RecommendRecord;
import com.datangliang.app.service.RecommendRecordService;
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
 * REST controller for managing RecommendRecord.
 */
@RestController
@RequestMapping("/api")
public class RecommendRecordResource {

    private final Logger log = LoggerFactory.getLogger(RecommendRecordResource.class);

    private static final String ENTITY_NAME = "recommendRecord";

    private final RecommendRecordService recommendRecordService;

    public RecommendRecordResource(RecommendRecordService recommendRecordService) {
        this.recommendRecordService = recommendRecordService;
    }

    /**
     * POST  /recommend-records : Create a new recommendRecord.
     *
     * @param recommendRecord the recommendRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recommendRecord, or with status 400 (Bad Request) if the recommendRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recommend-records")
    @Timed
    public ResponseEntity<RecommendRecord> createRecommendRecord(@RequestBody RecommendRecord recommendRecord) throws URISyntaxException {
        log.debug("REST request to save RecommendRecord : {}", recommendRecord);
        if (recommendRecord.getId() != null) {
            throw new BadRequestAlertException("A new recommendRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecommendRecord result = recommendRecordService.save(recommendRecord);
        return ResponseEntity.created(new URI("/api/recommend-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recommend-records : Updates an existing recommendRecord.
     *
     * @param recommendRecord the recommendRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recommendRecord,
     * or with status 400 (Bad Request) if the recommendRecord is not valid,
     * or with status 500 (Internal Server Error) if the recommendRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recommend-records")
    @Timed
    public ResponseEntity<RecommendRecord> updateRecommendRecord(@RequestBody RecommendRecord recommendRecord) throws URISyntaxException {
        log.debug("REST request to update RecommendRecord : {}", recommendRecord);
        if (recommendRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecommendRecord result = recommendRecordService.save(recommendRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recommendRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recommend-records : get all the recommendRecords.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of recommendRecords in body
     */
    @GetMapping("/recommend-records")
    @Timed
    public List<RecommendRecord> getAllRecommendRecords() {
        log.debug("REST request to get all RecommendRecords");
        return recommendRecordService.findAll();
    }

    /**
     * GET  /recommend-records/:id : get the "id" recommendRecord.
     *
     * @param id the id of the recommendRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recommendRecord, or with status 404 (Not Found)
     */
    @GetMapping("/recommend-records/{id}")
    @Timed
    public ResponseEntity<RecommendRecord> getRecommendRecord(@PathVariable Long id) {
        log.debug("REST request to get RecommendRecord : {}", id);
        Optional<RecommendRecord> recommendRecord = recommendRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recommendRecord);
    }

    /**
     * DELETE  /recommend-records/:id : delete the "id" recommendRecord.
     *
     * @param id the id of the recommendRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recommend-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecommendRecord(@PathVariable Long id) {
        log.debug("REST request to delete RecommendRecord : {}", id);
        recommendRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
