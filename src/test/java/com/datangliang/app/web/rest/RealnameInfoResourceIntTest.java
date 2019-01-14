package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.RealnameInfo;
import com.datangliang.app.repository.RealnameInfoRepository;
import com.datangliang.app.service.RealnameInfoService;
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
 * Test class for the RealnameInfoResource REST controller.
 *
 * @see RealnameInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class RealnameInfoResourceIntTest {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final String DEFAULT_REAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CARD_NUM = "AAAAAAAAAA";
    private static final String UPDATED_ID_CARD_NUM = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_CARD_IMG_ID_A = 1;
    private static final Integer UPDATED_ID_CARD_IMG_ID_A = 2;

    private static final Integer DEFAULT_ID_CARD_IMG_ID_B = 1;
    private static final Integer UPDATED_ID_CARD_IMG_ID_B = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RealnameInfoRepository realnameInfoRepository;

    @Autowired
    private RealnameInfoService realnameInfoService;

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

    private MockMvc restRealnameInfoMockMvc;

    private RealnameInfo realnameInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RealnameInfoResource realnameInfoResource = new RealnameInfoResource(realnameInfoService);
        this.restRealnameInfoMockMvc = MockMvcBuilders.standaloneSetup(realnameInfoResource)
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
    public static RealnameInfo createEntity(EntityManager em) {
        RealnameInfo realnameInfo = new RealnameInfo()
            .userId(DEFAULT_USER_ID)
            .realName(DEFAULT_REAL_NAME)
            .idCardNum(DEFAULT_ID_CARD_NUM)
            .idCardImgIdA(DEFAULT_ID_CARD_IMG_ID_A)
            .idCardImgIdB(DEFAULT_ID_CARD_IMG_ID_B)
            .status(DEFAULT_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return realnameInfo;
    }

    @Before
    public void initTest() {
        realnameInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createRealnameInfo() throws Exception {
        int databaseSizeBeforeCreate = realnameInfoRepository.findAll().size();

        // Create the RealnameInfo
        restRealnameInfoMockMvc.perform(post("/api/realname-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(realnameInfo)))
            .andExpect(status().isCreated());

        // Validate the RealnameInfo in the database
        List<RealnameInfo> realnameInfoList = realnameInfoRepository.findAll();
        assertThat(realnameInfoList).hasSize(databaseSizeBeforeCreate + 1);
        RealnameInfo testRealnameInfo = realnameInfoList.get(realnameInfoList.size() - 1);
        assertThat(testRealnameInfo.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testRealnameInfo.getRealName()).isEqualTo(DEFAULT_REAL_NAME);
        assertThat(testRealnameInfo.getIdCardNum()).isEqualTo(DEFAULT_ID_CARD_NUM);
        assertThat(testRealnameInfo.getIdCardImgIdA()).isEqualTo(DEFAULT_ID_CARD_IMG_ID_A);
        assertThat(testRealnameInfo.getIdCardImgIdB()).isEqualTo(DEFAULT_ID_CARD_IMG_ID_B);
        assertThat(testRealnameInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRealnameInfo.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testRealnameInfo.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createRealnameInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = realnameInfoRepository.findAll().size();

        // Create the RealnameInfo with an existing ID
        realnameInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRealnameInfoMockMvc.perform(post("/api/realname-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(realnameInfo)))
            .andExpect(status().isBadRequest());

        // Validate the RealnameInfo in the database
        List<RealnameInfo> realnameInfoList = realnameInfoRepository.findAll();
        assertThat(realnameInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRealnameInfos() throws Exception {
        // Initialize the database
        realnameInfoRepository.saveAndFlush(realnameInfo);

        // Get all the realnameInfoList
        restRealnameInfoMockMvc.perform(get("/api/realname-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(realnameInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].realName").value(hasItem(DEFAULT_REAL_NAME.toString())))
            .andExpect(jsonPath("$.[*].idCardNum").value(hasItem(DEFAULT_ID_CARD_NUM.toString())))
            .andExpect(jsonPath("$.[*].idCardImgIdA").value(hasItem(DEFAULT_ID_CARD_IMG_ID_A)))
            .andExpect(jsonPath("$.[*].idCardImgIdB").value(hasItem(DEFAULT_ID_CARD_IMG_ID_B)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getRealnameInfo() throws Exception {
        // Initialize the database
        realnameInfoRepository.saveAndFlush(realnameInfo);

        // Get the realnameInfo
        restRealnameInfoMockMvc.perform(get("/api/realname-infos/{id}", realnameInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(realnameInfo.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.realName").value(DEFAULT_REAL_NAME.toString()))
            .andExpect(jsonPath("$.idCardNum").value(DEFAULT_ID_CARD_NUM.toString()))
            .andExpect(jsonPath("$.idCardImgIdA").value(DEFAULT_ID_CARD_IMG_ID_A))
            .andExpect(jsonPath("$.idCardImgIdB").value(DEFAULT_ID_CARD_IMG_ID_B))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRealnameInfo() throws Exception {
        // Get the realnameInfo
        restRealnameInfoMockMvc.perform(get("/api/realname-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRealnameInfo() throws Exception {
        // Initialize the database
        realnameInfoService.save(realnameInfo);

        int databaseSizeBeforeUpdate = realnameInfoRepository.findAll().size();

        // Update the realnameInfo
        RealnameInfo updatedRealnameInfo = realnameInfoRepository.findById(realnameInfo.getId()).get();
        // Disconnect from session so that the updates on updatedRealnameInfo are not directly saved in db
        em.detach(updatedRealnameInfo);
        updatedRealnameInfo
            .userId(UPDATED_USER_ID)
            .realName(UPDATED_REAL_NAME)
            .idCardNum(UPDATED_ID_CARD_NUM)
            .idCardImgIdA(UPDATED_ID_CARD_IMG_ID_A)
            .idCardImgIdB(UPDATED_ID_CARD_IMG_ID_B)
            .status(UPDATED_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restRealnameInfoMockMvc.perform(put("/api/realname-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRealnameInfo)))
            .andExpect(status().isOk());

        // Validate the RealnameInfo in the database
        List<RealnameInfo> realnameInfoList = realnameInfoRepository.findAll();
        assertThat(realnameInfoList).hasSize(databaseSizeBeforeUpdate);
        RealnameInfo testRealnameInfo = realnameInfoList.get(realnameInfoList.size() - 1);
        assertThat(testRealnameInfo.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testRealnameInfo.getRealName()).isEqualTo(UPDATED_REAL_NAME);
        assertThat(testRealnameInfo.getIdCardNum()).isEqualTo(UPDATED_ID_CARD_NUM);
        assertThat(testRealnameInfo.getIdCardImgIdA()).isEqualTo(UPDATED_ID_CARD_IMG_ID_A);
        assertThat(testRealnameInfo.getIdCardImgIdB()).isEqualTo(UPDATED_ID_CARD_IMG_ID_B);
        assertThat(testRealnameInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRealnameInfo.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testRealnameInfo.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingRealnameInfo() throws Exception {
        int databaseSizeBeforeUpdate = realnameInfoRepository.findAll().size();

        // Create the RealnameInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRealnameInfoMockMvc.perform(put("/api/realname-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(realnameInfo)))
            .andExpect(status().isBadRequest());

        // Validate the RealnameInfo in the database
        List<RealnameInfo> realnameInfoList = realnameInfoRepository.findAll();
        assertThat(realnameInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRealnameInfo() throws Exception {
        // Initialize the database
        realnameInfoService.save(realnameInfo);

        int databaseSizeBeforeDelete = realnameInfoRepository.findAll().size();

        // Get the realnameInfo
        restRealnameInfoMockMvc.perform(delete("/api/realname-infos/{id}", realnameInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RealnameInfo> realnameInfoList = realnameInfoRepository.findAll();
        assertThat(realnameInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RealnameInfo.class);
        RealnameInfo realnameInfo1 = new RealnameInfo();
        realnameInfo1.setId(1L);
        RealnameInfo realnameInfo2 = new RealnameInfo();
        realnameInfo2.setId(realnameInfo1.getId());
        assertThat(realnameInfo1).isEqualTo(realnameInfo2);
        realnameInfo2.setId(2L);
        assertThat(realnameInfo1).isNotEqualTo(realnameInfo2);
        realnameInfo1.setId(null);
        assertThat(realnameInfo1).isNotEqualTo(realnameInfo2);
    }
}
