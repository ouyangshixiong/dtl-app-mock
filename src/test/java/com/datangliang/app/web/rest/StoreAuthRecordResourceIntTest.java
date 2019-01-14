package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.StoreAuthRecord;
import com.datangliang.app.repository.StoreAuthRecordRepository;
import com.datangliang.app.service.StoreAuthRecordService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.datangliang.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StoreAuthRecordResource REST controller.
 *
 * @see StoreAuthRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class StoreAuthRecordResourceIntTest {

    private static final String DEFAULT_TXN_ID = "AAAAAAAAAA";
    private static final String UPDATED_TXN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STORE_TYPE = 1;
    private static final Integer UPDATED_STORE_TYPE = 2;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    private static final String DEFAULT_LINKMAN = "AAAAAAAAAA";
    private static final String UPDATED_LINKMAN = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_AUTH_STATUS = 1;
    private static final Integer UPDATED_AUTH_STATUS = 2;

    private static final String DEFAULT_AUDIT_OPINION = "AAAAAAAAAA";
    private static final String UPDATED_AUDIT_OPINION = "BBBBBBBBBB";

    private static final String DEFAULT_AUDIT_STAFF_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AUDIT_STAFF_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private StoreAuthRecordRepository storeAuthRecordRepository;

    @Autowired
    private StoreAuthRecordService storeAuthRecordService;

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

    private MockMvc restStoreAuthRecordMockMvc;

    private StoreAuthRecord storeAuthRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StoreAuthRecordResource storeAuthRecordResource = new StoreAuthRecordResource(storeAuthRecordService);
        this.restStoreAuthRecordMockMvc = MockMvcBuilders.standaloneSetup(storeAuthRecordResource)
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
    public static StoreAuthRecord createEntity(EntityManager em) {
        StoreAuthRecord storeAuthRecord = new StoreAuthRecord()
            .txnId(DEFAULT_TXN_ID)
            .name(DEFAULT_NAME)
            .storeType(DEFAULT_STORE_TYPE)
            .address(DEFAULT_ADDRESS)
            .area(DEFAULT_AREA)
            .linkman(DEFAULT_LINKMAN)
            .tel(DEFAULT_TEL)
            .authStatus(DEFAULT_AUTH_STATUS)
            .auditOpinion(DEFAULT_AUDIT_OPINION)
            .auditStaffName(DEFAULT_AUDIT_STAFF_NAME)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return storeAuthRecord;
    }

    @Before
    public void initTest() {
        storeAuthRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createStoreAuthRecord() throws Exception {
        int databaseSizeBeforeCreate = storeAuthRecordRepository.findAll().size();

        // Create the StoreAuthRecord
        restStoreAuthRecordMockMvc.perform(post("/api/store-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeAuthRecord)))
            .andExpect(status().isCreated());

        // Validate the StoreAuthRecord in the database
        List<StoreAuthRecord> storeAuthRecordList = storeAuthRecordRepository.findAll();
        assertThat(storeAuthRecordList).hasSize(databaseSizeBeforeCreate + 1);
        StoreAuthRecord testStoreAuthRecord = storeAuthRecordList.get(storeAuthRecordList.size() - 1);
        assertThat(testStoreAuthRecord.getTxnId()).isEqualTo(DEFAULT_TXN_ID);
        assertThat(testStoreAuthRecord.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStoreAuthRecord.getStoreType()).isEqualTo(DEFAULT_STORE_TYPE);
        assertThat(testStoreAuthRecord.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testStoreAuthRecord.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testStoreAuthRecord.getLinkman()).isEqualTo(DEFAULT_LINKMAN);
        assertThat(testStoreAuthRecord.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testStoreAuthRecord.getAuthStatus()).isEqualTo(DEFAULT_AUTH_STATUS);
        assertThat(testStoreAuthRecord.getAuditOpinion()).isEqualTo(DEFAULT_AUDIT_OPINION);
        assertThat(testStoreAuthRecord.getAuditStaffName()).isEqualTo(DEFAULT_AUDIT_STAFF_NAME);
        assertThat(testStoreAuthRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testStoreAuthRecord.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createStoreAuthRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = storeAuthRecordRepository.findAll().size();

        // Create the StoreAuthRecord with an existing ID
        storeAuthRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStoreAuthRecordMockMvc.perform(post("/api/store-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the StoreAuthRecord in the database
        List<StoreAuthRecord> storeAuthRecordList = storeAuthRecordRepository.findAll();
        assertThat(storeAuthRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStoreAuthRecords() throws Exception {
        // Initialize the database
        storeAuthRecordRepository.saveAndFlush(storeAuthRecord);

        // Get all the storeAuthRecordList
        restStoreAuthRecordMockMvc.perform(get("/api/store-auth-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(storeAuthRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnId").value(hasItem(DEFAULT_TXN_ID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].storeType").value(hasItem(DEFAULT_STORE_TYPE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
            .andExpect(jsonPath("$.[*].linkman").value(hasItem(DEFAULT_LINKMAN.toString())))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL.toString())))
            .andExpect(jsonPath("$.[*].authStatus").value(hasItem(DEFAULT_AUTH_STATUS)))
            .andExpect(jsonPath("$.[*].auditOpinion").value(hasItem(DEFAULT_AUDIT_OPINION.toString())))
            .andExpect(jsonPath("$.[*].auditStaffName").value(hasItem(DEFAULT_AUDIT_STAFF_NAME.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getStoreAuthRecord() throws Exception {
        // Initialize the database
        storeAuthRecordRepository.saveAndFlush(storeAuthRecord);

        // Get the storeAuthRecord
        restStoreAuthRecordMockMvc.perform(get("/api/store-auth-records/{id}", storeAuthRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(storeAuthRecord.getId().intValue()))
            .andExpect(jsonPath("$.txnId").value(DEFAULT_TXN_ID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.storeType").value(DEFAULT_STORE_TYPE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()))
            .andExpect(jsonPath("$.linkman").value(DEFAULT_LINKMAN.toString()))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL.toString()))
            .andExpect(jsonPath("$.authStatus").value(DEFAULT_AUTH_STATUS))
            .andExpect(jsonPath("$.auditOpinion").value(DEFAULT_AUDIT_OPINION.toString()))
            .andExpect(jsonPath("$.auditStaffName").value(DEFAULT_AUDIT_STAFF_NAME.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStoreAuthRecord() throws Exception {
        // Get the storeAuthRecord
        restStoreAuthRecordMockMvc.perform(get("/api/store-auth-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStoreAuthRecord() throws Exception {
        // Initialize the database
        storeAuthRecordService.save(storeAuthRecord);

        int databaseSizeBeforeUpdate = storeAuthRecordRepository.findAll().size();

        // Update the storeAuthRecord
        StoreAuthRecord updatedStoreAuthRecord = storeAuthRecordRepository.findById(storeAuthRecord.getId()).get();
        // Disconnect from session so that the updates on updatedStoreAuthRecord are not directly saved in db
        em.detach(updatedStoreAuthRecord);
        updatedStoreAuthRecord
            .txnId(UPDATED_TXN_ID)
            .name(UPDATED_NAME)
            .storeType(UPDATED_STORE_TYPE)
            .address(UPDATED_ADDRESS)
            .area(UPDATED_AREA)
            .linkman(UPDATED_LINKMAN)
            .tel(UPDATED_TEL)
            .authStatus(UPDATED_AUTH_STATUS)
            .auditOpinion(UPDATED_AUDIT_OPINION)
            .auditStaffName(UPDATED_AUDIT_STAFF_NAME)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restStoreAuthRecordMockMvc.perform(put("/api/store-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStoreAuthRecord)))
            .andExpect(status().isOk());

        // Validate the StoreAuthRecord in the database
        List<StoreAuthRecord> storeAuthRecordList = storeAuthRecordRepository.findAll();
        assertThat(storeAuthRecordList).hasSize(databaseSizeBeforeUpdate);
        StoreAuthRecord testStoreAuthRecord = storeAuthRecordList.get(storeAuthRecordList.size() - 1);
        assertThat(testStoreAuthRecord.getTxnId()).isEqualTo(UPDATED_TXN_ID);
        assertThat(testStoreAuthRecord.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStoreAuthRecord.getStoreType()).isEqualTo(UPDATED_STORE_TYPE);
        assertThat(testStoreAuthRecord.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testStoreAuthRecord.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testStoreAuthRecord.getLinkman()).isEqualTo(UPDATED_LINKMAN);
        assertThat(testStoreAuthRecord.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testStoreAuthRecord.getAuthStatus()).isEqualTo(UPDATED_AUTH_STATUS);
        assertThat(testStoreAuthRecord.getAuditOpinion()).isEqualTo(UPDATED_AUDIT_OPINION);
        assertThat(testStoreAuthRecord.getAuditStaffName()).isEqualTo(UPDATED_AUDIT_STAFF_NAME);
        assertThat(testStoreAuthRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testStoreAuthRecord.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingStoreAuthRecord() throws Exception {
        int databaseSizeBeforeUpdate = storeAuthRecordRepository.findAll().size();

        // Create the StoreAuthRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStoreAuthRecordMockMvc.perform(put("/api/store-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the StoreAuthRecord in the database
        List<StoreAuthRecord> storeAuthRecordList = storeAuthRecordRepository.findAll();
        assertThat(storeAuthRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStoreAuthRecord() throws Exception {
        // Initialize the database
        storeAuthRecordService.save(storeAuthRecord);

        int databaseSizeBeforeDelete = storeAuthRecordRepository.findAll().size();

        // Get the storeAuthRecord
        restStoreAuthRecordMockMvc.perform(delete("/api/store-auth-records/{id}", storeAuthRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StoreAuthRecord> storeAuthRecordList = storeAuthRecordRepository.findAll();
        assertThat(storeAuthRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StoreAuthRecord.class);
        StoreAuthRecord storeAuthRecord1 = new StoreAuthRecord();
        storeAuthRecord1.setId(1L);
        StoreAuthRecord storeAuthRecord2 = new StoreAuthRecord();
        storeAuthRecord2.setId(storeAuthRecord1.getId());
        assertThat(storeAuthRecord1).isEqualTo(storeAuthRecord2);
        storeAuthRecord2.setId(2L);
        assertThat(storeAuthRecord1).isNotEqualTo(storeAuthRecord2);
        storeAuthRecord1.setId(null);
        assertThat(storeAuthRecord1).isNotEqualTo(storeAuthRecord2);
    }
}
