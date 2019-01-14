package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.StoreUser;
import com.datangliang.app.repository.StoreUserRepository;
import com.datangliang.app.service.StoreUserService;
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
import java.util.List;


import static com.datangliang.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StoreUserResource REST controller.
 *
 * @see StoreUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class StoreUserResourceIntTest {

    private static final Integer DEFAULT_STORE_ID = 1;
    private static final Integer UPDATED_STORE_ID = 2;

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    @Autowired
    private StoreUserRepository storeUserRepository;

    @Autowired
    private StoreUserService storeUserService;

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

    private MockMvc restStoreUserMockMvc;

    private StoreUser storeUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StoreUserResource storeUserResource = new StoreUserResource(storeUserService);
        this.restStoreUserMockMvc = MockMvcBuilders.standaloneSetup(storeUserResource)
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
    public static StoreUser createEntity(EntityManager em) {
        StoreUser storeUser = new StoreUser()
            .storeId(DEFAULT_STORE_ID)
            .userId(DEFAULT_USER_ID);
        return storeUser;
    }

    @Before
    public void initTest() {
        storeUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createStoreUser() throws Exception {
        int databaseSizeBeforeCreate = storeUserRepository.findAll().size();

        // Create the StoreUser
        restStoreUserMockMvc.perform(post("/api/store-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeUser)))
            .andExpect(status().isCreated());

        // Validate the StoreUser in the database
        List<StoreUser> storeUserList = storeUserRepository.findAll();
        assertThat(storeUserList).hasSize(databaseSizeBeforeCreate + 1);
        StoreUser testStoreUser = storeUserList.get(storeUserList.size() - 1);
        assertThat(testStoreUser.getStoreId()).isEqualTo(DEFAULT_STORE_ID);
        assertThat(testStoreUser.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createStoreUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = storeUserRepository.findAll().size();

        // Create the StoreUser with an existing ID
        storeUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStoreUserMockMvc.perform(post("/api/store-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeUser)))
            .andExpect(status().isBadRequest());

        // Validate the StoreUser in the database
        List<StoreUser> storeUserList = storeUserRepository.findAll();
        assertThat(storeUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStoreUsers() throws Exception {
        // Initialize the database
        storeUserRepository.saveAndFlush(storeUser);

        // Get all the storeUserList
        restStoreUserMockMvc.perform(get("/api/store-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(storeUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].storeId").value(hasItem(DEFAULT_STORE_ID)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)));
    }
    
    @Test
    @Transactional
    public void getStoreUser() throws Exception {
        // Initialize the database
        storeUserRepository.saveAndFlush(storeUser);

        // Get the storeUser
        restStoreUserMockMvc.perform(get("/api/store-users/{id}", storeUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(storeUser.getId().intValue()))
            .andExpect(jsonPath("$.storeId").value(DEFAULT_STORE_ID))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID));
    }

    @Test
    @Transactional
    public void getNonExistingStoreUser() throws Exception {
        // Get the storeUser
        restStoreUserMockMvc.perform(get("/api/store-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStoreUser() throws Exception {
        // Initialize the database
        storeUserService.save(storeUser);

        int databaseSizeBeforeUpdate = storeUserRepository.findAll().size();

        // Update the storeUser
        StoreUser updatedStoreUser = storeUserRepository.findById(storeUser.getId()).get();
        // Disconnect from session so that the updates on updatedStoreUser are not directly saved in db
        em.detach(updatedStoreUser);
        updatedStoreUser
            .storeId(UPDATED_STORE_ID)
            .userId(UPDATED_USER_ID);

        restStoreUserMockMvc.perform(put("/api/store-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStoreUser)))
            .andExpect(status().isOk());

        // Validate the StoreUser in the database
        List<StoreUser> storeUserList = storeUserRepository.findAll();
        assertThat(storeUserList).hasSize(databaseSizeBeforeUpdate);
        StoreUser testStoreUser = storeUserList.get(storeUserList.size() - 1);
        assertThat(testStoreUser.getStoreId()).isEqualTo(UPDATED_STORE_ID);
        assertThat(testStoreUser.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingStoreUser() throws Exception {
        int databaseSizeBeforeUpdate = storeUserRepository.findAll().size();

        // Create the StoreUser

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStoreUserMockMvc.perform(put("/api/store-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(storeUser)))
            .andExpect(status().isBadRequest());

        // Validate the StoreUser in the database
        List<StoreUser> storeUserList = storeUserRepository.findAll();
        assertThat(storeUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStoreUser() throws Exception {
        // Initialize the database
        storeUserService.save(storeUser);

        int databaseSizeBeforeDelete = storeUserRepository.findAll().size();

        // Get the storeUser
        restStoreUserMockMvc.perform(delete("/api/store-users/{id}", storeUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StoreUser> storeUserList = storeUserRepository.findAll();
        assertThat(storeUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StoreUser.class);
        StoreUser storeUser1 = new StoreUser();
        storeUser1.setId(1L);
        StoreUser storeUser2 = new StoreUser();
        storeUser2.setId(storeUser1.getId());
        assertThat(storeUser1).isEqualTo(storeUser2);
        storeUser2.setId(2L);
        assertThat(storeUser1).isNotEqualTo(storeUser2);
        storeUser1.setId(null);
        assertThat(storeUser1).isNotEqualTo(storeUser2);
    }
}
