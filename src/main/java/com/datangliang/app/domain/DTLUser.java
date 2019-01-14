package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 用户信息表
 */
@ApiModel(description = "用户信息表")
@Entity
@Table(name = "dtl_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DTLUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_role_id")
    private Integer userRoleId;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_account")
    private String account;

    @Column(name = "jhi_password")
    private String password;

    @Column(name = "status")
    private Integer status;

    @Column(name = "user_type")
    private Integer userType;

    @Column(name = "user_identity")
    private Integer userIdentity;

    @Column(name = "source")
    private Integer source;

    @Column(name = "ip_addr")
    private String ipAddr;

    @Column(name = "reg_time")
    private Instant regTime;

    @Column(name = "last_login_time")
    private Instant lastLoginTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public DTLUser userRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
        return this;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getName() {
        return name;
    }

    public DTLUser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public DTLUser account(String account) {
        this.account = account;
        return this;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public DTLUser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public DTLUser status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserType() {
        return userType;
    }

    public DTLUser userType(Integer userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserIdentity() {
        return userIdentity;
    }

    public DTLUser userIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
        return this;
    }

    public void setUserIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
    }

    public Integer getSource() {
        return source;
    }

    public DTLUser source(Integer source) {
        this.source = source;
        return this;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public DTLUser ipAddr(String ipAddr) {
        this.ipAddr = ipAddr;
        return this;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Instant getRegTime() {
        return regTime;
    }

    public DTLUser regTime(Instant regTime) {
        this.regTime = regTime;
        return this;
    }

    public void setRegTime(Instant regTime) {
        this.regTime = regTime;
    }

    public Instant getLastLoginTime() {
        return lastLoginTime;
    }

    public DTLUser lastLoginTime(Instant lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public void setLastLoginTime(Instant lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
        DTLUser dTLUser = (DTLUser) o;
        if (dTLUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dTLUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DTLUser{" +
            "id=" + getId() +
            ", userRoleId=" + getUserRoleId() +
            ", name='" + getName() + "'" +
            ", account='" + getAccount() + "'" +
            ", password='" + getPassword() + "'" +
            ", status=" + getStatus() +
            ", userType=" + getUserType() +
            ", userIdentity=" + getUserIdentity() +
            ", source=" + getSource() +
            ", ipAddr='" + getIpAddr() + "'" +
            ", regTime='" + getRegTime() + "'" +
            ", lastLoginTime='" + getLastLoginTime() + "'" +
            "}";
    }
}
