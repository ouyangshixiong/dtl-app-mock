package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.UserRole;
import com.datangliang.app.service.UserRoleService;
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
 * REST controller for managing UserRole.
 */
@RestController
@RequestMapping("/api")
public class UserRoleResource {

    private final Logger log = LoggerFactory.getLogger(UserRoleResource.class);

    private static final String ENTITY_NAME = "userRole";

    private final UserRoleService userRoleService;

    public UserRoleResource(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * POST  /user-roles : Create a new userRole.
     *
     * @param userRole the userRole to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userRole, or with status 400 (Bad Request) if the userRole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-roles")
    @Timed
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole) throws URISyntaxException {
        log.debug("REST request to save UserRole : {}", userRole);
        if (userRole.getId() != null) {
            throw new BadRequestAlertException("A new userRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserRole result = userRoleService.save(userRole);
        return ResponseEntity.created(new URI("/api/user-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-roles : Updates an existing userRole.
     *
     * @param userRole the userRole to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userRole,
     * or with status 400 (Bad Request) if the userRole is not valid,
     * or with status 500 (Internal Server Error) if the userRole couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-roles")
    @Timed
    public ResponseEntity<UserRole> updateUserRole(@RequestBody UserRole userRole) throws URISyntaxException {
        log.debug("REST request to update UserRole : {}", userRole);
        if (userRole.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserRole result = userRoleService.save(userRole);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userRole.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-roles : get all the userRoles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userRoles in body
     */
    @GetMapping("/user-roles")
    @Timed
    public List<UserRole> getAllUserRoles() {
        log.debug("REST request to get all UserRoles");
        return userRoleService.findAll();
    }

    /**
     * GET  /user-roles/:id : get the "id" userRole.
     *
     * @param id the id of the userRole to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userRole, or with status 404 (Not Found)
     */
    @GetMapping("/user-roles/{id}")
    @Timed
    public ResponseEntity<UserRole> getUserRole(@PathVariable Long id) {
        log.debug("REST request to get UserRole : {}", id);
        Optional<UserRole> userRole = userRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userRole);
    }

    /**
     * DELETE  /user-roles/:id : delete the "id" userRole.
     *
     * @param id the id of the userRole to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-roles/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        log.debug("REST request to delete UserRole : {}", id);
        userRoleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
