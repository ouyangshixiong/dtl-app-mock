package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 实地认证信息表
 */
@ApiModel(description = "实地认证信息表")
@Entity
@Table(name = "site_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SiteInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "site_type")
    private Integer siteType;

    @Column(name = "address")
    private String address;

    @Column(name = "site_img_ids")
    private String siteImgIds;

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

    public SiteInfo userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSiteType() {
        return siteType;
    }

    public SiteInfo siteType(Integer siteType) {
        this.siteType = siteType;
        return this;
    }

    public void setSiteType(Integer siteType) {
        this.siteType = siteType;
    }

    public String getAddress() {
        return address;
    }

    public SiteInfo address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSiteImgIds() {
        return siteImgIds;
    }

    public SiteInfo siteImgIds(String siteImgIds) {
        this.siteImgIds = siteImgIds;
        return this;
    }

    public void setSiteImgIds(String siteImgIds) {
        this.siteImgIds = siteImgIds;
    }

    public Integer getStatus() {
        return status;
    }

    public SiteInfo status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public SiteInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public SiteInfo lastModifyTime(Instant lastModifyTime) {
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
        SiteInfo siteInfo = (SiteInfo) o;
        if (siteInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), siteInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SiteInfo{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", siteType=" + getSiteType() +
            ", address='" + getAddress() + "'" +
            ", siteImgIds='" + getSiteImgIds() + "'" +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
