package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.ProductRecord;
import com.datangliang.app.repository.ProductRecordRepository;
import com.datangliang.app.service.ProductRecordService;
import com.datangliang.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.datangliang.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductRecordResource REST controller.
 *
 * @see ProductRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class ProductRecordResourceIntTest {

    private static final String DEFAULT_TXN_ID = "AAAAAAAAAA";
    private static final String UPDATED_TXN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CATEGORY_ID = 1;
    private static final Integer UPDATED_CATEGORY_ID = 2;

    private static final Integer DEFAULT_STANDARD_ID = 1;
    private static final Integer UPDATED_STANDARD_ID = 2;

    private static final Integer DEFAULT_STORE_ID = 1;
    private static final Integer UPDATED_STORE_ID = 2;

    private static final String DEFAULT_PRODUCT_IMG = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_IMG = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNIT_PRICE = new BigDecimal(2);

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    private static final Integer DEFAULT_MIN_DEAL = 1;
    private static final Integer UPDATED_MIN_DEAL = 2;

    private static final String DEFAULT_DEPOT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_DEPOT_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_OBJECTION = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTION = "BBBBBBBBBB";

    private static final String DEFAULT_RECORD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ProductRecordRepository productRecordRepository;

    @Autowired
    private ProductRecordService productRecordService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restProductRecordMockMvc;

    private ProductRecord productRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductRecordResource productRecordResource = new ProductRecordResource(productRecordService);
        this.restProductRecordMockMvc = MockMvcBuilders.standaloneSetup(productRecordResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductRecord createEntity(EntityManager em) {
        ProductRecord productRecord = new ProductRecord()
            .txnId(DEFAULT_TXN_ID)
            .title(DEFAULT_TITLE)
            .categoryId(DEFAULT_CATEGORY_ID)
            .standardId(DEFAULT_STANDARD_ID)
            .storeId(DEFAULT_STORE_ID)
            .productImg(DEFAULT_PRODUCT_IMG)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .stock(DEFAULT_STOCK)
            .minDeal(DEFAULT_MIN_DEAL)
            .depotAddress(DEFAULT_DEPOT_ADDRESS)
            .contactName(DEFAULT_CONTACT_NAME)
            .mobile(DEFAULT_MOBILE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .objection(DEFAULT_OBJECTION)
            .recordName(DEFAULT_RECORD_NAME)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return productRecord;
    }

    @Before
    public void initTest() {
        productRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductRecord() throws Exception {
        int databaseSizeBeforeCreate = productRecordRepository.findAll().size();

        // Create the ProductRecord
        restProductRecordMockMvc.perform(post("/api/product-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRecord)))
            .andExpect(status().isCreated());

        // Validate the ProductRecord in the database
        List<ProductRecord> productRecordList = productRecordRepository.findAll();
        assertThat(productRecordList).hasSize(databaseSizeBeforeCreate + 1);
        ProductRecord testProductRecord = productRecordList.get(productRecordList.size() - 1);
        assertThat(testProductRecord.getTxnId()).isEqualTo(DEFAULT_TXN_ID);
        assertThat(testProductRecord.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testProductRecord.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
        assertThat(testProductRecord.getStandardId()).isEqualTo(DEFAULT_STANDARD_ID);
        assertThat(testProductRecord.getStoreId()).isEqualTo(DEFAULT_STORE_ID);
        assertThat(testProductRecord.getProductImg()).isEqualTo(DEFAULT_PRODUCT_IMG);
        assertThat(testProductRecord.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testProductRecord.getStock()).isEqualTo(DEFAULT_STOCK);
        assertThat(testProductRecord.getMinDeal()).isEqualTo(DEFAULT_MIN_DEAL);
        assertThat(testProductRecord.getDepotAddress()).isEqualTo(DEFAULT_DEPOT_ADDRESS);
        assertThat(testProductRecord.getContactName()).isEqualTo(DEFAULT_CONTACT_NAME);
        assertThat(testProductRecord.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testProductRecord.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProductRecord.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProductRecord.getObjection()).isEqualTo(DEFAULT_OBJECTION);
        assertThat(testProductRecord.getRecordName()).isEqualTo(DEFAULT_RECORD_NAME);
        assertThat(testProductRecord.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testProductRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testProductRecord.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createProductRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRecordRepository.findAll().size();

        // Create the ProductRecord with an existing ID
        productRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductRecordMockMvc.perform(post("/api/product-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRecord)))
            .andExpect(status().isBadRequest());

        // Validate the ProductRecord in the database
        List<ProductRecord> productRecordList = productRecordRepository.findAll();
        assertThat(productRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProductRecords() throws Exception {
        // Initialize the database
        productRecordRepository.saveAndFlush(productRecord);

        // Get all the productRecordList
        restProductRecordMockMvc.perform(get("/api/product-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnId").value(hasItem(DEFAULT_TXN_ID.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID)))
            .andExpect(jsonPath("$.[*].standardId").value(hasItem(DEFAULT_STANDARD_ID)))
            .andExpect(jsonPath("$.[*].storeId").value(hasItem(DEFAULT_STORE_ID)))
            .andExpect(jsonPath("$.[*].productImg").value(hasItem(DEFAULT_PRODUCT_IMG.toString())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)))
            .andExpect(jsonPath("$.[*].minDeal").value(hasItem(DEFAULT_MIN_DEAL)))
            .andExpect(jsonPath("$.[*].depotAddress").value(hasItem(DEFAULT_DEPOT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].contactName").value(hasItem(DEFAULT_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].objection").value(hasItem(DEFAULT_OBJECTION.toString())))
            .andExpect(jsonPath("$.[*].recordName").value(hasItem(DEFAULT_RECORD_NAME.toString())))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getProductRecord() throws Exception {
        // Initialize the database
        productRecordRepository.saveAndFlush(productRecord);

        // Get the productRecord
        restProductRecordMockMvc.perform(get("/api/product-records/{id}", productRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productRecord.getId().intValue()))
            .andExpect(jsonPath("$.txnId").value(DEFAULT_TXN_ID.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID))
            .andExpect(jsonPath("$.standardId").value(DEFAULT_STANDARD_ID))
            .andExpect(jsonPath("$.storeId").value(DEFAULT_STORE_ID))
            .andExpect(jsonPath("$.productImg").value(DEFAULT_PRODUCT_IMG.toString()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.intValue()))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK))
            .andExpect(jsonPath("$.minDeal").value(DEFAULT_MIN_DEAL))
            .andExpect(jsonPath("$.depotAddress").value(DEFAULT_DEPOT_ADDRESS.toString()))
            .andExpect(jsonPath("$.contactName").value(DEFAULT_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.objection").value(DEFAULT_OBJECTION.toString()))
            .andExpect(jsonPath("$.recordName").value(DEFAULT_RECORD_NAME.toString()))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProductRecord() throws Exception {
        // Get the productRecord
        restProductRecordMockMvc.perform(get("/api/product-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductRecord() throws Exception {
        // Initialize the database
        productRecordService.save(productRecord);

        int databaseSizeBeforeUpdate = productRecordRepository.findAll().size();

        // Update the productRecord
        ProductRecord updatedProductRecord = productRecordRepository.findById(productRecord.getId()).get();
        // Disconnect from session so that the updates on updatedProductRecord are not directly saved in db
        em.detach(updatedProductRecord);
        updatedProductRecord
            .txnId(UPDATED_TXN_ID)
            .title(UPDATED_TITLE)
            .categoryId(UPDATED_CATEGORY_ID)
            .standardId(UPDATED_STANDARD_ID)
            .storeId(UPDATED_STORE_ID)
            .productImg(UPDATED_PRODUCT_IMG)
            .unitPrice(UPDATED_UNIT_PRICE)
            .stock(UPDATED_STOCK)
            .minDeal(UPDATED_MIN_DEAL)
            .depotAddress(UPDATED_DEPOT_ADDRESS)
            .contactName(UPDATED_CONTACT_NAME)
            .mobile(UPDATED_MOBILE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .objection(UPDATED_OBJECTION)
            .recordName(UPDATED_RECORD_NAME)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restProductRecordMockMvc.perform(put("/api/product-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductRecord)))
            .andExpect(status().isOk());

        // Validate the ProductRecord in the database
        List<ProductRecord> productRecordList = productRecordRepository.findAll();
        assertThat(productRecordList).hasSize(databaseSizeBeforeUpdate);
        ProductRecord testProductRecord = productRecordList.get(productRecordList.size() - 1);
        assertThat(testProductRecord.getTxnId()).isEqualTo(UPDATED_TXN_ID);
        assertThat(testProductRecord.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testProductRecord.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
        assertThat(testProductRecord.getStandardId()).isEqualTo(UPDATED_STANDARD_ID);
        assertThat(testProductRecord.getStoreId()).isEqualTo(UPDATED_STORE_ID);
        assertThat(testProductRecord.getProductImg()).isEqualTo(UPDATED_PRODUCT_IMG);
        assertThat(testProductRecord.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testProductRecord.getStock()).isEqualTo(UPDATED_STOCK);
        assertThat(testProductRecord.getMinDeal()).isEqualTo(UPDATED_MIN_DEAL);
        assertThat(testProductRecord.getDepotAddress()).isEqualTo(UPDATED_DEPOT_ADDRESS);
        assertThat(testProductRecord.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testProductRecord.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testProductRecord.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProductRecord.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProductRecord.getObjection()).isEqualTo(UPDATED_OBJECTION);
        assertThat(testProductRecord.getRecordName()).isEqualTo(UPDATED_RECORD_NAME);
        assertThat(testProductRecord.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testProductRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testProductRecord.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingProductRecord() throws Exception {
        int databaseSizeBeforeUpdate = productRecordRepository.findAll().size();

        // Create the ProductRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductRecordMockMvc.perform(put("/api/product-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productRecord)))
            .andExpect(status().isBadRequest());

        // Validate the ProductRecord in the database
        List<ProductRecord> productRecordList = productRecordRepository.findAll();
        assertThat(productRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProductRecord() throws Exception {
        // Initialize the database
        productRecordService.save(productRecord);

        int databaseSizeBeforeDelete = productRecordRepository.findAll().size();

        // Get the productRecord
        restProductRecordMockMvc.perform(delete("/api/product-records/{id}", productRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductRecord> productRecordList = productRecordRepository.findAll();
        assertThat(productRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductRecord.class);
        ProductRecord productRecord1 = new ProductRecord();
        productRecord1.setId(1L);
        ProductRecord productRecord2 = new ProductRecord();
        productRecord2.setId(productRecord1.getId());
        assertThat(productRecord1).isEqualTo(productRecord2);
        productRecord2.setId(2L);
        assertThat(productRecord1).isNotEqualTo(productRecord2);
        productRecord1.setId(null);
        assertThat(productRecord1).isNotEqualTo(productRecord2);
    }
}
