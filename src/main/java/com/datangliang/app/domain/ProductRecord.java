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
 * 商品审核历史记录表
 */
@ApiModel(description = "商品审核历史记录表")
@Entity
@Table(name = "product_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductRecord implements Serializable {

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

    @Column(name = "objection")
    private String objection;

    @Column(name = "record_name")
    private String recordName;

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

    public ProductRecord txnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getTitle() {
        return title;
    }

    public ProductRecord title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public ProductRecord categoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStandardId() {
        return standardId;
    }

    public ProductRecord standardId(Integer standardId) {
        this.standardId = standardId;
        return this;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public ProductRecord storeId(Integer storeId) {
        this.storeId = storeId;
        return this;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getProductImg() {
        return productImg;
    }

    public ProductRecord productImg(String productImg) {
        this.productImg = productImg;
        return this;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public ProductRecord unitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public ProductRecord stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMinDeal() {
        return minDeal;
    }

    public ProductRecord minDeal(Integer minDeal) {
        this.minDeal = minDeal;
        return this;
    }

    public void setMinDeal(Integer minDeal) {
        this.minDeal = minDeal;
    }

    public String getDepotAddress() {
        return depotAddress;
    }

    public ProductRecord depotAddress(String depotAddress) {
        this.depotAddress = depotAddress;
        return this;
    }

    public void setDepotAddress(String depotAddress) {
        this.depotAddress = depotAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public ProductRecord contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMobile() {
        return mobile;
    }

    public ProductRecord mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public ProductRecord description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductRecord status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getObjection() {
        return objection;
    }

    public ProductRecord objection(String objection) {
        this.objection = objection;
        return this;
    }

    public void setObjection(String objection) {
        this.objection = objection;
    }

    public String getRecordName() {
        return recordName;
    }

    public ProductRecord recordName(String recordName) {
        this.recordName = recordName;
        return this;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public ProductRecord createBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public ProductRecord createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public ProductRecord lastModifyTime(Instant lastModifyTime) {
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
        ProductRecord productRecord = (ProductRecord) o;
        if (productRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductRecord{" +
            "id=" + getId() +
            ", txnId='" + getTxnId() + "'" +
            ", title='" + getTitle() + "'" +
            ", categoryId=" + getCategoryId() +
            ", standardId=" + getStandardId() +
            ", storeId=" + getStoreId() +
            ", productImg='" + getProductImg() + "'" +
            ", unitPrice=" + getUnitPrice() +
            ", stock=" + getStock() +
            ", minDeal=" + getMinDeal() +
            ", depotAddress='" + getDepotAddress() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", objection='" + getObjection() + "'" +
            ", recordName='" + getRecordName() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
