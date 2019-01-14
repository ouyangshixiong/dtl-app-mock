package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.DTLUser;
import com.datangliang.app.repository.DTLUserRepository;
import com.datangliang.app.service.DTLUserService;
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
 * Test class for the DTLUserResource REST controller.
 *
 * @see DTLUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class DTLUserResourceIntTest {

    private static final Integer DEFAULT_USER_ROLE_ID = 1;
    private static final Integer UPDATED_USER_ROLE_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_USER_TYPE = 1;
    private static final Integer UPDATED_USER_TYPE = 2;

    private static final Integer DEFAULT_USER_IDENTITY = 1;
    private static final Integer UPDATED_USER_IDENTITY = 2;

    private static final Integer DEFAULT_SOURCE = 1;
    private static final Integer UPDATED_SOURCE = 2;

    private static final String DEFAULT_IP_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDR = "BBBBBBBBBB";

    private static final Instant DEFAULT_REG_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REG_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_LOGIN_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_LOGIN_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DTLUserRepository dTLUserRepository;

    @Autowired
    private DTLUserService dTLUserService;

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

    private MockMvc restDTLUserMockMvc;

    private DTLUser dTLUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DTLUserResource dTLUserResource = new DTLUserResource(dTLUserService);
        this.restDTLUserMockMvc = MockMvcBuilders.standaloneSetup(dTLUserResource)
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
    public static DTLUser createEntity(EntityManager em) {
        DTLUser dTLUser = new DTLUser()
            .userRoleId(DEFAULT_USER_ROLE_ID)
            .name(DEFAULT_NAME)
            .account(DEFAULT_ACCOUNT)
            .password(DEFAULT_PASSWORD)
            .status(DEFAULT_STATUS)
            .userType(DEFAULT_USER_TYPE)
            .userIdentity(DEFAULT_USER_IDENTITY)
            .source(DEFAULT_SOURCE)
            .ipAddr(DEFAULT_IP_ADDR)
            .regTime(DEFAULT_REG_TIME)
            .lastLoginTime(DEFAULT_LAST_LOGIN_TIME);
        return dTLUser;
    }

    @Before
    public void initTest() {
        dTLUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createDTLUser() throws Exception {
        int databaseSizeBeforeCreate = dTLUserRepository.findAll().size();

        // Create the DTLUser
        restDTLUserMockMvc.perform(post("/api/dtl-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dTLUser)))
            .andExpect(status().isCreated());

        // Validate the DTLUser in the database
        List<DTLUser> dTLUserList = dTLUserRepository.findAll();
        assertThat(dTLUserList).hasSize(databaseSizeBeforeCreate + 1);
        DTLUser testDTLUser = dTLUserList.get(dTLUserList.size() - 1);
        assertThat(testDTLUser.getUserRoleId()).isEqualTo(DEFAULT_USER_ROLE_ID);
        assertThat(testDTLUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDTLUser.getAccount()).isEqualTo(DEFAULT_ACCOUNT);
        assertThat(testDTLUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testDTLUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDTLUser.getUserType()).isEqualTo(DEFAULT_USER_TYPE);
        assertThat(testDTLUser.getUserIdentity()).isEqualTo(DEFAULT_USER_IDENTITY);
        assertThat(testDTLUser.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testDTLUser.getIpAddr()).isEqualTo(DEFAULT_IP_ADDR);
        assertThat(testDTLUser.getRegTime()).isEqualTo(DEFAULT_REG_TIME);
        assertThat(testDTLUser.getLastLoginTime()).isEqualTo(DEFAULT_LAST_LOGIN_TIME);
    }

    @Test
    @Transactional
    public void createDTLUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dTLUserRepository.findAll().size();

        // Create the DTLUser with an existing ID
        dTLUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDTLUserMockMvc.perform(post("/api/dtl-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dTLUser)))
            .andExpect(status().isBadRequest());

        // Validate the DTLUser in the database
        List<DTLUser> dTLUserList = dTLUserRepository.findAll();
        assertThat(dTLUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDTLUsers() throws Exception {
        // Initialize the database
        dTLUserRepository.saveAndFlush(dTLUser);

        // Get all the dTLUserList
        restDTLUserMockMvc.perform(get("/api/dtl-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dTLUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userRoleId").value(hasItem(DEFAULT_USER_ROLE_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].account").value(hasItem(DEFAULT_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].userType").value(hasItem(DEFAULT_USER_TYPE)))
            .andExpect(jsonPath("$.[*].userIdentity").value(hasItem(DEFAULT_USER_IDENTITY)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].ipAddr").value(hasItem(DEFAULT_IP_ADDR.toString())))
            .andExpect(jsonPath("$.[*].regTime").value(hasItem(DEFAULT_REG_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastLoginTime").value(hasItem(DEFAULT_LAST_LOGIN_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getDTLUser() throws Exception {
        // Initialize the database
        dTLUserRepository.saveAndFlush(dTLUser);

        // Get the dTLUser
        restDTLUserMockMvc.perform(get("/api/dtl-users/{id}", dTLUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dTLUser.getId().intValue()))
            .andExpect(jsonPath("$.userRoleId").value(DEFAULT_USER_ROLE_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.account").value(DEFAULT_ACCOUNT.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.userType").value(DEFAULT_USER_TYPE))
            .andExpect(jsonPath("$.userIdentity").value(DEFAULT_USER_IDENTITY))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE))
            .andExpect(jsonPath("$.ipAddr").value(DEFAULT_IP_ADDR.toString()))
            .andExpect(jsonPath("$.regTime").value(DEFAULT_REG_TIME.toString()))
            .andExpect(jsonPath("$.lastLoginTime").value(DEFAULT_LAST_LOGIN_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDTLUser() throws Exception {
        // Get the dTLUser
        restDTLUserMockMvc.perform(get("/api/dtl-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDTLUser() throws Exception {
        // Initialize the database
        dTLUserService.save(dTLUser);

        int databaseSizeBeforeUpdate = dTLUserRepository.findAll().size();

        // Update the dTLUser
        DTLUser updatedDTLUser = dTLUserRepository.findById(dTLUser.getId()).get();
        // Disconnect from session so that the updates on updatedDTLUser are not directly saved in db
        em.detach(updatedDTLUser);
        updatedDTLUser
            .userRoleId(UPDATED_USER_ROLE_ID)
            .name(UPDATED_NAME)
            .account(UPDATED_ACCOUNT)
            .password(UPDATED_PASSWORD)
            .status(UPDATED_STATUS)
            .userType(UPDATED_USER_TYPE)
            .userIdentity(UPDATED_USER_IDENTITY)
            .source(UPDATED_SOURCE)
            .ipAddr(UPDATED_IP_ADDR)
            .regTime(UPDATED_REG_TIME)
            .lastLoginTime(UPDATED_LAST_LOGIN_TIME);

        restDTLUserMockMvc.perform(put("/api/dtl-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDTLUser)))
            .andExpect(status().isOk());

        // Validate the DTLUser in the database
        List<DTLUser> dTLUserList = dTLUserRepository.findAll();
        assertThat(dTLUserList).hasSize(databaseSizeBeforeUpdate);
        DTLUser testDTLUser = dTLUserList.get(dTLUserList.size() - 1);
        assertThat(testDTLUser.getUserRoleId()).isEqualTo(UPDATED_USER_ROLE_ID);
        assertThat(testDTLUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDTLUser.getAccount()).isEqualTo(UPDATED_ACCOUNT);
        assertThat(testDTLUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testDTLUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDTLUser.getUserType()).isEqualTo(UPDATED_USER_TYPE);
        assertThat(testDTLUser.getUserIdentity()).isEqualTo(UPDATED_USER_IDENTITY);
        assertThat(testDTLUser.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testDTLUser.getIpAddr()).isEqualTo(UPDATED_IP_ADDR);
        assertThat(testDTLUser.getRegTime()).isEqualTo(UPDATED_REG_TIME);
        assertThat(testDTLUser.getLastLoginTime()).isEqualTo(UPDATED_LAST_LOGIN_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingDTLUser() throws Exception {
        int databaseSizeBeforeUpdate = dTLUserRepository.findAll().size();

        // Create the DTLUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDTLUserMockMvc.perform(put("/api/dtl-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dTLUser)))
            .andExpect(status().isBadRequest());

        // Validate the DTLUser in the database
        List<DTLUser> dTLUserList = dTLUserRepository.findAll();
        assertThat(dTLUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDTLUser() throws Exception {
        // Initialize the database
        dTLUserService.save(dTLUser);

        int databaseSizeBeforeDelete = dTLUserRepository.findAll().size();

        // Get the dTLUser
        restDTLUserMockMvc.perform(delete("/api/dtl-users/{id}", dTLUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DTLUser> dTLUserList = dTLUserRepository.findAll();
        assertThat(dTLUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DTLUser.class);
        DTLUser dTLUser1 = new DTLUser();
        dTLUser1.setId(1L);
        DTLUser dTLUser2 = new DTLUser();
        dTLUser2.setId(dTLUser1.getId());
        assertThat(dTLUser1).isEqualTo(dTLUser2);
        dTLUser2.setId(2L);
        assertThat(dTLUser1).isNotEqualTo(dTLUser2);
        dTLUser1.setId(null);
        assertThat(dTLUser1).isNotEqualTo(dTLUser2);
    }
}
