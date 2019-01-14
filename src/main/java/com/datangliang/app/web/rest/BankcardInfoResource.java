package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.BankcardInfo;
import com.datangliang.app.service.BankcardInfoService;
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
 * REST controller for managing BankcardInfo.
 */
@RestController
@RequestMapping("/api")
public class BankcardInfoResource {

    private final Logger log = LoggerFactory.getLogger(BankcardInfoResource.class);

    private static final String ENTITY_NAME = "bankcardInfo";

    private final BankcardInfoService bankcardInfoService;

    public BankcardInfoResource(BankcardInfoService bankcardInfoService) {
        this.bankcardInfoService = bankcardInfoService;
    }

    /**
     * POST  /bankcard-infos : Create a new bankcardInfo.
     *
     * @param bankcardInfo the bankcardInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bankcardInfo, or with status 400 (Bad Request) if the bankcardInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bankcard-infos")
    @Timed
    public ResponseEntity<BankcardInfo> createBankcardInfo(@RequestBody BankcardInfo bankcardInfo) throws URISyntaxException {
        log.debug("REST request to save BankcardInfo : {}", bankcardInfo);
        if (bankcardInfo.getId() != null) {
            throw new BadRequestAlertException("A new bankcardInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankcardInfo result = bankcardInfoService.save(bankcardInfo);
        return ResponseEntity.created(new URI("/api/bankcard-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bankcard-infos : Updates an existing bankcardInfo.
     *
     * @param bankcardInfo the bankcardInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bankcardInfo,
     * or with status 400 (Bad Request) if the bankcardInfo is not valid,
     * or with status 500 (Internal Server Error) if the bankcardInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bankcard-infos")
    @Timed
    public ResponseEntity<BankcardInfo> updateBankcardInfo(@RequestBody BankcardInfo bankcardInfo) throws URISyntaxException {
        log.debug("REST request to update BankcardInfo : {}", bankcardInfo);
        if (bankcardInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BankcardInfo result = bankcardInfoService.save(bankcardInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bankcardInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bankcard-infos : get all the bankcardInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bankcardInfos in body
     */
    @GetMapping("/bankcard-infos")
    @Timed
    public List<BankcardInfo> getAllBankcardInfos() {
        log.debug("REST request to get all BankcardInfos");
        return bankcardInfoService.findAll();
    }

    /**
     * GET  /bankcard-infos/:id : get the "id" bankcardInfo.
     *
     * @param id the id of the bankcardInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bankcardInfo, or with status 404 (Not Found)
     */
    @GetMapping("/bankcard-infos/{id}")
    @Timed
    public ResponseEntity<BankcardInfo> getBankcardInfo(@PathVariable Long id) {
        log.debug("REST request to get BankcardInfo : {}", id);
        Optional<BankcardInfo> bankcardInfo = bankcardInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bankcardInfo);
    }

    /**
     * DELETE  /bankcard-infos/:id : delete the "id" bankcardInfo.
     *
     * @param id the id of the bankcardInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bankcard-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteBankcardInfo(@PathVariable Long id) {
        log.debug("REST request to delete BankcardInfo : {}", id);
        bankcardInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
