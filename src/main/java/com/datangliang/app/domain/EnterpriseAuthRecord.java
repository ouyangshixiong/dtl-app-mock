package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 企业认证记录表
 */
@ApiModel(description = "企业认证记录表")
@Entity
@Table(name = "enterprise_auth_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnterpriseAuthRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "legal_person_name")
    private String legalPersonName;

    @Column(name = "legal_person_id_card_num")
    private String legalPersonIdCardNum;

    @Column(name = "legal_person_mobile")
    private String legalPersonMobile;

    @Column(name = "enterprise_tel")
    private String enterpriseTel;

    @Column(name = "business_license_img_id")
    private Integer businessLicenseImgId;

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

    public EnterpriseAuthRecord txnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Integer getUserId() {
        return userId;
    }

    public EnterpriseAuthRecord userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public EnterpriseAuthRecord name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public EnterpriseAuthRecord legalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
        return this;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonIdCardNum() {
        return legalPersonIdCardNum;
    }

    public EnterpriseAuthRecord legalPersonIdCardNum(String legalPersonIdCardNum) {
        this.legalPersonIdCardNum = legalPersonIdCardNum;
        return this;
    }

    public void setLegalPersonIdCardNum(String legalPersonIdCardNum) {
        this.legalPersonIdCardNum = legalPersonIdCardNum;
    }

    public String getLegalPersonMobile() {
        return legalPersonMobile;
    }

    public EnterpriseAuthRecord legalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile;
        return this;
    }

    public void setLegalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile;
    }

    public String getEnterpriseTel() {
        return enterpriseTel;
    }

    public EnterpriseAuthRecord enterpriseTel(String enterpriseTel) {
        this.enterpriseTel = enterpriseTel;
        return this;
    }

    public void setEnterpriseTel(String enterpriseTel) {
        this.enterpriseTel = enterpriseTel;
    }

    public Integer getBusinessLicenseImgId() {
        return businessLicenseImgId;
    }

    public EnterpriseAuthRecord businessLicenseImgId(Integer businessLicenseImgId) {
        this.businessLicenseImgId = businessLicenseImgId;
        return this;
    }

    public void setBusinessLicenseImgId(Integer businessLicenseImgId) {
        this.businessLicenseImgId = businessLicenseImgId;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public EnterpriseAuthRecord authStatus(Integer authStatus) {
        this.authStatus = authStatus;
        return this;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public EnterpriseAuthRecord auditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
        return this;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getAuditStaffName() {
        return auditStaffName;
    }

    public EnterpriseAuthRecord auditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
        return this;
    }

    public void setAuditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public EnterpriseAuthRecord createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public EnterpriseAuthRecord lastModifyTime(Instant lastModifyTime) {
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
        EnterpriseAuthRecord enterpriseAuthRecord = (EnterpriseAuthRecord) o;
        if (enterpriseAuthRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enterpriseAuthRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnterpriseAuthRecord{" +
            "id=" + getId() +
            ", txnId='" + getTxnId() + "'" +
            ", userId=" + getUserId() +
            ", name='" + getName() + "'" +
            ", legalPersonName='" + getLegalPersonName() + "'" +
            ", legalPersonIdCardNum='" + getLegalPersonIdCardNum() + "'" +
            ", legalPersonMobile='" + getLegalPersonMobile() + "'" +
            ", enterpriseTel='" + getEnterpriseTel() + "'" +
            ", businessLicenseImgId=" + getBusinessLicenseImgId() +
            ", authStatus=" + getAuthStatus() +
            ", auditOpinion='" + getAuditOpinion() + "'" +
            ", auditStaffName='" + getAuditStaffName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
