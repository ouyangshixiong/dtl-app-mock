package com.datangliang.app.web.rest;

import com.datangliang.app.DtlAppMockApp;

import com.datangliang.app.domain.Product;
import com.datangliang.app.repository.ProductRepository;
import com.datangliang.app.service.ProductService;
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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.datangliang.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProductResource REST controller.
 *
 * @see ProductResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtlAppMockApp.class)
public class ProductResourceIntTest {

    private static final String DEFAULT_TXN_ID = "AAAAAAAAAA";
    private static final String UPDATED_TXN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CATEGORY_ID = 1;
    private static final Integer UPDATED_CATEGORY_ID = 2;

    private static final Integer DEFAULT_STANDARD_ID = 1;
    private static final Integer UPDATED_STANDARD_ID = 2;

    private static final Integer DEFAULT_STORE_ID = 1;
    private static final Integer UPDATED_STORE_ID = 2;

    private static final String DEFAULT_PRODUCT_IMG = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_IMG = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNIT_PRICE = new BigDecimal(2);

    private static final Integer DEFAULT_AMOUNT = 1;
    private static final Integer UPDATED_AMOUNT = 2;

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    private static final Integer DEFAULT_MIN_DEAL = 1;
    private static final Integer UPDATED_MIN_DEAL = 2;

    private static final String DEFAULT_DEPOT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_DEPOT_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

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

    private MockMvc restProductMockMvc;

