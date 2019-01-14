package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 推荐记录表
 */
@ApiModel(description = "推荐记录表")
@Entity
@Table(name = "recommend_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RecommendRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rec_user_id")
    private Integer recUserId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "identity_flag")
    private String identityFlag;

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

    public Integer getRecUserId() {
        return recUserId;
    }

    public RecommendRecord recUserId(Integer recUserId) {
        this.recUserId = recUserId;
        return this;
    }

    public void setRecUserId(Integer recUserId) {
        this.recUserId = recUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public RecommendRecord userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public RecommendRecord status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdentityFlag() {
        return identityFlag;
    }

    public RecommendRecord identityFlag(String identityFlag) {
        this.identityFlag = identityFlag;
        return this;
    }

    public void setIdentityFlag(String identityFlag) {
        this.identityFlag = identityFlag;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public RecommendRecord createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public RecommendRecord lastModifyTime(Instant lastModifyTime) {
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
        RecommendRecord recommendRecord = (RecommendRecord) o;
        if (recommendRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recommendRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecommendRecord{" +
            "id=" + getId() +
            ", recUserId=" + getRecUserId() +
            ", userId=" + getUserId() +
            ", status=" + getStatus() +
            ", identityFlag='" + getIdentityFlag() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
