package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.Isp;
import com.datangliang.app.repository.IspRepository;
import com.datangliang.app.service.IspService;
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
 * Test class for the IspResource REST controller.
 *
 * @see IspResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class IspResourceIntTest {

    private static final String DEFAULT_TXN_ID = "AAAAAAAAAA";
    private static final String UPDATED_TXN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPLATE = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private IspRepository ispRepository;

    @Autowired
    private IspService ispService;

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

    private MockMvc restIspMockMvc;

    private Isp isp;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IspResource ispResource = new IspResource(ispService);
        this.restIspMockMvc = MockMvcBuilders.standaloneSetup(ispResource)
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
    public static Isp createEntity(EntityManager em) {
        Isp isp = new Isp()
            .txnId(DEFAULT_TXN_ID)
            .mobile(DEFAULT_MOBILE)
            .template(DEFAULT_TEMPLATE)
            .status(DEFAULT_STATUS)
            .createTime(DEFAULT_CREATE_TIME);
        return isp;
    }

    @Before
    public void initTest() {
        isp = createEntity(em);
    }

    @Test
    @Transactional
    public void createIsp() throws Exception {
        int databaseSizeBeforeCreate = ispRepository.findAll().size();

        // Create the Isp
        restIspMockMvc.perform(post("/api/isps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(isp)))
            .andExpect(status().isCreated());

        // Validate the Isp in the database
        List<Isp> ispList = ispRepository.findAll();
        assertThat(ispList).hasSize(databaseSizeBeforeCreate + 1);
        Isp testIsp = ispList.get(ispList.size() - 1);
        assertThat(testIsp.getTxnId()).isEqualTo(DEFAULT_TXN_ID);
        assertThat(testIsp.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testIsp.getTemplate()).isEqualTo(DEFAULT_TEMPLATE);
        assertThat(testIsp.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testIsp.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
    }

    @Test
    @Transactional
    public void createIspWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ispRepository.findAll().size();

        // Create the Isp with an existing ID
        isp.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIspMockMvc.perform(post("/api/isps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(isp)))
            .andExpect(status().isBadRequest());

        // Validate the Isp in the database
        List<Isp> ispList = ispRepository.findAll();
        assertThat(ispList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllIsps() throws Exception {
        // Initialize the database
        ispRepository.saveAndFlush(isp);

        // Get all the ispList
        restIspMockMvc.perform(get("/api/isps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(isp.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnId").value(hasItem(DEFAULT_TXN_ID.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].template").value(hasItem(DEFAULT_TEMPLATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getIsp() throws Exception {
        // Initialize the database
        ispRepository.saveAndFlush(isp);

        // Get the isp
        restIspMockMvc.perform(get("/api/isps/{id}", isp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(isp.getId().intValue()))
            .andExpect(jsonPath("$.txnId").value(DEFAULT_TXN_ID.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.template").value(DEFAULT_TEMPLATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIsp() throws Exception {
        // Get the isp
        restIspMockMvc.perform(get("/api/isps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIsp() throws Exception {
        // Initialize the database
        ispService.save(isp);

        int databaseSizeBeforeUpdate = ispRepository.findAll().size();

        // Update the isp
        Isp updatedIsp = ispRepository.findById(isp.getId()).get();
        // Disconnect from session so that the updates on updatedIsp are not directly saved in db
        em.detach(updatedIsp);
        updatedIsp
            .txnId(UPDATED_TXN_ID)
            .mobile(UPDATED_MOBILE)
            .template(UPDATED_TEMPLATE)
            .status(UPDATED_STATUS)
            .createTime(UPDATED_CREATE_TIME);

        restIspMockMvc.perform(put("/api/isps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIsp)))
            .andExpect(status().isOk());

        // Validate the Isp in the database
        List<Isp> ispList = ispRepository.findAll();
        assertThat(ispList).hasSize(databaseSizeBeforeUpdate);
        Isp testIsp = ispList.get(ispList.size() - 1);
        assertThat(testIsp.getTxnId()).isEqualTo(UPDATED_TXN_ID);
        assertThat(testIsp.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testIsp.getTemplate()).isEqualTo(UPDATED_TEMPLATE);
        assertThat(testIsp.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testIsp.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingIsp() throws Exception {
        int databaseSizeBeforeUpdate = ispRepository.findAll().size();

        // Create the Isp

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIspMockMvc.perform(put("/api/isps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(isp)))
            .andExpect(status().isBadRequest());

        // Validate the Isp in the database
        List<Isp> ispList = ispRepository.findAll();
        assertThat(ispList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIsp() throws Exception {
        // Initialize the database
        ispService.save(isp);

        int databaseSizeBeforeDelete = ispRepository.findAll().size();

        // Get the isp
        restIspMockMvc.perform(delete("/api/isps/{id}", isp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Isp> ispList = ispRepository.findAll();
        assertThat(ispList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Isp.class);
        Isp isp1 = new Isp();
        isp1.setId(1L);
        Isp isp2 = new Isp();
        isp2.setId(isp1.getId());
        assertThat(isp1).isEqualTo(isp2);
        isp2.setId(2L);
        assertThat(isp1).isNotEqualTo(isp2);
        isp1.setId(null);
        assertThat(isp1).isNotEqualTo(isp2);
    }
}
