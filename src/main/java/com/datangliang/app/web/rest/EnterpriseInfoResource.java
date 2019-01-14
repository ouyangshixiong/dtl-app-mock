package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.EnterpriseInfo;
import com.datangliang.app.service.EnterpriseInfoService;
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
 * REST controller for managing EnterpriseInfo.
 */
@RestController
@RequestMapping("/api")
public class EnterpriseInfoResource {

    private final Logger log = LoggerFactory.getLogger(EnterpriseInfoResource.class);

    private static final String ENTITY_NAME = "enterpriseInfo";

    private final EnterpriseInfoService enterpriseInfoService;

    public EnterpriseInfoResource(EnterpriseInfoService enterpriseInfoService) {
        this.enterpriseInfoService = enterpriseInfoService;
    }

    /**
     * POST  /enterprise-infos : Create a new enterpriseInfo.
     *
     * @param enterpriseInfo the enterpriseInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new enterpriseInfo, or with status 400 (Bad Request) if the enterpriseInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/enterprise-infos")
    @Timed
    public ResponseEntity<EnterpriseInfo> createEnterpriseInfo(@RequestBody EnterpriseInfo enterpriseInfo) throws URISyntaxException {
        log.debug("REST request to save EnterpriseInfo : {}", enterpriseInfo);
        if (enterpriseInfo.getId() != null) {
            throw new BadRequestAlertException("A new enterpriseInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnterpriseInfo result = enterpriseInfoService.save(enterpriseInfo);
        return ResponseEntity.created(new URI("/api/enterprise-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /enterprise-infos : Updates an existing enterpriseInfo.
     *
     * @param enterpriseInfo the enterpriseInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated enterpriseInfo,
     * or with status 400 (Bad Request) if the enterpriseInfo is not valid,
     * or with status 500 (Internal Server Error) if the enterpriseInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/enterprise-infos")
    @Timed
    public ResponseEntity<EnterpriseInfo> updateEnterpriseInfo(@RequestBody EnterpriseInfo enterpriseInfo) throws URISyntaxException {
        log.debug("REST request to update EnterpriseInfo : {}", enterpriseInfo);
        if (enterpriseInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnterpriseInfo result = enterpriseInfoService.save(enterpriseInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, enterpriseInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /enterprise-infos : get all the enterpriseInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of enterpriseInfos in body
     */
    @GetMapping("/enterprise-infos")
    @Timed
    public List<EnterpriseInfo> getAllEnterpriseInfos() {
        log.debug("REST request to get all EnterpriseInfos");
        return enterpriseInfoService.findAll();
    }

    /**
     * GET  /enterprise-infos/:id : get the "id" enterpriseInfo.
     *
     * @param id the id of the enterpriseInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the enterpriseInfo, or with status 404 (Not Found)
     */
    @GetMapping("/enterprise-infos/{id}")
    @Timed
    public ResponseEntity<EnterpriseInfo> getEnterpriseInfo(@PathVariable Long id) {
        log.debug("REST request to get EnterpriseInfo : {}", id);
        Optional<EnterpriseInfo> enterpriseInfo = enterpriseInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enterpriseInfo);
    }

    /**
     * DELETE  /enterprise-infos/:id : delete the "id" enterpriseInfo.
     *
     * @param id the id of the enterpriseInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/enterprise-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnterpriseInfo(@PathVariable Long id) {
        log.debug("REST request to delete EnterpriseInfo : {}", id);
        enterpriseInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
