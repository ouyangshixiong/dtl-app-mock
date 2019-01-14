package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.StoreUser;
import com.datangliang.app.service.StoreUserService;
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
 * REST controller for managing StoreUser.
 */
@RestController
@RequestMapping("/api")
public class StoreUserResource {

    private final Logger log = LoggerFactory.getLogger(StoreUserResource.class);

    private static final String ENTITY_NAME = "storeUser";

    private final StoreUserService storeUserService;

    public StoreUserResource(StoreUserService storeUserService) {
        this.storeUserService = storeUserService;
    }

    /**
     * POST  /store-users : Create a new storeUser.
     *
     * @param storeUser the storeUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new storeUser, or with status 400 (Bad Request) if the storeUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/store-users")
    @Timed
    public ResponseEntity<StoreUser> createStoreUser(@RequestBody StoreUser storeUser) throws URISyntaxException {
        log.debug("REST request to save StoreUser : {}", storeUser);
        if (storeUser.getId() != null) {
            throw new BadRequestAlertException("A new storeUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StoreUser result = storeUserService.save(storeUser);
        return ResponseEntity.created(new URI("/api/store-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /store-users : Updates an existing storeUser.
     *
     * @param storeUser the storeUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated storeUser,
     * or with status 400 (Bad Request) if the storeUser is not valid,
     * or with status 500 (Internal Server Error) if the storeUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/store-users")
    @Timed
    public ResponseEntity<StoreUser> updateStoreUser(@RequestBody StoreUser storeUser) throws URISyntaxException {
        log.debug("REST request to update StoreUser : {}", storeUser);
        if (storeUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StoreUser result = storeUserService.save(storeUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, storeUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /store-users : get all the storeUsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of storeUsers in body
     */
    @GetMapping("/store-users")
    @Timed
    public List<StoreUser> getAllStoreUsers() {
        log.debug("REST request to get all StoreUsers");
        return storeUserService.findAll();
    }

    /**
     * GET  /store-users/:id : get the "id" storeUser.
     *
     * @param id the id of the storeUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the storeUser, or with status 404 (Not Found)
     */
    @GetMapping("/store-users/{id}")
    @Timed
    public ResponseEntity<StoreUser> getStoreUser(@PathVariable Long id) {
        log.debug("REST request to get StoreUser : {}", id);
        Optional<StoreUser> storeUser = storeUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(storeUser);
    }

    /**
     * DELETE  /store-users/:id : delete the "id" storeUser.
     *
     * @param id the id of the storeUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/store-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteStoreUser(@PathVariable Long id) {
        log.debug("REST request to delete StoreUser : {}", id);
        storeUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