    private Product product;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductResource productResource = new ProductResource(productService);
        this.restProductMockMvc = MockMvcBuilders.standaloneSetup(productResource)
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
    public static Product createEntity(EntityManager em) {
        Product product = new Product()
            .txnId(DEFAULT_TXN_ID)
            .title(DEFAULT_TITLE)
            .categoryId(DEFAULT_CATEGORY_ID)
            .standardId(DEFAULT_STANDARD_ID)
            .storeId(DEFAULT_STORE_ID)
            .productImg(DEFAULT_PRODUCT_IMG)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .amount(DEFAULT_AMOUNT)
            .stock(DEFAULT_STOCK)
            .minDeal(DEFAULT_MIN_DEAL)
            .depotAddress(DEFAULT_DEPOT_ADDRESS)
            .contactName(DEFAULT_CONTACT_NAME)
            .mobile(DEFAULT_MOBILE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .createBy(DEFAULT_CREATE_BY)
            .createTime(DEFAULT_CREATE_TIME)
            .lastModifyTime(DEFAULT_LAST_MODIFY_TIME);
        return product;
    }

    @Before
    public void initTest() {
        product = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduct() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isCreated());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate + 1);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getTxnId()).isEqualTo(DEFAULT_TXN_ID);
        assertThat(testProduct.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testProduct.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
        assertThat(testProduct.getStandardId()).isEqualTo(DEFAULT_STANDARD_ID);
        assertThat(testProduct.getStoreId()).isEqualTo(DEFAULT_STORE_ID);
        assertThat(testProduct.getProductImg()).isEqualTo(DEFAULT_PRODUCT_IMG);
        assertThat(testProduct.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testProduct.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testProduct.getStock()).isEqualTo(DEFAULT_STOCK);
        assertThat(testProduct.getMinDeal()).isEqualTo(DEFAULT_MIN_DEAL);
        assertThat(testProduct.getDepotAddress()).isEqualTo(DEFAULT_DEPOT_ADDRESS);
        assertThat(testProduct.getContactName()).isEqualTo(DEFAULT_CONTACT_NAME);
        assertThat(testProduct.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testProduct.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testProduct.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testProduct.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testProduct.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
        assertThat(testProduct.getLastModifyTime()).isEqualTo(DEFAULT_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productRepository.findAll().size();

        // Create the Product with an existing ID
        product.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get all the productList
        restProductMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
            .andExpect(jsonPath("$.[*].txnId").value(hasItem(DEFAULT_TXN_ID.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID)))
            .andExpect(jsonPath("$.[*].standardId").value(hasItem(DEFAULT_STANDARD_ID)))
            .andExpect(jsonPath("$.[*].storeId").value(hasItem(DEFAULT_STORE_ID)))
            .andExpect(jsonPath("$.[*].productImg").value(hasItem(DEFAULT_PRODUCT_IMG.toString())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)))
            .andExpect(jsonPath("$.[*].minDeal").value(hasItem(DEFAULT_MIN_DEAL)))
            .andExpect(jsonPath("$.[*].depotAddress").value(hasItem(DEFAULT_DEPOT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].contactName").value(hasItem(DEFAULT_CONTACT_NAME.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY.toString())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].lastModifyTime").value(hasItem(DEFAULT_LAST_MODIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getProduct() throws Exception {
        // Initialize the database
        productRepository.saveAndFlush(product);

        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(product.getId().intValue()))
            .andExpect(jsonPath("$.txnId").value(DEFAULT_TXN_ID.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID))
            .andExpect(jsonPath("$.standardId").value(DEFAULT_STANDARD_ID))
            .andExpect(jsonPath("$.storeId").value(DEFAULT_STORE_ID))
            .andExpect(jsonPath("$.productImg").value(DEFAULT_PRODUCT_IMG.toString()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK))
            .andExpect(jsonPath("$.minDeal").value(DEFAULT_MIN_DEAL))
            .andExpect(jsonPath("$.depotAddress").value(DEFAULT_DEPOT_ADDRESS.toString()))
            .andExpect(jsonPath("$.contactName").value(DEFAULT_CONTACT_NAME.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY.toString()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
            .andExpect(jsonPath("$.lastModifyTime").value(DEFAULT_LAST_MODIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProduct() throws Exception {
        // Get the product
        restProductMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Update the product
        Product updatedProduct = productRepository.findById(product.getId()).get();
        // Disconnect from session so that the updates on updatedProduct are not directly saved in db
        em.detach(updatedProduct);
        updatedProduct
            .txnId(UPDATED_TXN_ID)
            .title(UPDATED_TITLE)
            .categoryId(UPDATED_CATEGORY_ID)
            .standardId(UPDATED_STANDARD_ID)
            .storeId(UPDATED_STORE_ID)
            .productImg(UPDATED_PRODUCT_IMG)
            .unitPrice(UPDATED_UNIT_PRICE)
            .amount(UPDATED_AMOUNT)
            .stock(UPDATED_STOCK)
            .minDeal(UPDATED_MIN_DEAL)
            .depotAddress(UPDATED_DEPOT_ADDRESS)
            .contactName(UPDATED_CONTACT_NAME)
            .mobile(UPDATED_MOBILE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .createBy(UPDATED_CREATE_BY)
            .createTime(UPDATED_CREATE_TIME)
            .lastModifyTime(UPDATED_LAST_MODIFY_TIME);

        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProduct)))
            .andExpect(status().isOk());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
        Product testProduct = productList.get(productList.size() - 1);
        assertThat(testProduct.getTxnId()).isEqualTo(UPDATED_TXN_ID);
        assertThat(testProduct.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testProduct.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
        assertThat(testProduct.getStandardId()).isEqualTo(UPDATED_STANDARD_ID);
        assertThat(testProduct.getStoreId()).isEqualTo(UPDATED_STORE_ID);
        assertThat(testProduct.getProductImg()).isEqualTo(UPDATED_PRODUCT_IMG);
        assertThat(testProduct.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testProduct.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testProduct.getStock()).isEqualTo(UPDATED_STOCK);
        assertThat(testProduct.getMinDeal()).isEqualTo(UPDATED_MIN_DEAL);
        assertThat(testProduct.getDepotAddress()).isEqualTo(UPDATED_DEPOT_ADDRESS);
        assertThat(testProduct.getContactName()).isEqualTo(UPDATED_CONTACT_NAME);
        assertThat(testProduct.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testProduct.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testProduct.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testProduct.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testProduct.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
        assertThat(testProduct.getLastModifyTime()).isEqualTo(UPDATED_LAST_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingProduct() throws Exception {
        int databaseSizeBeforeUpdate = productRepository.findAll().size();

        // Create the Product

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(product)))
            .andExpect(status().isBadRequest());

        // Validate the Product in the database
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduct() throws Exception {
        // Initialize the database
        productService.save(product);

        int databaseSizeBeforeDelete = productRepository.findAll().size();

        // Get the product
        restProductMockMvc.perform(delete("/api/products/{id}", product.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Product> productList = productRepository.findAll();
        assertThat(productList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);
        product2.setId(2L);
        assertThat(product1).isNotEqualTo(product2);
        product1.setId(null);
        assertThat(product1).isNotEqualTo(product2);
    }
}
