package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 用户等级表
 */
@ApiModel(description = "用户等级表")
@Entity
@Table(name = "user_level")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "integral_rule")
    private Integer integralRule;

    @Column(name = "user_identity")
    private Integer userIdentity;

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

    public String getName() {
        return name;
    }

    public UserLevel name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIntegralRule() {
        return integralRule;
    }

    public UserLevel integralRule(Integer integralRule) {
        this.integralRule = integralRule;
        return this;
    }

    public void setIntegralRule(Integer integralRule) {
        this.integralRule = integralRule;
    }

    public Integer getUserIdentity() {
        return userIdentity;
    }

    public UserLevel userIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
        return this;
    }

    public void setUserIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
    }

    public Integer getStatus() {
        return status;
    }

    public UserLevel status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public UserLevel createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public UserLevel lastModifyTime(Instant lastModifyTime) {
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
        UserLevel userLevel = (UserLevel) o;
        if (userLevel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userLevel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserLevel{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", integralRule=" + getIntegralRule() +
            ", userIdentity=" + getUserIdentity() +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
