package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.SiteInfo;
import com.datangliang.app.repository.SiteInfoRepository;
import com.datangliang.app.service.SiteInfoService;
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
 * Test class for the SiteInfoResource REST controller.
 *
 * @see SiteInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class SiteInfoResourceIntTest {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final Integer DEFAULT_SITE_TYPE = 1;
    private static final Integer UPDATED_SITE_TYPE = 2;

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_IMG_IDS = "AAAAAAAAAA";
    private static final String UPDATED_SITE_IMG_IDS = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private SiteInfoRepository siteInfoRepository;

    @Autowired
    private SiteInfoService siteInfoService;

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

    private MockMvc restSiteInfoMockMvc;

    private SiteInfo siteInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SiteInfoResource siteInfoResource = new SiteInfoResource(siteInfoService);
        this.restSiteInfoMockMvc = MockMvcBuilders.standaloneSetup(siteInfoResource)
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
    public static SiteInfo createEntity(EntityManager em) {
        SiteInfo siteInfo = new SiteInfo()
            .userId(DEFAULT_USER_ID)
            .siteType(DEFAULT_SITE_TYPE)
            .address(DEFAULT_ADDRESS)
            .siteImgIds(DEFAULT_SITE_IMG_IDS)
            .status(DEFAULT_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return siteInfo;
    }

    @Before
    public void initTest() {
        siteInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createSiteInfo() throws Exception {
        int databaseSizeBeforeCreate = siteInfoRepository.findAll().size();

        // Create the SiteInfo
        restSiteInfoMockMvc.perform(post("/api/site-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteInfo)))
            .andExpect(status().isCreated());

        // Validate the SiteInfo in the database
        List<SiteInfo> siteInfoList = siteInfoRepository.findAll();
        assertThat(siteInfoList).hasSize(databaseSizeBeforeCreate + 1);
        SiteInfo testSiteInfo = siteInfoList.get(siteInfoList.size() - 1);
        assertThat(testSiteInfo.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testSiteInfo.getSiteType()).isEqualTo(DEFAULT_SITE_TYPE);
        assertThat(testSiteInfo.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSiteInfo.getSiteImgIds()).isEqualTo(DEFAULT_SITE_IMG_IDS);
        assertThat(testSiteInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSiteInfo.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testSiteInfo.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createSiteInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = siteInfoRepository.findAll().size();

        // Create the SiteInfo with an existing ID
        siteInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSiteInfoMockMvc.perform(post("/api/site-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteInfo)))
            .andExpect(status().isBadRequest());

        // Validate the SiteInfo in the database
        List<SiteInfo> siteInfoList = siteInfoRepository.findAll();
        assertThat(siteInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSiteInfos() throws Exception {
        // Initialize the database
        siteInfoRepository.saveAndFlush(siteInfo);

        // Get all the siteInfoList
        restSiteInfoMockMvc.perform(get("/api/site-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(siteInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].siteType").value(hasItem(DEFAULT_SITE_TYPE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].siteImgIds").value(hasItem(DEFAULT_SITE_IMG_IDS.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getSiteInfo() throws Exception {
        // Initialize the database
        siteInfoRepository.saveAndFlush(siteInfo);

        // Get the siteInfo
        restSiteInfoMockMvc.perform(get("/api/site-infos/{id}", siteInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(siteInfo.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.siteType").value(DEFAULT_SITE_TYPE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.siteImgIds").value(DEFAULT_SITE_IMG_IDS.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSiteInfo() throws Exception {
        // Get the siteInfo
        restSiteInfoMockMvc.perform(get("/api/site-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSiteInfo() throws Exception {
        // Initialize the database
        siteInfoService.save(siteInfo);

        int databaseSizeBeforeUpdate = siteInfoRepository.findAll().size();

        // Update the siteInfo
        SiteInfo updatedSiteInfo = siteInfoRepository.findById(siteInfo.getId()).get();
        // Disconnect from session so that the updates on updatedSiteInfo are not directly saved in db
        em.detach(updatedSiteInfo);
        updatedSiteInfo
            .userId(UPDATED_USER_ID)
            .siteType(UPDATED_SITE_TYPE)
            .address(UPDATED_ADDRESS)
            .siteImgIds(UPDATED_SITE_IMG_IDS)
            .status(UPDATED_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restSiteInfoMockMvc.perform(put("/api/site-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSiteInfo)))
            .andExpect(status().isOk());

        // Validate the SiteInfo in the database
        List<SiteInfo> siteInfoList = siteInfoRepository.findAll();
        assertThat(siteInfoList).hasSize(databaseSizeBeforeUpdate);
        SiteInfo testSiteInfo = siteInfoList.get(siteInfoList.size() - 1);
        assertThat(testSiteInfo.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSiteInfo.getSiteType()).isEqualTo(UPDATED_SITE_TYPE);
        assertThat(testSiteInfo.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSiteInfo.getSiteImgIds()).isEqualTo(UPDATED_SITE_IMG_IDS);
        assertThat(testSiteInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSiteInfo.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testSiteInfo.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSiteInfo() throws Exception {
        int databaseSizeBeforeUpdate = siteInfoRepository.findAll().size();

        // Create the SiteInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSiteInfoMockMvc.perform(put("/api/site-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(siteInfo)))
            .andExpect(status().isBadRequest());

        // Validate the SiteInfo in the database
        List<SiteInfo> siteInfoList = siteInfoRepository.findAll();
        assertThat(siteInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSiteInfo() throws Exception {
        // Initialize the database
        siteInfoService.save(siteInfo);

        int databaseSizeBeforeDelete = siteInfoRepository.findAll().size();

        // Get the siteInfo
        restSiteInfoMockMvc.perform(delete("/api/site-infos/{id}", siteInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SiteInfo> siteInfoList = siteInfoRepository.findAll();
        assertThat(siteInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SiteInfo.class);
        SiteInfo siteInfo1 = new SiteInfo();
        siteInfo1.setId(1L);
        SiteInfo siteInfo2 = new SiteInfo();
        siteInfo2.setId(siteInfo1.getId());
        assertThat(siteInfo1).isEqualTo(siteInfo2);
        siteInfo2.setId(2L);
        assertThat(siteInfo1).isNotEqualTo(siteInfo2);
        siteInfo1.setId(null);
        assertThat(siteInfo1).isNotEqualTo(siteInfo2);
    }
}
