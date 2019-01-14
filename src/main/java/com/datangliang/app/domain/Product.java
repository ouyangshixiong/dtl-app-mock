package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * 商品信息表
 */
@ApiModel(description = "商品信息表")
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "title")
    private String title;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "standard_id")
    private Integer standardId;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "min_deal")
    private Integer minDeal;

    @Column(name = "depot_address")
    private String depotAddress;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "last_modify_time")
    private Instant lastModifyTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxnId() {
        return txnId;
    }

    public Product txnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getTitle() {
        return title;
    }

    public Product title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Product categoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStandardId() {
        return standardId;
    }

    public Product standardId(Integer standardId) {
        this.standardId = standardId;
        return this;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public Product storeId(Integer storeId) {
        this.storeId = storeId;
        return this;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getProductImg() {
        return productImg;
    }

    public Product productImg(String productImg) {
        this.productImg = productImg;
        return this;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Product unitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public Product amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStock() {
        return stock;
    }

    public Product stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMinDeal() {
        return minDeal;
    }

    public Product minDeal(Integer minDeal) {
        this.minDeal = minDeal;
        return this;
    }

    public void setMinDeal(Integer minDeal) {
        this.minDeal = minDeal;
    }

    public String getDepotAddress() {
        return depotAddress;
    }

    public Product depotAddress(String depotAddress) {
        this.depotAddress = depotAddress;
        return this;
    }

    public void setDepotAddress(String depotAddress) {
        this.depotAddress = depotAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public Product contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMobile() {
        return mobile;
    }

    public Product mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public Product description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public Product status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public Product createBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public Product createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public Product lastModifyTime(Instant lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
        return this;
    }

    public void setLastModifyTime(Instant lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", txnId='" + getTxnId() + "'" +
            ", title='" + getTitle() + "'" +
            ", categoryId=" + getCategoryId() +
            ", standardId=" + getStandardId() +
            ", storeId=" + getStoreId() +
            ", productImg='" + getProductImg() + "'" +
            ", unitPrice=" + getUnitPrice() +
            ", amount=" + getAmount() +
            ", stock=" + getStock() +
            ", minDeal=" + getMinDeal() +
            ", depotAddress='" + getDepotAddress() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
