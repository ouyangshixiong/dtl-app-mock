package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.UserLevel;
import com.datangliang.app.repository.UserLevelRepository;
import com.datangliang.app.service.UserLevelService;
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
 * Test class for the UserLevelResource REST controller.
 *
 * @see UserLevelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class UserLevelResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_INTEGRAL_RULE = 1;
    private static final Integer UPDATED_INTEGRAL_RULE = 2;

    private static final Integer DEFAULT_USER_IDENTITY = 1;
    private static final Integer UPDATED_USER_IDENTITY = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private UserLevelRepository userLevelRepository;

    @Autowired
    private UserLevelService userLevelService;

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

    private MockMvc restUserLevelMockMvc;

    private UserLevel userLevel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserLevelResource userLevelResource = new UserLevelResource(userLevelService);
        this.restUserLevelMockMvc = MockMvcBuilders.standaloneSetup(userLevelResource)
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
    public static UserLevel createEntity(EntityManager em) {
        UserLevel userLevel = new UserLevel()
            .name(DEFAULT_NAME)
            .integralRule(DEFAULT_INTEGRAL_RULE)
            .userIdentity(DEFAULT_USER_IDENTITY)
            .status(DEFAULT_STATUS)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return userLevel;
    }

    @Before
    public void initTest() {
        userLevel = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserLevel() throws Exception {
        int databaseSizeBeforeCreate = userLevelRepository.findAll().size();

        // Create the UserLevel
        restUserLevelMockMvc.perform(post("/api/user-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userLevel)))
            .andExpect(status().isCreated());

        // Validate the UserLevel in the database
        List<UserLevel> userLevelList = userLevelRepository.findAll();
        assertThat(userLevelList).hasSize(databaseSizeBeforeCreate + 1);
        UserLevel testUserLevel = userLevelList.get(userLevelList.size() - 1);
        assertThat(testUserLevel.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUserLevel.getIntegralRule()).isEqualTo(DEFAULT_INTEGRAL_RULE);
        assertThat(testUserLevel.getUserIdentity()).isEqualTo(DEFAULT_USER_IDENTITY);
        assertThat(testUserLevel.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUserLevel.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testUserLevel.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createUserLevelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userLevelRepository.findAll().size();

        // Create the UserLevel with an existing ID
        userLevel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserLevelMockMvc.perform(post("/api/user-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userLevel)))
            .andExpect(status().isBadRequest());

        // Validate the UserLevel in the database
        List<UserLevel> userLevelList = userLevelRepository.findAll();
        assertThat(userLevelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserLevels() throws Exception {
        // Initialize the database
        userLevelRepository.saveAndFlush(userLevel);

        // Get all the userLevelList
        restUserLevelMockMvc.perform(get("/api/user-levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].integralRule").value(hasItem(DEFAULT_INTEGRAL_RULE)))
            .andExpect(jsonPath("$.[*].userIdentity").value(hasItem(DEFAULT_USER_IDENTITY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getUserLevel() throws Exception {
        // Initialize the database
        userLevelRepository.saveAndFlush(userLevel);

        // Get the userLevel
        restUserLevelMockMvc.perform(get("/api/user-levels/{id}", userLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userLevel.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.integralRule").value(DEFAULT_INTEGRAL_RULE))
            .andExpect(jsonPath("$.userIdentity").value(DEFAULT_USER_IDENTITY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserLevel() throws Exception {
        // Get the userLevel
        restUserLevelMockMvc.perform(get("/api/user-levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserLevel() throws Exception {
        // Initialize the database
        userLevelService.save(userLevel);

        int databaseSizeBeforeUpdate = userLevelRepository.findAll().size();

        // Update the userLevel
        UserLevel updatedUserLevel = userLevelRepository.findById(userLevel.getId()).get();
        // Disconnect from session so that the updates on updatedUserLevel are not directly saved in db
        em.detach(updatedUserLevel);
        updatedUserLevel
            .name(UPDATED_NAME)
            .integralRule(UPDATED_INTEGRAL_RULE)
            .userIdentity(UPDATED_USER_IDENTITY)
            .status(UPDATED_STATUS)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restUserLevelMockMvc.perform(put("/api/user-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserLevel)))
            .andExpect(status().isOk());

        // Validate the UserLevel in the database
        List<UserLevel> userLevelList = userLevelRepository.findAll();
        assertThat(userLevelList).hasSize(databaseSizeBeforeUpdate);
        UserLevel testUserLevel = userLevelList.get(userLevelList.size() - 1);
        assertThat(testUserLevel.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUserLevel.getIntegralRule()).isEqualTo(UPDATED_INTEGRAL_RULE);
        assertThat(testUserLevel.getUserIdentity()).isEqualTo(UPDATED_USER_IDENTITY);
        assertThat(testUserLevel.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUserLevel.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testUserLevel.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingUserLevel() throws Exception {
        int databaseSizeBeforeUpdate = userLevelRepository.findAll().size();

        // Create the UserLevel

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserLevelMockMvc.perform(put("/api/user-levels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userLevel)))
            .andExpect(status().isBadRequest());

        // Validate the UserLevel in the database
        List<UserLevel> userLevelList = userLevelRepository.findAll();
        assertThat(userLevelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserLevel() throws Exception {
        // Initialize the database
        userLevelService.save(userLevel);

        int databaseSizeBeforeDelete = userLevelRepository.findAll().size();

        // Get the userLevel
        restUserLevelMockMvc.perform(delete("/api/user-levels/{id}", userLevel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserLevel> userLevelList = userLevelRepository.findAll();
        assertThat(userLevelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserLevel.class);
        UserLevel userLevel1 = new UserLevel();
        userLevel1.setId(1L);
        UserLevel userLevel2 = new UserLevel();
        userLevel2.setId(userLevel1.getId());
        assertThat(userLevel1).isEqualTo(userLevel2);
        userLevel2.setId(2L);
        assertThat(userLevel1).isNotEqualTo(userLevel2);
        userLevel1.setId(null);
        assertThat(userLevel1).isNotEqualTo(userLevel2);
    }
}
