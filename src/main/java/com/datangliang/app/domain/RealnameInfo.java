package com.datangliang.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 实名认证信息表
 */
@ApiModel(description = "实名认证信息表")
@Entity
@Table(name = "realname_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RealnameInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "id_card_num")
    private String idCardNum;

    @Column(name = "id_card_img_id_a")
    private Integer idCardImgIdA;

    @Column(name = "id_card_img_id_b")
    private Integer idCardImgIdB;

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

    public RealnameInfo userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public RealnameInfo realName(String realName) {
        this.realName = realName;
        return this;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public RealnameInfo idCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
        return this;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Integer getIdCardImgIdA() {
        return idCardImgIdA;
    }

    public RealnameInfo idCardImgIdA(Integer idCardImgIdA) {
        this.idCardImgIdA = idCardImgIdA;
        return this;
    }

    public void setIdCardImgIdA(Integer idCardImgIdA) {
        this.idCardImgIdA = idCardImgIdA;
    }

    public Integer getIdCardImgIdB() {
        return idCardImgIdB;
    }

    public RealnameInfo idCardImgIdB(Integer idCardImgIdB) {
        this.idCardImgIdB = idCardImgIdB;
        return this;
    }

    public void setIdCardImgIdB(Integer idCardImgIdB) {
        this.idCardImgIdB = idCardImgIdB;
    }

    public Integer getStatus() {
        return status;
    }

    public RealnameInfo status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public RealnameInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getLastModifyTime() {
        return lastModifyTime;
    }

    public RealnameInfo lastModifyTime(Instant lastModifyTime) {
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
        RealnameInfo realnameInfo = (RealnameInfo) o;
        if (realnameInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), realnameInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RealnameInfo{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", realName='" + getRealName() + "'" +
            ", idCardNum='" + getIdCardNum() + "'" +
            ", idCardImgIdA=" + getIdCardImgIdA() +
            ", idCardImgIdB=" + getIdCardImgIdB() +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", lastModifyTime='" + getLastModifyTime() + "'" +
            "}";
    }
}
