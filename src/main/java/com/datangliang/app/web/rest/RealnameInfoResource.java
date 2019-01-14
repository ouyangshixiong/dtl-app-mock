package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.RealnameInfo;
import com.datangliang.app.service.RealnameInfoService;
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
 * REST controller for managing RealnameInfo.
 */
@RestController
@RequestMapping("/api")
public class RealnameInfoResource {

    private final Logger log = LoggerFactory.getLogger(RealnameInfoResource.class);

    private static final String ENTITY_NAME = "realnameInfo";

    private final RealnameInfoService realnameInfoService;

    public RealnameInfoResource(RealnameInfoService realnameInfoService) {
        this.realnameInfoService = realnameInfoService;
    }

    /**
     * POST  /realname-infos : Create a new realnameInfo.
     *
     * @param realnameInfo the realnameInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new realnameInfo, or with status 400 (Bad Request) if the realnameInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/realname-infos")
    @Timed
    public ResponseEntity<RealnameInfo> createRealnameInfo(@RequestBody RealnameInfo realnameInfo) throws URISyntaxException {
        log.debug("REST request to save RealnameInfo : {}", realnameInfo);
        if (realnameInfo.getId() != null) {
            throw new BadRequestAlertException("A new realnameInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RealnameInfo result = realnameInfoService.save(realnameInfo);
        return ResponseEntity.created(new URI("/api/realname-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /realname-infos : Updates an existing realnameInfo.
     *
     * @param realnameInfo the realnameInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated realnameInfo,
     * or with status 400 (Bad Request) if the realnameInfo is not valid,
     * or with status 500 (Internal Server Error) if the realnameInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/realname-infos")
    @Timed
    public ResponseEntity<RealnameInfo> updateRealnameInfo(@RequestBody RealnameInfo realnameInfo) throws URISyntaxException {
        log.debug("REST request to update RealnameInfo : {}", realnameInfo);
        if (realnameInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RealnameInfo result = realnameInfoService.save(realnameInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, realnameInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /realname-infos : get all the realnameInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of realnameInfos in body
     */
    @GetMapping("/realname-infos")
    @Timed
    public List<RealnameInfo> getAllRealnameInfos() {
        log.debug("REST request to get all RealnameInfos");
        return realnameInfoService.findAll();
    }

    /**
     * GET  /realname-infos/:id : get the "id" realnameInfo.
     *
     * @param id the id of the realnameInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the realnameInfo, or with status 404 (Not Found)
     */
    @GetMapping("/realname-infos/{id}")
    @Timed
    public ResponseEntity<RealnameInfo> getRealnameInfo(@PathVariable Long id) {
        log.debug("REST request to get RealnameInfo : {}", id);
        Optional<RealnameInfo> realnameInfo = realnameInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(realnameInfo);
    }

    /**
     * DELETE  /realname-infos/:id : delete the "id" realnameInfo.
     *
     * @param id the id of the realnameInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/realname-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteRealnameInfo(@PathVariable Long id) {
        log.debug("REST request to delete RealnameInfo : {}", id);
        realnameInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
