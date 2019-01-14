package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 店铺认证记录表
 */
@ApiModel(description = "店铺认证记录表")
@Entity
@Table(name = "store_auth_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StoreAuthRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "name")
    private String name;

    @Column(name = "store_type")
    private Integer storeType;

    @Column(name = "address")
    private String address;

    @Column(name = "area")
    private String area;

    @Column(name = "linkman")
    private String linkman;

    @Column(name = "tel")
    private String tel;

    @Column(name = "auth_status")
    private Integer authStatus;

    @Column(name = "audit_opinion")
    private String auditOpinion;

    @Column(name = "audit_staff_name")
    private String auditStaffName;

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

    public StoreAuthRecord txnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getName() {
        return name;
    }

    public StoreAuthRecord name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public StoreAuthRecord storeType(Integer storeType) {
        this.storeType = storeType;
        return this;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getAddress() {
        return address;
    }

    public StoreAuthRecord address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public StoreAuthRecord area(String area) {
        this.area = area;
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLinkman() {
        return linkman;
    }

    public StoreAuthRecord linkman(String linkman) {
        this.linkman = linkman;
        return this;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTel() {
        return tel;
    }

    public StoreAuthRecord tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public StoreAuthRecord authStatus(Integer authStatus) {
        this.authStatus = authStatus;
        return this;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public StoreAuthRecord auditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
        return this;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getAuditStaffName() {
        return auditStaffName;
    }

    public StoreAuthRecord auditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
        return this;
    }

    public void setAuditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public StoreAuthRecord createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public StoreAuthRecord lastModifyTime(Instant lastModifyTime) {
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
        StoreAuthRecord storeAuthRecord = (StoreAuthRecord) o;
        if (storeAuthRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), storeAuthRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StoreAuthRecord{" +
            "id=" + getId() +
            ", txnId='" + getTxnId() + "'" +
            ", name='" + getName() + "'" +
            ", storeType=" + getStoreType() +
            ", address='" + getAddress() + "'" +
            ", area='" + getArea() + "'" +
            ", linkman='" + getLinkman() + "'" +
            ", tel='" + getTel() + "'" +
            ", authStatus=" + getAuthStatus() +
            ", auditOpinion='" + getAuditOpinion() + "'" +
            ", auditStaffName='" + getAuditStaffName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
