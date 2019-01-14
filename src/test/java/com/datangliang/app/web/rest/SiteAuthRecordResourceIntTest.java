package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.SiteAuthRecord;
import com.datangliang.app.repository.SiteAuthRecordRepository;
import com.datangliang.app.service.SiteAuthRecordService;
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
 * Test class for the SiteAuthRecordResource REST controller.
 *
 * @see SiteAuthRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class SiteAuthRecordResourceIntTest {

    private static final String DEFAULT_TXN_ID = "AAAAAAAAAA";
    private static final String UPDATED_TXN_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_IMG_IDS = "AAAAAAAAAA";
    private static final String UPDATED_SITE_IMG_IDS = "BBBBBBBBBB";

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
    private SiteAuthRecordRepository siteAuthRecordRepository;

    @Autowired
    private SiteAuthRecordService siteAuthRecordService;

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

    private MockMvc restSiteAuthRecordMockMvc;

    private SiteAuthRecord siteAuthRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SiteAuthRecordResource siteAuthRecordResource = new SiteAuthRecordResource(siteAuthRecordService);
        this.restSiteAuthRecordMockMvc = MockMvcBuilders.standaloneSetup(siteAuthRecordResource)
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
    public static SiteAuthRecord createEntity(EntityManager em) {
        SiteAuthRecord siteAuthRecord = new SiteAuthRecord()
            .txnId(DEFAULT_TXN_ID)
            .userId(DEFAULT_USER_ID)
            .type(DEFAULT_TYPE)
            .address(DEFAULT_ADDRESS)
            .siteImgIds(DEFAULT_SITE_IMG_IDS)
            .authStatus(DEFAULT_AUTH_STATUS)
            .auditOpinion(DEFAULT_AUDIT_OPINION)
            .auditStaffName(DEFAULT_AUDIT_STAFF_NAME)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return siteAuthRecord;
    }

    @Before
    public void initTest() {
        siteAuthRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createSiteAuthRecord() throws Exception {
        int databaseSizeBeforeCreate = siteAuthRecordRepository.findAll().size();

        // Create the SiteAuthRecord
        restSiteAuthRecordMockMvc.perform(post("/api/site-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteAuthRecord)))
            .andExpect(status().isCreated());

        // Validate the SiteAuthRecord in the database
        List<SiteAuthRecord> siteAuthRecordList = siteAuthRecordRepository.findAll();
        assertThat(siteAuthRecordList).hasSize(databaseSizeBeforeCreate + 1);
        SiteAuthRecord testSiteAuthRecord = siteAuthRecordList.get(siteAuthRecordList.size() - 1);
        assertThat(testSiteAuthRecord.getTxnId()).isEqualTo(DEFAULT_TXN_ID);
        assertThat(testSiteAuthRecord.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testSiteAuthRecord.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSiteAuthRecord.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSiteAuthRecord.getSiteImgIds()).isEqualTo(DEFAULT_SITE_IMG_IDS);
        assertThat(testSiteAuthRecord.getAuthStatus()).isEqualTo(DEFAULT_AUTH_STATUS);
        assertThat(testSiteAuthRecord.getAuditOpinion()).isEqualTo(DEFAULT_AUDIT_OPINION);
        assertThat(testSiteAuthRecord.getAuditStaffName()).isEqualTo(DEFAULT_AUDIT_STAFF_NAME);
        assertThat(testSiteAuthRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSiteAuthRecord.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createSiteAuthRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = siteAuthRecordRepository.findAll().size();

        // Create the SiteAuthRecord with an existing ID
        siteAuthRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSiteAuthRecordMockMvc.perform(post("/api/site-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the SiteAuthRecord in the database
        List<SiteAuthRecord> siteAuthRecordList = siteAuthRecordRepository.findAll();
        assertThat(siteAuthRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSiteAuthRecords() throws Exception {
        // Initialize the database
        siteAuthRecordRepository.saveAndFlush(siteAuthRecord);

        // Get all the siteAuthRecordList
        restSiteAuthRecordMockMvc.perform(get("/api/site-auth-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(siteAuthRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnId").value(hasItem(DEFAULT_TXN_ID.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].siteImgIds").value(hasItem(DEFAULT_SITE_IMG_IDS.toString())))
            .andExpect(jsonPath("$.[*].authStatus").value(hasItem(DEFAULT_AUTH_STATUS)))
            .andExpect(jsonPath("$.[*].auditOpinion").value(hasItem(DEFAULT_AUDIT_OPINION.toString())))
            .andExpect(jsonPath("$.[*].auditStaffName").value(hasItem(DEFAULT_AUDIT_STAFF_NAME.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getSiteAuthRecord() throws Exception {
        // Initialize the database
        siteAuthRecordRepository.saveAndFlush(siteAuthRecord);

        // Get the siteAuthRecord
        restSiteAuthRecordMockMvc.perform(get("/api/site-auth-records/{id}", siteAuthRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(siteAuthRecord.getId().intValue()))
            .andExpect(jsonPath("$.txnId").value(DEFAULT_TXN_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.siteImgIds").value(DEFAULT_SITE_IMG_IDS.toString()))
            .andExpect(jsonPath("$.authStatus").value(DEFAULT_AUTH_STATUS))
            .andExpect(jsonPath("$.auditOpinion").value(DEFAULT_AUDIT_OPINION.toString()))
            .andExpect(jsonPath("$.auditStaffName").value(DEFAULT_AUDIT_STAFF_NAME.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSiteAuthRecord() throws Exception {
        // Get the siteAuthRecord
        restSiteAuthRecordMockMvc.perform(get("/api/site-auth-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSiteAuthRecord() throws Exception {
        // Initialize the database
        siteAuthRecordService.save(siteAuthRecord);

        int databaseSizeBeforeUpdate = siteAuthRecordRepository.findAll().size();

        // Update the siteAuthRecord
        SiteAuthRecord updatedSiteAuthRecord = siteAuthRecordRepository.findById(siteAuthRecord.getId()).get();
        // Disconnect from session so that the updates on updatedSiteAuthRecord are not directly saved in db
        em.detach(updatedSiteAuthRecord);
        updatedSiteAuthRecord
            .txnId(UPDATED_TXN_ID)
            .userId(UPDATED_USER_ID)
            .type(UPDATED_TYPE)
            .address(UPDATED_ADDRESS)
            .siteImgIds(UPDATED_SITE_IMG_IDS)
            .authStatus(UPDATED_AUTH_STATUS)
            .auditOpinion(UPDATED_AUDIT_OPINION)
            .auditStaffName(UPDATED_AUDIT_STAFF_NAME)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restSiteAuthRecordMockMvc.perform(put("/api/site-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSiteAuthRecord)))
            .andExpect(status().isOk());

        // Validate the SiteAuthRecord in the database
        List<SiteAuthRecord> siteAuthRecordList = siteAuthRecordRepository.findAll();
        assertThat(siteAuthRecordList).hasSize(databaseSizeBeforeUpdate);
        SiteAuthRecord testSiteAuthRecord = siteAuthRecordList.get(siteAuthRecordList.size() - 1);
        assertThat(testSiteAuthRecord.getTxnId()).isEqualTo(UPDATED_TXN_ID);
        assertThat(testSiteAuthRecord.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSiteAuthRecord.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSiteAuthRecord.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSiteAuthRecord.getSiteImgIds()).isEqualTo(UPDATED_SITE_IMG_IDS);
        assertThat(testSiteAuthRecord.getAuthStatus()).isEqualTo(UPDATED_AUTH_STATUS);
        assertThat(testSiteAuthRecord.getAuditOpinion()).isEqualTo(UPDATED_AUDIT_OPINION);
        assertThat(testSiteAuthRecord.getAuditStaffName()).isEqualTo(UPDATED_AUDIT_STAFF_NAME);
        assertThat(testSiteAuthRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSiteAuthRecord.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSiteAuthRecord() throws Exception {
        int databaseSizeBeforeUpdate = siteAuthRecordRepository.findAll().size();

        // Create the SiteAuthRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSiteAuthRecordMockMvc.perform(put("/api/site-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the SiteAuthRecord in the database
        List<SiteAuthRecord> siteAuthRecordList = siteAuthRecordRepository.findAll();
        assertThat(siteAuthRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSiteAuthRecord() throws Exception {
        // Initialize the database
        siteAuthRecordService.save(siteAuthRecord);

        int databaseSizeBeforeDelete = siteAuthRecordRepository.findAll().size();

        // Get the siteAuthRecord
        restSiteAuthRecordMockMvc.perform(delete("/api/site-auth-records/{id}", siteAuthRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SiteAuthRecord> siteAuthRecordList = siteAuthRecordRepository.findAll();
        assertThat(siteAuthRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SiteAuthRecord.class);
        SiteAuthRecord siteAuthRecord1 = new SiteAuthRecord();
        siteAuthRecord1.setId(1L);
        SiteAuthRecord siteAuthRecord2 = new SiteAuthRecord();
        siteAuthRecord2.setId(siteAuthRecord1.getId());
        assertThat(siteAuthRecord1).isEqualTo(siteAuthRecord2);
        siteAuthRecord2.setId(2L);
        assertThat(siteAuthRecord1).isNotEqualTo(siteAuthRecord2);
        siteAuthRecord1.setId(null);
        assertThat(siteAuthRecord1).isNotEqualTo(siteAuthRecord2);
    }
}
