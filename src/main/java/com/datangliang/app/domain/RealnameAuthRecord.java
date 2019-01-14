package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 实名认证记录表
 */
@ApiModel(description = "实名认证记录表")
@Entity
@Table(name = "realname_auth_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RealnameAuthRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "id_card_num")
    private String idCardNum;

    @Column(name = "id_card_img_a")
    private Integer idCardImgA;

    @Column(name = "id_card_img_b")
    private Integer idCardImgB;

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

    public RealnameAuthRecord txnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Integer getUserId() {
        return userId;
    }

    public RealnameAuthRecord userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public RealnameAuthRecord realName(String realName) {
        this.realName = realName;
        return this;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public RealnameAuthRecord idCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
        return this;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Integer getIdCardImgA() {
        return idCardImgA;
    }

    public RealnameAuthRecord idCardImgA(Integer idCardImgA) {
        this.idCardImgA = idCardImgA;
        return this;
    }

    public void setIdCardImgA(Integer idCardImgA) {
        this.idCardImgA = idCardImgA;
    }

    public Integer getIdCardImgB() {
        return idCardImgB;
    }

    public RealnameAuthRecord idCardImgB(Integer idCardImgB) {
        this.idCardImgB = idCardImgB;
        return this;
    }

    public void setIdCardImgB(Integer idCardImgB) {
        this.idCardImgB = idCardImgB;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public RealnameAuthRecord authStatus(Integer authStatus) {
        this.authStatus = authStatus;
        return this;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public RealnameAuthRecord auditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
        return this;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getAuditStaffName() {
        return auditStaffName;
    }

    public RealnameAuthRecord auditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
        return this;
    }

    public void setAuditStaffName(String auditStaffName) {
        this.auditStaffName = auditStaffName;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public RealnameAuthRecord createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public RealnameAuthRecord lastModifyTime(Instant lastModifyTime) {
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
        RealnameAuthRecord realnameAuthRecord = (RealnameAuthRecord) o;
        if (realnameAuthRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), realnameAuthRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RealnameAuthRecord{" +
            "id=" + getId() +
            ", txnId='" + getTxnId() + "'" +
            ", userId=" + getUserId() +
            ", realName='" + getRealName() + "'" +
            ", idCardNum='" + getIdCardNum() + "'" +
            ", idCardImgA=" + getIdCardImgA() +
            ", idCardImgB=" + getIdCardImgB() +
            ", authStatus=" + getAuthStatus() +
            ", auditOpinion='" + getAuditOpinion() + "'" +
            ", auditStaffName='" + getAuditStaffName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
