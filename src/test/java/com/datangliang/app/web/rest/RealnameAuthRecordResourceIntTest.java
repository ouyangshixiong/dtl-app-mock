package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.RealnameAuthRecord;
import com.datangliang.app.repository.RealnameAuthRecordRepository;
import com.datangliang.app.service.RealnameAuthRecordService;
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
 * Test class for the RealnameAuthRecordResource REST controller.
 *
 * @see RealnameAuthRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class RealnameAuthRecordResourceIntTest {

    private static final String DEFAULT_TXN_ID = "AAAAAAAAAA";
    private static final String UPDATED_TXN_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_REAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CARD_NUM = "AAAAAAAAAA";
    private static final String UPDATED_ID_CARD_NUM = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_CARD_IMG_A = 1;
    private static final Integer UPDATED_ID_CARD_IMG_A = 2;

    private static final Integer DEFAULT_ID_CARD_IMG_B = 1;
    private static final Integer UPDATED_ID_CARD_IMG_B = 2;

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
    private RealnameAuthRecordRepository realnameAuthRecordRepository;

    @Autowired
    private RealnameAuthRecordService realnameAuthRecordService;

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

    private MockMvc restRealnameAuthRecordMockMvc;

    private RealnameAuthRecord realnameAuthRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RealnameAuthRecordResource realnameAuthRecordResource = new RealnameAuthRecordResource(realnameAuthRecordService);
        this.restRealnameAuthRecordMockMvc = MockMvcBuilders.standaloneSetup(realnameAuthRecordResource)
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
    public static RealnameAuthRecord createEntity(EntityManager em) {
        RealnameAuthRecord realnameAuthRecord = new RealnameAuthRecord()
            .txnId(DEFAULT_TXN_ID)
            .userId(DEFAULT_USER_ID)
            .realName(DEFAULT_REAL_NAME)
            .idCardNum(DEFAULT_ID_CARD_NUM)
            .idCardImgA(DEFAULT_ID_CARD_IMG_A)
            .idCardImgB(DEFAULT_ID_CARD_IMG_B)
            .authStatus(DEFAULT_AUTH_STATUS)
            .auditOpinion(DEFAULT_AUDIT_OPINION)
            .auditStaffName(DEFAULT_AUDIT_STAFF_NAME)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return realnameAuthRecord;
    }

    @Before
    public void initTest() {
        realnameAuthRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createRealnameAuthRecord() throws Exception {
        int databaseSizeBeforeCreate = realnameAuthRecordRepository.findAll().size();

        // Create the RealnameAuthRecord
        restRealnameAuthRecordMockMvc.perform(post("/api/realname-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(realnameAuthRecord)))
            .andExpect(status().isCreated());

        // Validate the RealnameAuthRecord in the database
        List<RealnameAuthRecord> realnameAuthRecordList = realnameAuthRecordRepository.findAll();
        assertThat(realnameAuthRecordList).hasSize(databaseSizeBeforeCreate + 1);
        RealnameAuthRecord testRealnameAuthRecord = realnameAuthRecordList.get(realnameAuthRecordList.size() - 1);
        assertThat(testRealnameAuthRecord.getTxnId()).isEqualTo(DEFAULT_TXN_ID);
        assertThat(testRealnameAuthRecord.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testRealnameAuthRecord.getRealName()).isEqualTo(DEFAULT_REAL_NAME);
        assertThat(testRealnameAuthRecord.getIdCardNum()).isEqualTo(DEFAULT_ID_CARD_NUM);
        assertThat(testRealnameAuthRecord.getIdCardImgA()).isEqualTo(DEFAULT_ID_CARD_IMG_A);
        assertThat(testRealnameAuthRecord.getIdCardImgB()).isEqualTo(DEFAULT_ID_CARD_IMG_B);
        assertThat(testRealnameAuthRecord.getAuthStatus()).isEqualTo(DEFAULT_AUTH_STATUS);
        assertThat(testRealnameAuthRecord.getAuditOpinion()).isEqualTo(DEFAULT_AUDIT_OPINION);
        assertThat(testRealnameAuthRecord.getAuditStaffName()).isEqualTo(DEFAULT_AUDIT_STAFF_NAME);
        assertThat(testRealnameAuthRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testRealnameAuthRecord.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createRealnameAuthRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = realnameAuthRecordRepository.findAll().size();

        // Create the RealnameAuthRecord with an existing ID
        realnameAuthRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRealnameAuthRecordMockMvc.perform(post("/api/realname-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(realnameAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the RealnameAuthRecord in the database
        List<RealnameAuthRecord> realnameAuthRecordList = realnameAuthRecordRepository.findAll();
        assertThat(realnameAuthRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRealnameAuthRecords() throws Exception {
        // Initialize the database
        realnameAuthRecordRepository.saveAndFlush(realnameAuthRecord);

        // Get all the realnameAuthRecordList
        restRealnameAuthRecordMockMvc.perform(get("/api/realname-auth-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(realnameAuthRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnId").value(hasItem(DEFAULT_TXN_ID.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].realName").value(hasItem(DEFAULT_REAL_NAME.toString())))
            .andExpect(jsonPath("$.[*].idCardNum").value(hasItem(DEFAULT_ID_CARD_NUM.toString())))
            .andExpect(jsonPath("$.[*].idCardImgA").value(hasItem(DEFAULT_ID_CARD_IMG_A)))
            .andExpect(jsonPath("$.[*].idCardImgB").value(hasItem(DEFAULT_ID_CARD_IMG_B)))
            .andExpect(jsonPath("$.[*].authStatus").value(hasItem(DEFAULT_AUTH_STATUS)))
            .andExpect(jsonPath("$.[*].auditOpinion").value(hasItem(DEFAULT_AUDIT_OPINION.toString())))
            .andExpect(jsonPath("$.[*].auditStaffName").value(hasItem(DEFAULT_AUDIT_STAFF_NAME.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getRealnameAuthRecord() throws Exception {
        // Initialize the database
        realnameAuthRecordRepository.saveAndFlush(realnameAuthRecord);

        // Get the realnameAuthRecord
        restRealnameAuthRecordMockMvc.perform(get("/api/realname-auth-records/{id}", realnameAuthRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(realnameAuthRecord.getId().intValue()))
            .andExpect(jsonPath("$.txnId").value(DEFAULT_TXN_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.realName").value(DEFAULT_REAL_NAME.toString()))
            .andExpect(jsonPath("$.idCardNum").value(DEFAULT_ID_CARD_NUM.toString()))
            .andExpect(jsonPath("$.idCardImgA").value(DEFAULT_ID_CARD_IMG_A))
            .andExpect(jsonPath("$.idCardImgB").value(DEFAULT_ID_CARD_IMG_B))
            .andExpect(jsonPath("$.authStatus").value(DEFAULT_AUTH_STATUS))
            .andExpect(jsonPath("$.auditOpinion").value(DEFAULT_AUDIT_OPINION.toString()))
            .andExpect(jsonPath("$.auditStaffName").value(DEFAULT_AUDIT_STAFF_NAME.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRealnameAuthRecord() throws Exception {
        // Get the realnameAuthRecord
        restRealnameAuthRecordMockMvc.perform(get("/api/realname-auth-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRealnameAuthRecord() throws Exception {
        // Initialize the database
        realnameAuthRecordService.save(realnameAuthRecord);

        int databaseSizeBeforeUpdate = realnameAuthRecordRepository.findAll().size();

        // Update the realnameAuthRecord
        RealnameAuthRecord updatedRealnameAuthRecord = realnameAuthRecordRepository.findById(realnameAuthRecord.getId()).get();
        // Disconnect from session so that the updates on updatedRealnameAuthRecord are not directly saved in db
        em.detach(updatedRealnameAuthRecord);
        updatedRealnameAuthRecord
            .txnId(UPDATED_TXN_ID)
            .userId(UPDATED_USER_ID)
            .realName(UPDATED_REAL_NAME)
            .idCardNum(UPDATED_ID_CARD_NUM)
            .idCardImgA(UPDATED_ID_CARD_IMG_A)
            .idCardImgB(UPDATED_ID_CARD_IMG_B)
            .authStatus(UPDATED_AUTH_STATUS)
            .auditOpinion(UPDATED_AUDIT_OPINION)
            .auditStaffName(UPDATED_AUDIT_STAFF_NAME)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restRealnameAuthRecordMockMvc.perform(put("/api/realname-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRealnameAuthRecord)))
            .andExpect(status().isOk());

        // Validate the RealnameAuthRecord in the database
        List<RealnameAuthRecord> realnameAuthRecordList = realnameAuthRecordRepository.findAll();
        assertThat(realnameAuthRecordList).hasSize(databaseSizeBeforeUpdate);
        RealnameAuthRecord testRealnameAuthRecord = realnameAuthRecordList.get(realnameAuthRecordList.size() - 1);
        assertThat(testRealnameAuthRecord.getTxnId()).isEqualTo(UPDATED_TXN_ID);
        assertThat(testRealnameAuthRecord.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testRealnameAuthRecord.getRealName()).isEqualTo(UPDATED_REAL_NAME);
        assertThat(testRealnameAuthRecord.getIdCardNum()).isEqualTo(UPDATED_ID_CARD_NUM);
        assertThat(testRealnameAuthRecord.getIdCardImgA()).isEqualTo(UPDATED_ID_CARD_IMG_A);
        assertThat(testRealnameAuthRecord.getIdCardImgB()).isEqualTo(UPDATED_ID_CARD_IMG_B);
        assertThat(testRealnameAuthRecord.getAuthStatus()).isEqualTo(UPDATED_AUTH_STATUS);
        assertThat(testRealnameAuthRecord.getAuditOpinion()).isEqualTo(UPDATED_AUDIT_OPINION);
        assertThat(testRealnameAuthRecord.getAuditStaffName()).isEqualTo(UPDATED_AUDIT_STAFF_NAME);
        assertThat(testRealnameAuthRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testRealnameAuthRecord.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingRealnameAuthRecord() throws Exception {
        int databaseSizeBeforeUpdate = realnameAuthRecordRepository.findAll().size();

        // Create the RealnameAuthRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRealnameAuthRecordMockMvc.perform(put("/api/realname-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(realnameAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the RealnameAuthRecord in the database
        List<RealnameAuthRecord> realnameAuthRecordList = realnameAuthRecordRepository.findAll();
        assertThat(realnameAuthRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRealnameAuthRecord() throws Exception {
        // Initialize the database
        realnameAuthRecordService.save(realnameAuthRecord);

        int databaseSizeBeforeDelete = realnameAuthRecordRepository.findAll().size();

        // Get the realnameAuthRecord
        restRealnameAuthRecordMockMvc.perform(delete("/api/realname-auth-records/{id}", realnameAuthRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RealnameAuthRecord> realnameAuthRecordList = realnameAuthRecordRepository.findAll();
        assertThat(realnameAuthRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RealnameAuthRecord.class);
        RealnameAuthRecord realnameAuthRecord1 = new RealnameAuthRecord();
        realnameAuthRecord1.setId(1L);
        RealnameAuthRecord realnameAuthRecord2 = new RealnameAuthRecord();
        realnameAuthRecord2.setId(realnameAuthRecord1.getId());
        assertThat(realnameAuthRecord1).isEqualTo(realnameAuthRecord2);
        realnameAuthRecord2.setId(2L);
        assertThat(realnameAuthRecord1).isNotEqualTo(realnameAuthRecord2);
        realnameAuthRecord1.setId(null);
        assertThat(realnameAuthRecord1).isNotEqualTo(realnameAuthRecord2);
    }
}
