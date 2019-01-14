package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 企业认证信息表
 */
@ApiModel(description = "企业认证信息表")
@Entity
@Table(name = "enterprise_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnterpriseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "status")
    private Integer status;

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

    public Integer getUserId() {
        return userId;
    }

    public EnterpriseInfo userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public EnterpriseInfo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public EnterpriseInfo legalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
        return this;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonIdCardNum() {
        return legalPersonIdCardNum;
    }

    public EnterpriseInfo legalPersonIdCardNum(String legalPersonIdCardNum) {
        this.legalPersonIdCardNum = legalPersonIdCardNum;
        return this;
    }

    public void setLegalPersonIdCardNum(String legalPersonIdCardNum) {
        this.legalPersonIdCardNum = legalPersonIdCardNum;
    }

    public String getLegalPersonMobile() {
        return legalPersonMobile;
    }

    public EnterpriseInfo legalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile;
        return this;
    }

    public void setLegalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile;
    }

    public String getEnterpriseTel() {
        return enterpriseTel;
    }

    public EnterpriseInfo enterpriseTel(String enterpriseTel) {
        this.enterpriseTel = enterpriseTel;
        return this;
    }

    public void setEnterpriseTel(String enterpriseTel) {
        this.enterpriseTel = enterpriseTel;
    }

    public Integer getBusinessLicenseImgId() {
        return businessLicenseImgId;
    }

    public EnterpriseInfo businessLicenseImgId(Integer businessLicenseImgId) {
        this.businessLicenseImgId = businessLicenseImgId;
        return this;
    }

    public void setBusinessLicenseImgId(Integer businessLicenseImgId) {
        this.businessLicenseImgId = businessLicenseImgId;
    }

    public Integer getStatus() {
        return status;
    }

    public EnterpriseInfo status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public EnterpriseInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public EnterpriseInfo lastModifyTime(Instant lastModifyTime) {
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
        EnterpriseInfo enterpriseInfo = (EnterpriseInfo) o;
        if (enterpriseInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enterpriseInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnterpriseInfo{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", name='" + getName() + "'" +
            ", legalPersonName='" + getLegalPersonName() + "'" +
            ", legalPersonIdCardNum='" + getLegalPersonIdCardNum() + "'" +
            ", legalPersonMobile='" + getLegalPersonMobile() + "'" +
            ", enterpriseTel='" + getEnterpriseTel() + "'" +
            ", businessLicenseImgId=" + getBusinessLicenseImgId() +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
