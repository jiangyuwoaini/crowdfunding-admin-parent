package com.lblz.crowd.beans;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lblz
 * @deacription 共用的属性
 * @date 2021/11/2 9:48
 **/
//public class GeneralProperty<T extends Model> extends Model<T> implements Serializable {
public class GeneralProperty implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 创建人id
     */
    private Long createId;
    /**
     * 修改人id
     */
    private Long modifyId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改信息
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * 删除状态
     */
    @TableLogic(delval = "1",value = "0")
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    /**
     * 系统id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long systemId;

    protected Serializable pkVal() { //该方法表示返回当前id
        return this.id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    @Override
    public String toString() {
        return "GeneralProperty{" +
                "id=" + id +
                ", createId=" + createId +
                ", modifyId=" + modifyId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                ", systemId=" + systemId +
                '}';
    }
}
