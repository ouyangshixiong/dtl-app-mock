package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 短信验证码流水记录表
 */
@ApiModel(description = "短信验证码流水记录表")
@Entity
@Table(name = "isp")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Isp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "template")
    private String template;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Instant createTime;

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

    public Isp txnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getMobile() {
        return mobile;
    }

    public Isp mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTemplate() {
        return template;
    }

    public Isp template(String template) {
        this.template = template;
        return this;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Integer getStatus() {
        return status;
    }

    public Isp status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public Isp createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
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
        Isp isp = (Isp) o;
        if (isp.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), isp.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Isp{" +
            "id=" + getId() +
            ", txnId='" + getTxnId() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", template='" + getTemplate() + "'" +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            "}";
    }
}
