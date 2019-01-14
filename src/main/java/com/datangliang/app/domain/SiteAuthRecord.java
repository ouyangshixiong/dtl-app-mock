package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 实地认证记录表
 */
@ApiModel(description = "实地认证记录表")
@Entity
@Table(name = "site_auth_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SiteAuthRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "address")
    private String address;

    @Column(name = "site_img_ids")
    private String siteImgIds;

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

    public SiteAuthRecord txnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Integer getUserId() {
        return userId;
    }

    public SiteAuthRecord userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public SiteAuthRecord type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public SiteAuthRecord address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSiteImgIds() {
        return siteImgIds;
    }

    public SiteAuthRecord siteImgIds(String siteImgIds) {
        this.siteImgIds = siteImgIds;
        return this;
    }

    public void setSiteImgIds(String siteImgIds) {
        this.siteImgIds = siteImgIds;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public SiteAuthRecord authStatus(Integer authStatus) {
        this.authStatus = authStatus;
        return this;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public SiteAuthRecord auditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
        return this;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getAuditStaffName() {
        return auditStaffName;
    }

    public SiteAuthRecord auditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
        return this;
    }

    public void setAuditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public SiteAuthRecord createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public SiteAuthRecord lastModifyTime(Instant lastModifyTime) {
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
        SiteAuthRecord siteAuthRecord = (SiteAuthRecord) o;
        if (siteAuthRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), siteAuthRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SiteAuthRecord{" +
            "id=" + getId() +
            ", txnId='" + getTxnId() + "'" +
            ", userId=" + getUserId() +
            ", type=" + getType() +
            ", address='" + getAddress() + "'" +
            ", siteImgIds='" + getSiteImgIds() + "'" +
            ", authStatus=" + getAuthStatus() +
            ", auditOpinion='" + getAuditOpinion() + "'" +
            ", auditStaffName='" + getAuditStaffName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
