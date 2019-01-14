package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.UserLevel;
import com.datangliang.app.service.UserLevelService;
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
 * REST controller for managing UserLevel.
 */
@RestController
@RequestMapping("/api")
public class UserLevelResource {

    private final Logger log = LoggerFactory.getLogger(UserLevelResource.class);

    private static final String ENTITY_NAME = "userLevel";

    private final UserLevelService userLevelService;

    public UserLevelResource(UserLevelService userLevelService) {
        this.userLevelService = userLevelService;
    }

    /**
     * POST  /user-levels : Create a new userLevel.
     *
     * @param userLevel the userLevel to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userLevel, or with status 400 (Bad Request) if the userLevel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-levels")
    @Timed
    public ResponseEntity<UserLevel> createUserLevel(@RequestBody UserLevel userLevel) throws URISyntaxException {
        log.debug("REST request to save UserLevel : {}", userLevel);
        if (userLevel.getId() != null) {
            throw new BadRequestAlertException("A new userLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserLevel result = userLevelService.save(userLevel);
        return ResponseEntity.created(new URI("/api/user-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-levels : Updates an existing userLevel.
     *
     * @param userLevel the userLevel to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userLevel,
     * or with status 400 (Bad Request) if the userLevel is not valid,
     * or with status 500 (Internal Server Error) if the userLevel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-levels")
    @Timed
    public ResponseEntity<UserLevel> updateUserLevel(@RequestBody UserLevel userLevel) throws URISyntaxException {
        log.debug("REST request to update UserLevel : {}", userLevel);
        if (userLevel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserLevel result = userLevelService.save(userLevel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userLevel.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-levels : get all the userLevels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userLevels in body
     */
    @GetMapping("/user-levels")
    @Timed
    public List<UserLevel> getAllUserLevels() {
        log.debug("REST request to get all UserLevels");
        return userLevelService.findAll();
    }

    /**
     * GET  /user-levels/:id : get the "id" userLevel.
     *
     * @param id the id of the userLevel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userLevel, or with status 404 (Not Found)
     */
    @GetMapping("/user-levels/{id}")
    @Timed
    public ResponseEntity<UserLevel> getUserLevel(@PathVariable Long id) {
        log.debug("REST request to get UserLevel : {}", id);
        Optional<UserLevel> userLevel = userLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userLevel);
    }

    /**
     * DELETE  /user-levels/:id : delete the "id" userLevel.
     *
     * @param id the id of the userLevel to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-levels/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserLevel(@PathVariable Long id) {
        log.debug("REST request to delete UserLevel : {}", id);
        userLevelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
