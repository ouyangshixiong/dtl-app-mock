package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.EnterpriseAuthRecord;
import com.datangliang.app.repository.EnterpriseAuthRecordRepository;
import com.datangliang.app.service.EnterpriseAuthRecordService;
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
 * Test class for the EnterpriseAuthRecordResource REST controller.
 *
 * @see EnterpriseAuthRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class EnterpriseAuthRecordResourceIntTest {

    private static final String DEFAULT_TXN_ID = "AAAAAAAAAA";
    private static final String UPDATED_TXN_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_PERSON_ID_CARD_NUM = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_PERSON_ID_CARD_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_PERSON_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_PERSON_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_ENTERPRISE_TEL = "AAAAAAAAAA";
    private static final String UPDATED_ENTERPRISE_TEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_BUSINESS_LICENSE_IMG_ID = 1;
    private static final Integer UPDATED_BUSINESS_LICENSE_IMG_ID = 2;

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
    private EnterpriseAuthRecordRepository enterpriseAuthRecordRepository;

    @Autowired
    private EnterpriseAuthRecordService enterpriseAuthRecordService;

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

    private MockMvc restEnterpriseAuthRecordMockMvc;

    private EnterpriseAuthRecord enterpriseAuthRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnterpriseAuthRecordResource enterpriseAuthRecordResource = new EnterpriseAuthRecordResource(enterpriseAuthRecordService);
        this.restEnterpriseAuthRecordMockMvc = MockMvcBuilders.standaloneSetup(enterpriseAuthRecordResource)
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
    public static EnterpriseAuthRecord createEntity(EntityManager em) {
        EnterpriseAuthRecord enterpriseAuthRecord = new EnterpriseAuthRecord()
            .txnId(DEFAULT_TXN_ID)
            .userId(DEFAULT_USER_ID)
            .name(DEFAULT_NAME)
            .legalPersonName(DEFAULT_LEGAL_PERSON_NAME)
            .legalPersonIdCardNum(DEFAULT_LEGAL_PERSON_ID_CARD_NUM)
            .legalPersonMobile(DEFAULT_LEGAL_PERSON_MOBILE)
            .enterpriseTel(DEFAULT_ENTERPRISE_TEL)
            .businessLicenseImgId(DEFAULT_BUSINESS_LICENSE_IMG_ID)
            .authStatus(DEFAULT_AUTH_STATUS)
            .auditOpinion(DEFAULT_AUDIT_OPINION)
            .auditStaffName(DEFAULT_AUDIT_STAFF_NAME)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return enterpriseAuthRecord;
    }

    @Before
    public void initTest() {
        enterpriseAuthRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnterpriseAuthRecord() throws Exception {
        int databaseSizeBeforeCreate = enterpriseAuthRecordRepository.findAll().size();

        // Create the EnterpriseAuthRecord
        restEnterpriseAuthRecordMockMvc.perform(post("/api/enterprise-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseAuthRecord)))
            .andExpect(status().isCreated());

        // Validate the EnterpriseAuthRecord in the database
        List<EnterpriseAuthRecord> enterpriseAuthRecordList = enterpriseAuthRecordRepository.findAll();
        assertThat(enterpriseAuthRecordList).hasSize(databaseSizeBeforeCreate + 1);
        EnterpriseAuthRecord testEnterpriseAuthRecord = enterpriseAuthRecordList.get(enterpriseAuthRecordList.size() - 1);
        assertThat(testEnterpriseAuthRecord.getTxnId()).isEqualTo(DEFAULT_TXN_ID);
        assertThat(testEnterpriseAuthRecord.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testEnterpriseAuthRecord.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEnterpriseAuthRecord.getLegalPersonName()).isEqualTo(DEFAULT_LEGAL_PERSON_NAME);
        assertThat(testEnterpriseAuthRecord.getLegalPersonIdCardNum()).isEqualTo(DEFAULT_LEGAL_PERSON_ID_CARD_NUM);
        assertThat(testEnterpriseAuthRecord.getLegalPersonMobile()).isEqualTo(DEFAULT_LEGAL_PERSON_MOBILE);
        assertThat(testEnterpriseAuthRecord.getEnterpriseTel()).isEqualTo(DEFAULT_ENTERPRISE_TEL);
        assertThat(testEnterpriseAuthRecord.getBusinessLicenseImgId()).isEqualTo(DEFAULT_BUSINESS_LICENSE_IMG_ID);
        assertThat(testEnterpriseAuthRecord.getAuthStatus()).isEqualTo(DEFAULT_AUTH_STATUS);
        assertThat(testEnterpriseAuthRecord.getAuditOpinion()).isEqualTo(DEFAULT_AUDIT_OPINION);
        assertThat(testEnterpriseAuthRecord.getAuditStaffName()).isEqualTo(DEFAULT_AUDIT_STAFF_NAME);
        assertThat(testEnterpriseAuthRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testEnterpriseAuthRecord.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createEnterpriseAuthRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enterpriseAuthRecordRepository.findAll().size();

        // Create the EnterpriseAuthRecord with an existing ID
        enterpriseAuthRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnterpriseAuthRecordMockMvc.perform(post("/api/enterprise-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the EnterpriseAuthRecord in the database
        List<EnterpriseAuthRecord> enterpriseAuthRecordList = enterpriseAuthRecordRepository.findAll();
        assertThat(enterpriseAuthRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEnterpriseAuthRecords() throws Exception {
        // Initialize the database
        enterpriseAuthRecordRepository.saveAndFlush(enterpriseAuthRecord);

        // Get all the enterpriseAuthRecordList
        restEnterpriseAuthRecordMockMvc.perform(get("/api/enterprise-auth-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enterpriseAuthRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnId").value(hasItem(DEFAULT_TXN_ID.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].legalPersonName").value(hasItem(DEFAULT_LEGAL_PERSON_NAME.toString())))
            .andExpect(jsonPath("$.[*].legalPersonIdCardNum").value(hasItem(DEFAULT_LEGAL_PERSON_ID_CARD_NUM.toString())))
            .andExpect(jsonPath("$.[*].legalPersonMobile").value(hasItem(DEFAULT_LEGAL_PERSON_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].enterpriseTel").value(hasItem(DEFAULT_ENTERPRISE_TEL.toString())))
            .andExpect(jsonPath("$.[*].businessLicenseImgId").value(hasItem(DEFAULT_BUSINESS_LICENSE_IMG_ID)))
            .andExpect(jsonPath("$.[*].authStatus").value(hasItem(DEFAULT_AUTH_STATUS)))
            .andExpect(jsonPath("$.[*].auditOpinion").value(hasItem(DEFAULT_AUDIT_OPINION.toString())))
            .andExpect(jsonPath("$.[*].auditStaffName").value(hasItem(DEFAULT_AUDIT_STAFF_NAME.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getEnterpriseAuthRecord() throws Exception {
        // Initialize the database
        enterpriseAuthRecordRepository.saveAndFlush(enterpriseAuthRecord);

        // Get the enterpriseAuthRecord
        restEnterpriseAuthRecordMockMvc.perform(get("/api/enterprise-auth-records/{id}", enterpriseAuthRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enterpriseAuthRecord.getId().intValue()))
            .andExpect(jsonPath("$.txnId").value(DEFAULT_TXN_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.legalPersonName").value(DEFAULT_LEGAL_PERSON_NAME.toString()))
            .andExpect(jsonPath("$.legalPersonIdCardNum").value(DEFAULT_LEGAL_PERSON_ID_CARD_NUM.toString()))
            .andExpect(jsonPath("$.legalPersonMobile").value(DEFAULT_LEGAL_PERSON_MOBILE.toString()))
            .andExpect(jsonPath("$.enterpriseTel").value(DEFAULT_ENTERPRISE_TEL.toString()))
            .andExpect(jsonPath("$.businessLicenseImgId").value(DEFAULT_BUSINESS_LICENSE_IMG_ID))
            .andExpect(jsonPath("$.authStatus").value(DEFAULT_AUTH_STATUS))
            .andExpect(jsonPath("$.auditOpinion").value(DEFAULT_AUDIT_OPINION.toString()))
            .andExpect(jsonPath("$.auditStaffName").value(DEFAULT_AUDIT_STAFF_NAME.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnterpriseAuthRecord() throws Exception {
        // Get the enterpriseAuthRecord
        restEnterpriseAuthRecordMockMvc.perform(get("/api/enterprise-auth-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnterpriseAuthRecord() throws Exception {
        // Initialize the database
        enterpriseAuthRecordService.save(enterpriseAuthRecord);

        int databaseSizeBeforeUpdate = enterpriseAuthRecordRepository.findAll().size();

        // Update the enterpriseAuthRecord
        EnterpriseAuthRecord updatedEnterpriseAuthRecord = enterpriseAuthRecordRepository.findById(enterpriseAuthRecord.getId()).get();
        // Disconnect from session so that the updates on updatedEnterpriseAuthRecord are not directly saved in db
        em.detach(updatedEnterpriseAuthRecord);
        updatedEnterpriseAuthRecord
            .txnId(UPDATED_TXN_ID)
            .userId(UPDATED_USER_ID)
            .name(UPDATED_NAME)
            .legalPersonName(UPDATED_LEGAL_PERSON_NAME)
            .legalPersonIdCardNum(UPDATED_LEGAL_PERSON_ID_CARD_NUM)
            .legalPersonMobile(UPDATED_LEGAL_PERSON_MOBILE)
            .enterpriseTel(UPDATED_ENTERPRISE_TEL)
            .businessLicenseImgId(UPDATED_BUSINESS_LICENSE_IMG_ID)
            .authStatus(UPDATED_AUTH_STATUS)
            .auditOpinion(UPDATED_AUDIT_OPINION)
            .auditStaffName(UPDATED_AUDIT_STAFF_NAME)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restEnterpriseAuthRecordMockMvc.perform(put("/api/enterprise-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnterpriseAuthRecord)))
            .andExpect(status().isOk());

        // Validate the EnterpriseAuthRecord in the database
        List<EnterpriseAuthRecord> enterpriseAuthRecordList = enterpriseAuthRecordRepository.findAll();
        assertThat(enterpriseAuthRecordList).hasSize(databaseSizeBeforeUpdate);
        EnterpriseAuthRecord testEnterpriseAuthRecord = enterpriseAuthRecordList.get(enterpriseAuthRecordList.size() - 1);
        assertThat(testEnterpriseAuthRecord.getTxnId()).isEqualTo(UPDATED_TXN_ID);
        assertThat(testEnterpriseAuthRecord.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testEnterpriseAuthRecord.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEnterpriseAuthRecord.getLegalPersonName()).isEqualTo(UPDATED_LEGAL_PERSON_NAME);
        assertThat(testEnterpriseAuthRecord.getLegalPersonIdCardNum()).isEqualTo(UPDATED_LEGAL_PERSON_ID_CARD_NUM);
        assertThat(testEnterpriseAuthRecord.getLegalPersonMobile()).isEqualTo(UPDATED_LEGAL_PERSON_MOBILE);
        assertThat(testEnterpriseAuthRecord.getEnterpriseTel()).isEqualTo(UPDATED_ENTERPRISE_TEL);
        assertThat(testEnterpriseAuthRecord.getBusinessLicenseImgId()).isEqualTo(UPDATED_BUSINESS_LICENSE_IMG_ID);
        assertThat(testEnterpriseAuthRecord.getAuthStatus()).isEqualTo(UPDATED_AUTH_STATUS);
        assertThat(testEnterpriseAuthRecord.getAuditOpinion()).isEqualTo(UPDATED_AUDIT_OPINION);
        assertThat(testEnterpriseAuthRecord.getAuditStaffName()).isEqualTo(UPDATED_AUDIT_STAFF_NAME);
        assertThat(testEnterpriseAuthRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEnterpriseAuthRecord.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingEnterpriseAuthRecord() throws Exception {
        int databaseSizeBeforeUpdate = enterpriseAuthRecordRepository.findAll().size();

        // Create the EnterpriseAuthRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnterpriseAuthRecordMockMvc.perform(put("/api/enterprise-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the EnterpriseAuthRecord in the database
        List<EnterpriseAuthRecord> enterpriseAuthRecordList = enterpriseAuthRecordRepository.findAll();
        assertThat(enterpriseAuthRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnterpriseAuthRecord() throws Exception {
        // Initialize the database
        enterpriseAuthRecordService.save(enterpriseAuthRecord);

        int databaseSizeBeforeDelete = enterpriseAuthRecordRepository.findAll().size();

        // Get the enterpriseAuthRecord
        restEnterpriseAuthRecordMockMvc.perform(delete("/api/enterprise-auth-records/{id}", enterpriseAuthRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnterpriseAuthRecord> enterpriseAuthRecordList = enterpriseAuthRecordRepository.findAll();
        assertThat(enterpriseAuthRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnterpriseAuthRecord.class);
        EnterpriseAuthRecord enterpriseAuthRecord1 = new EnterpriseAuthRecord();
        enterpriseAuthRecord1.setId(1L);
        EnterpriseAuthRecord enterpriseAuthRecord2 = new EnterpriseAuthRecord();
        enterpriseAuthRecord2.setId(enterpriseAuthRecord1.getId());
        assertThat(enterpriseAuthRecord1).isEqualTo(enterpriseAuthRecord2);
        enterpriseAuthRecord2.setId(2L);
        assertThat(enterpriseAuthRecord1).isNotEqualTo(enterpriseAuthRecord2);
        enterpriseAuthRecord1.setId(null);
        assertThat(enterpriseAuthRecord1).isNotEqualTo(enterpriseAuthRecord2);
    }
}
