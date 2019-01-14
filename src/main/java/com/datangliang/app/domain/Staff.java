package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 后台信息用户表
 */
@ApiModel(description = "后台信息用户表")
@Entity
@Table(name = "staff")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staff_name")
    private String staffName;

    @Column(name = "jhi_password")
    private String password;

    @Column(name = "real_name")
    private String realName;

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

    public String getStaffName() {
        return staffName;
    }

    public Staff staffName(String staffName) {
        this.staffName = staffName;
        return this;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPassword() {
        return password;
    }

    public Staff password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public Staff realName(String realName) {
        this.realName = realName;
        return this;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getStatus() {
        return status;
    }

    public Staff status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public Staff createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public Staff lastModifyTime(Instant lastModifyTime) {
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
        Staff staff = (Staff) o;
        if (staff.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), staff.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Staff{" +
            "id=" + getId() +
            ", staffName='" + getStaffName() + "'" +
            ", password='" + getPassword() + "'" +
            ", realName='" + getRealName() + "'" +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
