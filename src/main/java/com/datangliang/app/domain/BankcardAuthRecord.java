package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 银行卡认证记录表
 */
@ApiModel(description = "银行卡认证记录表")
@Entity
@Table(name = "bankcard_auth_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BankcardAuthRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "txn_id")
    private String txnId;

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

    public BankcardAuthRecord txnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Integer getUserId() {
        return userId;
    }

    public BankcardAuthRecord userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public BankcardAuthRecord bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public BankcardAuthRecord bankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
        return this;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public BankcardAuthRecord bankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
        return this;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public Integer getBankCardImgId() {
        return bankCardImgId;
    }

    public BankcardAuthRecord bankCardImgId(Integer bankCardImgId) {
        this.bankCardImgId = bankCardImgId;
        return this;
    }

    public void setBankCardImgId(Integer bankCardImgId) {
        this.bankCardImgId = bankCardImgId;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public BankcardAuthRecord authStatus(Integer authStatus) {
        this.authStatus = authStatus;
        return this;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public BankcardAuthRecord auditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
        return this;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getAuditStaffName() {
        return auditStaffName;
    }

    public BankcardAuthRecord auditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
        return this;
    }

    public void setAuditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public BankcardAuthRecord createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public BankcardAuthRecord lastModifyTime(Instant lastModifyTime) {
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
        BankcardAuthRecord bankcardAuthRecord = (BankcardAuthRecord) o;
        if (bankcardAuthRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankcardAuthRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankcardAuthRecord{" +
            "id=" + getId() +
            ", txnId='" + getTxnId() + "'" +
            ", userId=" + getUserId() +
            ", bankName='" + getBankName() + "'" +
            ", bankAccountName='" + getBankAccountName() + "'" +
            ", bankCardNum='" + getBankCardNum() + "'" +
            ", bankCardImgId=" + getBankCardImgId() +
            ", authStatus=" + getAuthStatus() +
            ", auditOpinion='" + getAuditOpinion() + "'" +
            ", auditStaffName='" + getAuditStaffName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
