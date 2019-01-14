package com.datangliang.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.datangliang.app.domain.ProductRecord;
import com.datangliang.app.service.ProductRecordService;
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
 * REST controller for managing ProductRecord.
 */
@RestController
@RequestMapping("/api")
public class ProductRecordResource {

    private final Logger log = LoggerFactory.getLogger(ProductRecordResource.class);

    private static final String ENTITY_NAME = "productRecord";

    private final ProductRecordService productRecordService;

    public ProductRecordResource(ProductRecordService productRecordService) {
        this.productRecordService = productRecordService;
    }

    /**
     * POST  /product-records : Create a new productRecord.
     *
     * @param productRecord the productRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productRecord, or with status 400 (Bad Request) if the productRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-records")
    @Timed
    public ResponseEntity<ProductRecord> createProductRecord(@RequestBody ProductRecord productRecord) throws URISyntaxException {
        log.debug("REST request to save ProductRecord : {}", productRecord);
        if (productRecord.getId() != null) {
            throw new BadRequestAlertException("A new productRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductRecord result = productRecordService.save(productRecord);
        return ResponseEntity.created(new URI("/api/product-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-records : Updates an existing productRecord.
     *
     * @param productRecord the productRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productRecord,
     * or with status 400 (Bad Request) if the productRecord is not valid,
     * or with status 500 (Internal Server Error) if the productRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-records")
    @Timed
    public ResponseEntity<ProductRecord> updateProductRecord(@RequestBody ProductRecord productRecord) throws URISyntaxException {
        log.debug("REST request to update ProductRecord : {}", productRecord);
        if (productRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProductRecord result = productRecordService.save(productRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-records : get all the productRecords.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of productRecords in body
     */
    @GetMapping("/product-records")
    @Timed
    public List<ProductRecord> getAllProductRecords() {
        log.debug("REST request to get all ProductRecords");
        return productRecordService.findAll();
    }

    /**
     * GET  /product-records/:id : get the "id" productRecord.
     *
     * @param id the id of the productRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productRecord, or with status 404 (Not Found)
     */
    @GetMapping("/product-records/{id}")
    @Timed
    public ResponseEntity<ProductRecord> getProductRecord(@PathVariable Long id) {
        log.debug("REST request to get ProductRecord : {}", id);
        Optional<ProductRecord> productRecord = productRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productRecord);
    }

    /**
     * DELETE  /product-records/:id : delete the "id" productRecord.
     *
     * @param id the id of the productRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductRecord(@PathVariable Long id) {
        log.debug("REST request to delete ProductRecord : {}", id);
        productRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
