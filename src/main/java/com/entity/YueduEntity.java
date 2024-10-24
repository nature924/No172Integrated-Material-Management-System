package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 月度用料
 *
 * @author 
 * @email
 */
@TableName("yuedu")
public class YueduEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YueduEntity() {

	}

	public YueduEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 物资
     */
    @ColumnInfo(comment="物资",type="int(11)")
    @TableField(value = "wuzi_id")

    private Integer wuziId;


    /**
     * 月度用料数量
     */
    @ColumnInfo(comment="月度用料数量",type="int(11)")
    @TableField(value = "yuedu_shuliang")

    private Integer yueduShuliang;


    /**
     * 月度用料介绍
     */
    @ColumnInfo(comment="月度用料介绍",type="longtext")
    @TableField(value = "yuedu_content")

    private String yueduContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "yuedu_delete")

    private Integer yueduDelete;


    /**
     * 月度用料类型
     */
    @ColumnInfo(comment="月度用料类型",type="int(11)")
    @TableField(value = "yuedu_types")

    private Integer yueduTypes;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：物资
	 */
    public Integer getWuziId() {
        return wuziId;
    }
    /**
	 * 设置：物资
	 */

    public void setWuziId(Integer wuziId) {
        this.wuziId = wuziId;
    }
    /**
	 * 获取：月度用料数量
	 */
    public Integer getYueduShuliang() {
        return yueduShuliang;
    }
    /**
	 * 设置：月度用料数量
	 */

    public void setYueduShuliang(Integer yueduShuliang) {
        this.yueduShuliang = yueduShuliang;
    }
    /**
	 * 获取：月度用料介绍
	 */
    public String getYueduContent() {
        return yueduContent;
    }
    /**
	 * 设置：月度用料介绍
	 */

    public void setYueduContent(String yueduContent) {
        this.yueduContent = yueduContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getYueduDelete() {
        return yueduDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setYueduDelete(Integer yueduDelete) {
        this.yueduDelete = yueduDelete;
    }
    /**
	 * 获取：月度用料类型
	 */
    public Integer getYueduTypes() {
        return yueduTypes;
    }
    /**
	 * 设置：月度用料类型
	 */

    public void setYueduTypes(Integer yueduTypes) {
        this.yueduTypes = yueduTypes;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Yuedu{" +
            ", id=" + id +
            ", wuziId=" + wuziId +
            ", yueduShuliang=" + yueduShuliang +
            ", yueduContent=" + yueduContent +
            ", yueduDelete=" + yueduDelete +
            ", yueduTypes=" + yueduTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
