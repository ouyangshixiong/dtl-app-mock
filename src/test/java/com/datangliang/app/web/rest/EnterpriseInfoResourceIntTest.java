package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.EnterpriseInfo;
import com.datangliang.app.repository.EnterpriseInfoRepository;
import com.datangliang.app.service.EnterpriseInfoService;
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
 * Test class for the EnterpriseInfoResource REST controller.
 *
 * @see EnterpriseInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class EnterpriseInfoResourceIntTest {

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

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private EnterpriseInfoRepository enterpriseInfoRepository;

    @Autowired
    private EnterpriseInfoService enterpriseInfoService;

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

    private MockMvc restEnterpriseInfoMockMvc;

    private EnterpriseInfo enterpriseInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnterpriseInfoResource enterpriseInfoResource = new EnterpriseInfoResource(enterpriseInfoService);
        this.restEnterpriseInfoMockMvc = MockMvcBuilders.standaloneSetup(enterpriseInfoResource)
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
    public static EnterpriseInfo createEntity(EntityManager em) {
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo()
            .userId(DEFAULT_USER_ID)
            .name(DEFAULT_NAME)
            .legalPersonName(DEFAULT_LEGAL_PERSON_NAME)
            .legalPersonIdCardNum(DEFAULT_LEGAL_PERSON_ID_CARD_NUM)
            .legalPersonMobile(DEFAULT_LEGAL_PERSON_MOBILE)
            .enterpriseTel(DEFAULT_ENTERPRISE_TEL)
            .businessLicenseImgId(DEFAULT_BUSINESS_LICENSE_IMG_ID)
            .status(DEFAULT_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return enterpriseInfo;
    }

    @Before
    public void initTest() {
        enterpriseInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnterpriseInfo() throws Exception {
        int databaseSizeBeforeCreate = enterpriseInfoRepository.findAll().size();

        // Create the EnterpriseInfo
        restEnterpriseInfoMockMvc.perform(post("/api/enterprise-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseInfo)))
            .andExpect(status().isCreated());

        // Validate the EnterpriseInfo in the database
        List<EnterpriseInfo> enterpriseInfoList = enterpriseInfoRepository.findAll();
        assertThat(enterpriseInfoList).hasSize(databaseSizeBeforeCreate + 1);
        EnterpriseInfo testEnterpriseInfo = enterpriseInfoList.get(enterpriseInfoList.size() - 1);
        assertThat(testEnterpriseInfo.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testEnterpriseInfo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEnterpriseInfo.getLegalPersonName()).isEqualTo(DEFAULT_LEGAL_PERSON_NAME);
        assertThat(testEnterpriseInfo.getLegalPersonIdCardNum()).isEqualTo(DEFAULT_LEGAL_PERSON_ID_CARD_NUM);
        assertThat(testEnterpriseInfo.getLegalPersonMobile()).isEqualTo(DEFAULT_LEGAL_PERSON_MOBILE);
        assertThat(testEnterpriseInfo.getEnterpriseTel()).isEqualTo(DEFAULT_ENTERPRISE_TEL);
        assertThat(testEnterpriseInfo.getBusinessLicenseImgId()).isEqualTo(DEFAULT_BUSINESS_LICENSE_IMG_ID);
        assertThat(testEnterpriseInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEnterpriseInfo.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testEnterpriseInfo.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createEnterpriseInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enterpriseInfoRepository.findAll().size();

        // Create the EnterpriseInfo with an existing ID
        enterpriseInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnterpriseInfoMockMvc.perform(post("/api/enterprise-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseInfo)))
            .andExpect(status().isBadRequest());

        // Validate the EnterpriseInfo in the database
        List<EnterpriseInfo> enterpriseInfoList = enterpriseInfoRepository.findAll();
        assertThat(enterpriseInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEnterpriseInfos() throws Exception {
        // Initialize the database
        enterpriseInfoRepository.saveAndFlush(enterpriseInfo);

        // Get all the enterpriseInfoList
        restEnterpriseInfoMockMvc.perform(get("/api/enterprise-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enterpriseInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].legalPersonName").value(hasItem(DEFAULT_LEGAL_PERSON_NAME.toString())))
            .andExpect(jsonPath("$.[*].legalPersonIdCardNum").value(hasItem(DEFAULT_LEGAL_PERSON_ID_CARD_NUM.toString())))
            .andExpect(jsonPath("$.[*].legalPersonMobile").value(hasItem(DEFAULT_LEGAL_PERSON_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].enterpriseTel").value(hasItem(DEFAULT_ENTERPRISE_TEL.toString())))
            .andExpect(jsonPath("$.[*].businessLicenseImgId").value(hasItem(DEFAULT_BUSINESS_LICENSE_IMG_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getEnterpriseInfo() throws Exception {
        // Initialize the database
        enterpriseInfoRepository.saveAndFlush(enterpriseInfo);

        // Get the enterpriseInfo
        restEnterpriseInfoMockMvc.perform(get("/api/enterprise-infos/{id}", enterpriseInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enterpriseInfo.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.legalPersonName").value(DEFAULT_LEGAL_PERSON_NAME.toString()))
            .andExpect(jsonPath("$.legalPersonIdCardNum").value(DEFAULT_LEGAL_PERSON_ID_CARD_NUM.toString()))
            .andExpect(jsonPath("$.legalPersonMobile").value(DEFAULT_LEGAL_PERSON_MOBILE.toString()))
            .andExpect(jsonPath("$.enterpriseTel").value(DEFAULT_ENTERPRISE_TEL.toString()))
            .andExpect(jsonPath("$.businessLicenseImgId").value(DEFAULT_BUSINESS_LICENSE_IMG_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnterpriseInfo() throws Exception {
        // Get the enterpriseInfo
        restEnterpriseInfoMockMvc.perform(get("/api/enterprise-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnterpriseInfo() throws Exception {
        // Initialize the database
        enterpriseInfoService.save(enterpriseInfo);

        int databaseSizeBeforeUpdate = enterpriseInfoRepository.findAll().size();

        // Update the enterpriseInfo
        EnterpriseInfo updatedEnterpriseInfo = enterpriseInfoRepository.findById(enterpriseInfo.getId()).get();
        // Disconnect from session so that the updates on updatedEnterpriseInfo are not directly saved in db
        em.detach(updatedEnterpriseInfo);
        updatedEnterpriseInfo
            .userId(UPDATED_USER_ID)
            .name(UPDATED_NAME)
            .legalPersonName(UPDATED_LEGAL_PERSON_NAME)
            .legalPersonIdCardNum(UPDATED_LEGAL_PERSON_ID_CARD_NUM)
            .legalPersonMobile(UPDATED_LEGAL_PERSON_MOBILE)
            .enterpriseTel(UPDATED_ENTERPRISE_TEL)
            .businessLicenseImgId(UPDATED_BUSINESS_LICENSE_IMG_ID)
            .status(UPDATED_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restEnterpriseInfoMockMvc.perform(put("/api/enterprise-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnterpriseInfo)))
            .andExpect(status().isOk());

        // Validate the EnterpriseInfo in the database
        List<EnterpriseInfo> enterpriseInfoList = enterpriseInfoRepository.findAll();
        assertThat(enterpriseInfoList).hasSize(databaseSizeBeforeUpdate);
        EnterpriseInfo testEnterpriseInfo = enterpriseInfoList.get(enterpriseInfoList.size() - 1);
        assertThat(testEnterpriseInfo.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testEnterpriseInfo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEnterpriseInfo.getLegalPersonName()).isEqualTo(UPDATED_LEGAL_PERSON_NAME);
        assertThat(testEnterpriseInfo.getLegalPersonIdCardNum()).isEqualTo(UPDATED_LEGAL_PERSON_ID_CARD_NUM);
        assertThat(testEnterpriseInfo.getLegalPersonMobile()).isEqualTo(UPDATED_LEGAL_PERSON_MOBILE);
        assertThat(testEnterpriseInfo.getEnterpriseTel()).isEqualTo(UPDATED_ENTERPRISE_TEL);
        assertThat(testEnterpriseInfo.getBusinessLicenseImgId()).isEqualTo(UPDATED_BUSINESS_LICENSE_IMG_ID);
        assertThat(testEnterpriseInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEnterpriseInfo.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testEnterpriseInfo.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingEnterpriseInfo() throws Exception {
        int databaseSizeBeforeUpdate = enterpriseInfoRepository.findAll().size();

        // Create the EnterpriseInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnterpriseInfoMockMvc.perform(put("/api/enterprise-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enterpriseInfo)))
            .andExpect(status().isBadRequest());

        // Validate the EnterpriseInfo in the database
        List<EnterpriseInfo> enterpriseInfoList = enterpriseInfoRepository.findAll();
        assertThat(enterpriseInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnterpriseInfo() throws Exception {
        // Initialize the database
        enterpriseInfoService.save(enterpriseInfo);

        int databaseSizeBeforeDelete = enterpriseInfoRepository.findAll().size();

        // Get the enterpriseInfo
        restEnterpriseInfoMockMvc.perform(delete("/api/enterprise-infos/{id}", enterpriseInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnterpriseInfo> enterpriseInfoList = enterpriseInfoRepository.findAll();
        assertThat(enterpriseInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnterpriseInfo.class);
        EnterpriseInfo enterpriseInfo1 = new EnterpriseInfo();
        enterpriseInfo1.setId(1L);
        EnterpriseInfo enterpriseInfo2 = new EnterpriseInfo();
        enterpriseInfo2.setId(enterpriseInfo1.getId());
        assertThat(enterpriseInfo1).isEqualTo(enterpriseInfo2);
        enterpriseInfo2.setId(2L);
        assertThat(enterpriseInfo1).isNotEqualTo(enterpriseInfo2);
        enterpriseInfo1.setId(null);
        assertThat(enterpriseInfo1).isNotEqualTo(enterpriseInfo2);
    }
}
