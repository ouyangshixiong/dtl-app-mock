package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.DTLUser;
import com.datangliang.app.service.DTLUserService;
import com.datangliang.app.web.rest.errors.BadRequestAlertException;
import com.datangliang.app.web.rest.util.HeaderUtil;
import com.datangliang.app.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DTLUser.
 */
@RestController
@RequestMapping("/api")
public class DTLUserResource {

    private final Logger log = LoggerFactory.getLogger(DTLUserResource.class);

    private static final String ENTITY_NAME = "dTLUser";

    private final DTLUserService dTLUserService;

    public DTLUserResource(DTLUserService dTLUserService) {
        this.dTLUserService = dTLUserService;
    }

    /**
     * POST  /dtl-users : Create a new dTLUser.
     *
     * @param dTLUser the dTLUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dTLUser, or with status 400 (Bad Request) if the dTLUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dtl-users")
    @Timed
    public ResponseEntity<DTLUser> createDTLUser(@RequestBody DTLUser dTLUser) throws URISyntaxException {
        log.debug("REST request to save DTLUser : {}", dTLUser);
        if (dTLUser.getId() != null) {
            throw new BadRequestAlertException("A new dTLUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DTLUser result = dTLUserService.save(dTLUser);
        return ResponseEntity.created(new URI("/api/dtl-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dtl-users : Updates an existing dTLUser.
     *
     * @param dTLUser the dTLUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dTLUser,
     * or with status 400 (Bad Request) if the dTLUser is not valid,
     * or with status 500 (Internal Server Error) if the dTLUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dtl-users")
    @Timed
    public ResponseEntity<DTLUser> updateDTLUser(@RequestBody DTLUser dTLUser) throws URISyntaxException {
        log.debug("REST request to update DTLUser : {}", dTLUser);
        if (dTLUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DTLUser result = dTLUserService.save(dTLUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dTLUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dtl-users : get all the dTLUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dTLUsers in body
     */
    @GetMapping("/dtl-users")
    @Timed
    public ResponseEntity<List<DTLUser>> getAllDTLUsers(Pageable pageable) {
        log.debug("REST request to get a page of DTLUsers");
        Page<DTLUser> page = dTLUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dtl-users");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /dtl-users/:id : get the "id" dTLUser.
     *
     * @param id the id of the dTLUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dTLUser, or with status 404 (Not Found)
     */
    @GetMapping("/dtl-users/{id}")
    @Timed
    public ResponseEntity<DTLUser> getDTLUser(@PathVariable Long id) {
        log.debug("REST request to get DTLUser : {}", id);
        Optional<DTLUser> dTLUser = dTLUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dTLUser);
    }

    /**
     * DELETE  /dtl-users/:id : delete the "id" dTLUser.
     *
     * @param id the id of the dTLUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dtl-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteDTLUser(@PathVariable Long id) {
        log.debug("REST request to delete DTLUser : {}", id);
        dTLUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
