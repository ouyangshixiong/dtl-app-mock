package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.BankcardInfo;
import com.datangliang.app.repository.BankcardInfoRepository;
import com.datangliang.app.service.BankcardInfoService;
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
 * Test class for the BankcardInfoResource REST controller.
 *
 * @see BankcardInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class BankcardInfoResourceIntTest {

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

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BankcardInfoRepository bankcardInfoRepository;

    @Autowired
    private BankcardInfoService bankcardInfoService;

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

    private MockMvc restBankcardInfoMockMvc;

    private BankcardInfo bankcardInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BankcardInfoResource bankcardInfoResource = new BankcardInfoResource(bankcardInfoService);
        this.restBankcardInfoMockMvc = MockMvcBuilders.standaloneSetup(bankcardInfoResource)
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
    public static BankcardInfo createEntity(EntityManager em) {
        BankcardInfo bankcardInfo = new BankcardInfo()
            .userId(DEFAULT_USER_ID)
            .bankName(DEFAULT_BANK_NAME)
            .bankAccountName(DEFAULT_BANK_ACCOUNT_NAME)
            .bankCardNum(DEFAULT_BANK_CARD_NUM)
            .bankCardImgId(DEFAULT_BANK_CARD_IMG_ID)
            .status(DEFAULT_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return bankcardInfo;
    }

    @Before
    public void initTest() {
        bankcardInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createBankcardInfo() throws Exception {
        int databaseSizeBeforeCreate = bankcardInfoRepository.findAll().size();

        // Create the BankcardInfo
        restBankcardInfoMockMvc.perform(post("/api/bankcard-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankcardInfo)))
            .andExpect(status().isCreated());

        // Validate the BankcardInfo in the database
        List<BankcardInfo> bankcardInfoList = bankcardInfoRepository.findAll();
        assertThat(bankcardInfoList).hasSize(databaseSizeBeforeCreate + 1);
        BankcardInfo testBankcardInfo = bankcardInfoList.get(bankcardInfoList.size() - 1);
        assertThat(testBankcardInfo.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBankcardInfo.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBankcardInfo.getBankAccountName()).isEqualTo(DEFAULT_BANK_ACCOUNT_NAME);
        assertThat(testBankcardInfo.getBankCardNum()).isEqualTo(DEFAULT_BANK_CARD_NUM);
        assertThat(testBankcardInfo.getBankCardImgId()).isEqualTo(DEFAULT_BANK_CARD_IMG_ID);
        assertThat(testBankcardInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBankcardInfo.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testBankcardInfo.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createBankcardInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankcardInfoRepository.findAll().size();

        // Create the BankcardInfo with an existing ID
        bankcardInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankcardInfoMockMvc.perform(post("/api/bankcard-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankcardInfo)))
            .andExpect(status().isBadRequest());

        // Validate the BankcardInfo in the database
        List<BankcardInfo> bankcardInfoList = bankcardInfoRepository.findAll();
        assertThat(bankcardInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBankcardInfos() throws Exception {
        // Initialize the database
        bankcardInfoRepository.saveAndFlush(bankcardInfo);

        // Get all the bankcardInfoList
        restBankcardInfoMockMvc.perform(get("/api/bankcard-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankcardInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME.toString())))
            .andExpect(jsonPath("$.[*].bankAccountName").value(hasItem(DEFAULT_BANK_ACCOUNT_NAME.toString())))
            .andExpect(jsonPath("$.[*].bankCardNum").value(hasItem(DEFAULT_BANK_CARD_NUM.toString())))
            .andExpect(jsonPath("$.[*].bankCardImgId").value(hasItem(DEFAULT_BANK_CARD_IMG_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getBankcardInfo() throws Exception {
        // Initialize the database
        bankcardInfoRepository.saveAndFlush(bankcardInfo);

        // Get the bankcardInfo
        restBankcardInfoMockMvc.perform(get("/api/bankcard-infos/{id}", bankcardInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bankcardInfo.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME.toString()))
            .andExpect(jsonPath("$.bankAccountName").value(DEFAULT_BANK_ACCOUNT_NAME.toString()))
            .andExpect(jsonPath("$.bankCardNum").value(DEFAULT_BANK_CARD_NUM.toString()))
            .andExpect(jsonPath("$.bankCardImgId").value(DEFAULT_BANK_CARD_IMG_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBankcardInfo() throws Exception {
        // Get the bankcardInfo
        restBankcardInfoMockMvc.perform(get("/api/bankcard-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankcardInfo() throws Exception {
        // Initialize the database
        bankcardInfoService.save(bankcardInfo);

        int databaseSizeBeforeUpdate = bankcardInfoRepository.findAll().size();

        // Update the bankcardInfo
        BankcardInfo updatedBankcardInfo = bankcardInfoRepository.findById(bankcardInfo.getId()).get();
        // Disconnect from session so that the updates on updatedBankcardInfo are not directly saved in db
        em.detach(updatedBankcardInfo);
        updatedBankcardInfo
            .userId(UPDATED_USER_ID)
            .bankName(UPDATED_BANK_NAME)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankCardNum(UPDATED_BANK_CARD_NUM)
            .bankCardImgId(UPDATED_BANK_CARD_IMG_ID)
            .status(UPDATED_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restBankcardInfoMockMvc.perform(put("/api/bankcard-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBankcardInfo)))
            .andExpect(status().isOk());

        // Validate the BankcardInfo in the database
        List<BankcardInfo> bankcardInfoList = bankcardInfoRepository.findAll();
        assertThat(bankcardInfoList).hasSize(databaseSizeBeforeUpdate);
        BankcardInfo testBankcardInfo = bankcardInfoList.get(bankcardInfoList.size() - 1);
        assertThat(testBankcardInfo.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBankcardInfo.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBankcardInfo.getBankAccountName()).isEqualTo(UPDATED_BANK_ACCOUNT_NAME);
        assertThat(testBankcardInfo.getBankCardNum()).isEqualTo(UPDATED_BANK_CARD_NUM);
        assertThat(testBankcardInfo.getBankCardImgId()).isEqualTo(UPDATED_BANK_CARD_IMG_ID);
        assertThat(testBankcardInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBankcardInfo.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testBankcardInfo.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingBankcardInfo() throws Exception {
        int databaseSizeBeforeUpdate = bankcardInfoRepository.findAll().size();

        // Create the BankcardInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankcardInfoMockMvc.perform(put("/api/bankcard-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bankcardInfo)))
            .andExpect(status().isBadRequest());

        // Validate the BankcardInfo in the database
        List<BankcardInfo> bankcardInfoList = bankcardInfoRepository.findAll();
        assertThat(bankcardInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBankcardInfo() throws Exception {
        // Initialize the database
        bankcardInfoService.save(bankcardInfo);

        int databaseSizeBeforeDelete = bankcardInfoRepository.findAll().size();

        // Get the bankcardInfo
        restBankcardInfoMockMvc.perform(delete("/api/bankcard-infos/{id}", bankcardInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BankcardInfo> bankcardInfoList = bankcardInfoRepository.findAll();
        assertThat(bankcardInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankcardInfo.class);
        BankcardInfo bankcardInfo1 = new BankcardInfo();
        bankcardInfo1.setId(1L);
        BankcardInfo bankcardInfo2 = new BankcardInfo();
        bankcardInfo2.setId(bankcardInfo1.getId());
        assertThat(bankcardInfo1).isEqualTo(bankcardInfo2);
        bankcardInfo2.setId(2L);
        assertThat(bankcardInfo1).isNotEqualTo(bankcardInfo2);
        bankcardInfo1.setId(null);
        assertThat(bankcardInfo1).isNotEqualTo(bankcardInfo2);
    }
}
