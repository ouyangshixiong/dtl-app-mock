package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 银行卡认证信息表
 */
@ApiModel(description = "银行卡认证信息表")
@Entity
@Table(name = "bankcard_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BankcardInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_account_name")
    private String bankAccountName;

    @Column(name = "bank_card_num")
    private String bankCardNum;

    @Column(name = "bank_card_img_id")
    private Integer bankCardImgId;

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

    public BankcardInfo userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public BankcardInfo bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public BankcardInfo bankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
        return this;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public BankcardInfo bankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
        return this;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public Integer getBankCardImgId() {
        return bankCardImgId;
    }

    public BankcardInfo bankCardImgId(Integer bankCardImgId) {
        this.bankCardImgId = bankCardImgId;
        return this;
    }

    public void setBankCardImgId(Integer bankCardImgId) {
        this.bankCardImgId = bankCardImgId;
    }

    public Integer getStatus() {
        return status;
    }

    public BankcardInfo status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public BankcardInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public BankcardInfo lastModifyTime(Instant lastModifyTime) {
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
        BankcardInfo bankcardInfo = (BankcardInfo) o;
        if (bankcardInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankcardInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankcardInfo{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", bankName='" + getBankName() + "'" +
            ", bankAccountName='" + getBankAccountName() + "'" +
            ", bankCardNum='" + getBankCardNum() + "'" +
            ", bankCardImgId=" + getBankCardImgId() +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
