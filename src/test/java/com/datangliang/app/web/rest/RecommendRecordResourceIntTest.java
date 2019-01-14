package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.RecommendRecord;
import com.datangliang.app.repository.RecommendRecordRepository;
import com.datangliang.app.service.RecommendRecordService;
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
 * Test class for the RecommendRecordResource REST controller.
 *
 * @see RecommendRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class RecommendRecordResourceIntTest {

    private static final Integer DEFAULT_REC_USER_ID = 1;
    private static final Integer UPDATED_REC_USER_ID = 2;

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_IDENTITY_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_IDENTITY_FLAG = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RecommendRecordRepository recommendRecordRepository;

    @Autowired
    private RecommendRecordService recommendRecordService;

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

    private MockMvc restRecommendRecordMockMvc;

    private RecommendRecord recommendRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RecommendRecordResource recommendRecordResource = new RecommendRecordResource(recommendRecordService);
        this.restRecommendRecordMockMvc = MockMvcBuilders.standaloneSetup(recommendRecordResource)
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
    public static RecommendRecord createEntity(EntityManager em) {
        RecommendRecord recommendRecord = new RecommendRecord()
            .recUserId(DEFAULT_REC_USER_ID)
            .userId(DEFAULT_USER_ID)
            .status(DEFAULT_STATUS)
            .identityFlag(DEFAULT_IDENTITY_FLAG)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return recommendRecord;
    }

    @Before
    public void initTest() {
        recommendRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createRecommendRecord() throws Exception {
        int databaseSizeBeforeCreate = recommendRecordRepository.findAll().size();

        // Create the RecommendRecord
        restRecommendRecordMockMvc.perform(post("/api/recommend-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recommendRecord)))
            .andExpect(status().isCreated());

        // Validate the RecommendRecord in the database
        List<RecommendRecord> recommendRecordList = recommendRecordRepository.findAll();
        assertThat(recommendRecordList).hasSize(databaseSizeBeforeCreate + 1);
        RecommendRecord testRecommendRecord = recommendRecordList.get(recommendRecordList.size() - 1);
        assertThat(testRecommendRecord.getRecUserId()).isEqualTo(DEFAULT_REC_USER_ID);
        assertThat(testRecommendRecord.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testRecommendRecord.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRecommendRecord.getIdentityFlag()).isEqualTo(DEFAULT_IDENTITY_FLAG);
        assertThat(testRecommendRecord.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testRecommendRecord.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createRecommendRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recommendRecordRepository.findAll().size();

        // Create the RecommendRecord with an existing ID
        recommendRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecommendRecordMockMvc.perform(post("/api/recommend-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recommendRecord)))
            .andExpect(status().isBadRequest());

        // Validate the RecommendRecord in the database
        List<RecommendRecord> recommendRecordList = recommendRecordRepository.findAll();
        assertThat(recommendRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRecommendRecords() throws Exception {
        // Initialize the database
        recommendRecordRepository.saveAndFlush(recommendRecord);

        // Get all the recommendRecordList
        restRecommendRecordMockMvc.perform(get("/api/recommend-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recommendRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].recUserId").value(hasItem(DEFAULT_REC_USER_ID)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].identityFlag").value(hasItem(DEFAULT_IDENTITY_FLAG.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getRecommendRecord() throws Exception {
        // Initialize the database
        recommendRecordRepository.saveAndFlush(recommendRecord);

        // Get the recommendRecord
        restRecommendRecordMockMvc.perform(get("/api/recommend-records/{id}", recommendRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recommendRecord.getId().intValue()))
            .andExpect(jsonPath("$.recUserId").value(DEFAULT_REC_USER_ID))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.identityFlag").value(DEFAULT_IDENTITY_FLAG.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRecommendRecord() throws Exception {
        // Get the recommendRecord
        restRecommendRecordMockMvc.perform(get("/api/recommend-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRecommendRecord() throws Exception {
        // Initialize the database
        recommendRecordService.save(recommendRecord);

        int databaseSizeBeforeUpdate = recommendRecordRepository.findAll().size();

        // Update the recommendRecord
        RecommendRecord updatedRecommendRecord = recommendRecordRepository.findById(recommendRecord.getId()).get();
        // Disconnect from session so that the updates on updatedRecommendRecord are not directly saved in db
        em.detach(updatedRecommendRecord);
        updatedRecommendRecord
            .recUserId(UPDATED_REC_USER_ID)
            .userId(UPDATED_USER_ID)
            .status(UPDATED_STATUS)
            .identityFlag(UPDATED_IDENTITY_FLAG)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restRecommendRecordMockMvc.perform(put("/api/recommend-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRecommendRecord)))
            .andExpect(status().isOk());

        // Validate the RecommendRecord in the database
        List<RecommendRecord> recommendRecordList = recommendRecordRepository.findAll();
        assertThat(recommendRecordList).hasSize(databaseSizeBeforeUpdate);
        RecommendRecord testRecommendRecord = recommendRecordList.get(recommendRecordList.size() - 1);
        assertThat(testRecommendRecord.getRecUserId()).isEqualTo(UPDATED_REC_USER_ID);
        assertThat(testRecommendRecord.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testRecommendRecord.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRecommendRecord.getIdentityFlag()).isEqualTo(UPDATED_IDENTITY_FLAG);
        assertThat(testRecommendRecord.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testRecommendRecord.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingRecommendRecord() throws Exception {
        int databaseSizeBeforeUpdate = recommendRecordRepository.findAll().size();

        // Create the RecommendRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecommendRecordMockMvc.perform(put("/api/recommend-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recommendRecord)))
            .andExpect(status().isBadRequest());

        // Validate the RecommendRecord in the database
        List<RecommendRecord> recommendRecordList = recommendRecordRepository.findAll();
        assertThat(recommendRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRecommendRecord() throws Exception {
        // Initialize the database
        recommendRecordService.save(recommendRecord);

        int databaseSizeBeforeDelete = recommendRecordRepository.findAll().size();

        // Get the recommendRecord
        restRecommendRecordMockMvc.perform(delete("/api/recommend-records/{id}", recommendRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RecommendRecord> recommendRecordList = recommendRecordRepository.findAll();
        assertThat(recommendRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecommendRecord.class);
        RecommendRecord recommendRecord1 = new RecommendRecord();
        recommendRecord1.setId(1L);
        RecommendRecord recommendRecord2 = new RecommendRecord();
        recommendRecord2.setId(recommendRecord1.getId());
        assertThat(recommendRecord1).isEqualTo(recommendRecord2);
        recommendRecord2.setId(2L);
        assertThat(recommendRecord1).isNotEqualTo(recommendRecord2);
        recommendRecord1.setId(null);
        assertThat(recommendRecord1).isNotEqualTo(recommendRecord2);
    }
}
