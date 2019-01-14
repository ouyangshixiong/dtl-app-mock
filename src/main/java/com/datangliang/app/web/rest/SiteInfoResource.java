package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.SiteInfo;
import com.datangliang.app.service.SiteInfoService;
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
 * REST controller for managing SiteInfo.
 */
@RestController
@RequestMapping("/api")
public class SiteInfoResource {

    private final Logger log = LoggerFactory.getLogger(SiteInfoResource.class);

    private static final String ENTITY_NAME = "siteInfo";

    private final SiteInfoService siteInfoService;

    public SiteInfoResource(SiteInfoService siteInfoService) {
        this.siteInfoService = siteInfoService;
    }

    /**
     * POST  /site-infos : Create a new siteInfo.
     *
     * @param siteInfo the siteInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new siteInfo, or with status 400 (Bad Request) if the siteInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/site-infos")
    @Timed
    public ResponseEntity<SiteInfo> createSiteInfo(@RequestBody SiteInfo siteInfo) throws URISyntaxException {
        log.debug("REST request to save SiteInfo : {}", siteInfo);
        if (siteInfo.getId() != null) {
            throw new BadRequestAlertException("A new siteInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SiteInfo result = siteInfoService.save(siteInfo);
        return ResponseEntity.created(new URI("/api/site-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /site-infos : Updates an existing siteInfo.
     *
     * @param siteInfo the siteInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated siteInfo,
     * or with status 400 (Bad Request) if the siteInfo is not valid,
     * or with status 500 (Internal Server Error) if the siteInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/site-infos")
    @Timed
    public ResponseEntity<SiteInfo> updateSiteInfo(@RequestBody SiteInfo siteInfo) throws URISyntaxException {
        log.debug("REST request to update SiteInfo : {}", siteInfo);
        if (siteInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SiteInfo result = siteInfoService.save(siteInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, siteInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /site-infos : get all the siteInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of siteInfos in body
     */
    @GetMapping("/site-infos")
    @Timed
    public List<SiteInfo> getAllSiteInfos() {
        log.debug("REST request to get all SiteInfos");
        return siteInfoService.findAll();
    }

    /**
     * GET  /site-infos/:id : get the "id" siteInfo.
     *
     * @param id the id of the siteInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the siteInfo, or with status 404 (Not Found)
     */
    @GetMapping("/site-infos/{id}")
    @Timed
    public ResponseEntity<SiteInfo> getSiteInfo(@PathVariable Long id) {
        log.debug("REST request to get SiteInfo : {}", id);
        Optional<SiteInfo> siteInfo = siteInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(siteInfo);
    }

    /**
     * DELETE  /site-infos/:id : delete the "id" siteInfo.
     *
     * @param id the id of the siteInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/site-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteSiteInfo(@PathVariable Long id) {
        log.debug("REST request to delete SiteInfo : {}", id);
        siteInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
