package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.BankcardAuthRecord;
import com.datangliang.app.repository.BankcardAuthRecordRepository;
import com.datangliang.app.service.BankcardAuthRecordService;
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
 * Test class for the BankcardAuthRecordResource REST controller.
 *
 * @see BankcardAuthRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class BankcardAuthRecordResourceIntTest {

    private static final String DEFAULT_TXN_ID = "AAAAAAAAAA";
    private static final String UPDATED_TXN_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_CARD_NUM = "AAAAAAAAAA";
    private static final String UPDATED_BANK_CARD_NUM = "BBBBBBBBBB";

    private static final Integer DEFAULT_BANK_CARD_IMG_ID = 1;
    private static final Integer UPDATED_BANK_CARD_IMG_ID = 2;

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
    private BankcardAuthRecordRepository bankcardAuthRecordRepository;

    @Autowired
    private BankcardAuthRecordService bankcardAuthRecordService;

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

    private MockMvc restBankcardAuthRecordMockMvc;

    private BankcardAuthRecord bankcardAuthRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BankcardAuthRecordResource bankcardAuthRecordResource = new BankcardAuthRecordResource(bankcardAuthRecordService);
        this.restBankcardAuthRecordMockMvc = MockMvcBuilders.standaloneSetup(bankcardAuthRecordResource)
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
    public static BankcardAuthRecord createEntity(EntityManager em) {
        BankcardAuthRecord bankcardAuthRecord = new BankcardAuthRecord()
            .txnId(DEFAULT_TXN_ID)
            .userId(DEFAULT_USER_ID)
            .bankName(DEFAULT_BANK_NAME)
            .bankAccountName(DEFAULT_BANK_ACCOUNT_NAME)
            .bankCardNum(DEFAULT_BANK_CARD_NUM)
            .bankCardImgId(DEFAULT_BANK_CARD_IMG_ID)
            .authStatus(DEFAULT_AUTH_STATUS)
            .auditOpinion(DEFAULT_AUDIT_OPINION)
            .auditStaffName(DEFAULT_AUDIT_STAFF_NAME)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return bankcardAuthRecord;
    }

    @Before
    public void initTest() {
        bankcardAuthRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createBankcardAuthRecord() throws Exception {
        int databaseSizeBeforeCreate = bankcardAuthRecordRepository.findAll().size();

        // Create the BankcardAuthRecord
        restBankcardAuthRecordMockMvc.perform(post("/api/bankcard-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankcardAuthRecord)))
            .andExpect(status().isCreated());

        // Validate the BankcardAuthRecord in the database
        List<BankcardAuthRecord> bankcardAuthRecordList = bankcardAuthRecordRepository.findAll();
        assertThat(bankcardAuthRecordList).hasSize(databaseSizeBeforeCreate + 1);
        BankcardAuthRecord testBankcardAuthRecord = bankcardAuthRecordList.get(bankcardAuthRecordList.size() - 1);
        assertThat(testBankcardAuthRecord.getTxnId()).isEqualTo(DEFAULT_TXN_ID);
        assertThat(testBankcardAuthRecord.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBankcardAuthRecord.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBankcardAuthRecord.getBankAccountName()).isEqualTo(DEFAULT_BANK_ACCOUNT_NAME);
        assertThat(testBankcardAuthRecord.getBankCardNum()).isEqualTo(DEFAULT_BANK_CARD_NUM);
        assertThat(testBankcardAuthRecord.getBankCardImgId()).isEqualTo(DEFAULT_BANK_CARD_IMG_ID);
        assertThat(testBankcardAuthRecord.getAuthStatus()).isEqualTo(DEFAULT_AUTH_STATUS);
        assertThat(testBankcardAuthRecord.getAuditOpinion()).isEqualTo(DEFAULT_AUDIT_OPINION);
        assertThat(testBankcardAuthRecord.getAuditStaffName()).isEqualTo(DEFAULT_AUDIT_STAFF_NAME);
        assertThat(testBankcardAuthRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testBankcardAuthRecord.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createBankcardAuthRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankcardAuthRecordRepository.findAll().size();

        // Create the BankcardAuthRecord with an existing ID
        bankcardAuthRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankcardAuthRecordMockMvc.perform(post("/api/bankcard-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankcardAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the BankcardAuthRecord in the database
        List<BankcardAuthRecord> bankcardAuthRecordList = bankcardAuthRecordRepository.findAll();
        assertThat(bankcardAuthRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBankcardAuthRecords() throws Exception {
        // Initialize the database
        bankcardAuthRecordRepository.saveAndFlush(bankcardAuthRecord);

        // Get all the bankcardAuthRecordList
        restBankcardAuthRecordMockMvc.perform(get("/api/bankcard-auth-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankcardAuthRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnId").value(hasItem(DEFAULT_TXN_ID.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME.toString())))
            .andExpect(jsonPath("$.[*].bankAccountName").value(hasItem(DEFAULT_BANK_ACCOUNT_NAME.toString())))
            .andExpect(jsonPath("$.[*].bankCardNum").value(hasItem(DEFAULT_BANK_CARD_NUM.toString())))
            .andExpect(jsonPath("$.[*].bankCardImgId").value(hasItem(DEFAULT_BANK_CARD_IMG_ID)))
            .andExpect(jsonPath("$.[*].authStatus").value(hasItem(DEFAULT_AUTH_STATUS)))
            .andExpect(jsonPath("$.[*].auditOpinion").value(hasItem(DEFAULT_AUDIT_OPINION.toString())))
            .andExpect(jsonPath("$.[*].auditStaffName").value(hasItem(DEFAULT_AUDIT_STAFF_NAME.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getBankcardAuthRecord() throws Exception {
        // Initialize the database
        bankcardAuthRecordRepository.saveAndFlush(bankcardAuthRecord);

        // Get the bankcardAuthRecord
        restBankcardAuthRecordMockMvc.perform(get("/api/bankcard-auth-records/{id}", bankcardAuthRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bankcardAuthRecord.getId().intValue()))
            .andExpect(jsonPath("$.txnId").value(DEFAULT_TXN_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME.toString()))
            .andExpect(jsonPath("$.bankAccountName").value(DEFAULT_BANK_ACCOUNT_NAME.toString()))
            .andExpect(jsonPath("$.bankCardNum").value(DEFAULT_BANK_CARD_NUM.toString()))
            .andExpect(jsonPath("$.bankCardImgId").value(DEFAULT_BANK_CARD_IMG_ID))
            .andExpect(jsonPath("$.authStatus").value(DEFAULT_AUTH_STATUS))
            .andExpect(jsonPath("$.auditOpinion").value(DEFAULT_AUDIT_OPINION.toString()))
            .andExpect(jsonPath("$.auditStaffName").value(DEFAULT_AUDIT_STAFF_NAME.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBankcardAuthRecord() throws Exception {
        // Get the bankcardAuthRecord
        restBankcardAuthRecordMockMvc.perform(get("/api/bankcard-auth-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankcardAuthRecord() throws Exception {
        // Initialize the database
        bankcardAuthRecordService.save(bankcardAuthRecord);

        int databaseSizeBeforeUpdate = bankcardAuthRecordRepository.findAll().size();

        // Update the bankcardAuthRecord
        BankcardAuthRecord updatedBankcardAuthRecord = bankcardAuthRecordRepository.findById(bankcardAuthRecord.getId()).get();
        // Disconnect from session so that the updates on updatedBankcardAuthRecord are not directly saved in db
        em.detach(updatedBankcardAuthRecord);
        updatedBankcardAuthRecord
            .txnId(UPDATED_TXN_ID)
            .userId(UPDATED_USER_ID)
            .bankName(UPDATED_BANK_NAME)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankCardNum(UPDATED_BANK_CARD_NUM)
            .bankCardImgId(UPDATED_BANK_CARD_IMG_ID)
            .authStatus(UPDATED_AUTH_STATUS)
            .auditOpinion(UPDATED_AUDIT_OPINION)
            .auditStaffName(UPDATED_AUDIT_STAFF_NAME)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restBankcardAuthRecordMockMvc.perform(put("/api/bankcard-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBankcardAuthRecord)))
            .andExpect(status().isOk());

        // Validate the BankcardAuthRecord in the database
        List<BankcardAuthRecord> bankcardAuthRecordList = bankcardAuthRecordRepository.findAll();
        assertThat(bankcardAuthRecordList).hasSize(databaseSizeBeforeUpdate);
        BankcardAuthRecord testBankcardAuthRecord = bankcardAuthRecordList.get(bankcardAuthRecordList.size() - 1);
        assertThat(testBankcardAuthRecord.getTxnId()).isEqualTo(UPDATED_TXN_ID);
        assertThat(testBankcardAuthRecord.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBankcardAuthRecord.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBankcardAuthRecord.getBankAccountName()).isEqualTo(UPDATED_BANK_ACCOUNT_NAME);
        assertThat(testBankcardAuthRecord.getBankCardNum()).isEqualTo(UPDATED_BANK_CARD_NUM);
        assertThat(testBankcardAuthRecord.getBankCardImgId()).isEqualTo(UPDATED_BANK_CARD_IMG_ID);
        assertThat(testBankcardAuthRecord.getAuthStatus()).isEqualTo(UPDATED_AUTH_STATUS);
        assertThat(testBankcardAuthRecord.getAuditOpinion()).isEqualTo(UPDATED_AUDIT_OPINION);
        assertThat(testBankcardAuthRecord.getAuditStaffName()).isEqualTo(UPDATED_AUDIT_STAFF_NAME);
        assertThat(testBankcardAuthRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testBankcardAuthRecord.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingBankcardAuthRecord() throws Exception {
        int databaseSizeBeforeUpdate = bankcardAuthRecordRepository.findAll().size();

        // Create the BankcardAuthRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankcardAuthRecordMockMvc.perform(put("/api/bankcard-auth-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankcardAuthRecord)))
            .andExpect(status().isBadRequest());

        // Validate the BankcardAuthRecord in the database
        List<BankcardAuthRecord> bankcardAuthRecordList = bankcardAuthRecordRepository.findAll();
        assertThat(bankcardAuthRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBankcardAuthRecord() throws Exception {
        // Initialize the database
        bankcardAuthRecordService.save(bankcardAuthRecord);

        int databaseSizeBeforeDelete = bankcardAuthRecordRepository.findAll().size();

        // Get the bankcardAuthRecord
        restBankcardAuthRecordMockMvc.perform(delete("/api/bankcard-auth-records/{id}", bankcardAuthRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BankcardAuthRecord> bankcardAuthRecordList = bankcardAuthRecordRepository.findAll();
        assertThat(bankcardAuthRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankcardAuthRecord.class);
        BankcardAuthRecord bankcardAuthRecord1 = new BankcardAuthRecord();
        bankcardAuthRecord1.setId(1L);
        BankcardAuthRecord bankcardAuthRecord2 = new BankcardAuthRecord();
        bankcardAuthRecord2.setId(bankcardAuthRecord1.getId());
        assertThat(bankcardAuthRecord1).isEqualTo(bankcardAuthRecord2);
        bankcardAuthRecord2.setId(2L);
        assertThat(bankcardAuthRecord1).isNotEqualTo(bankcardAuthRecord2);
        bankcardAuthRecord1.setId(null);
        assertThat(bankcardAuthRecord1).isNotEqualTo(bankcardAuthRecord2);
    }
}
