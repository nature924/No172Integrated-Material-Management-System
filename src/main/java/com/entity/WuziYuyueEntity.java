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
 * 物资申报
 *
 * @author 
 * @email
 */
@TableName("wuzi_yuyue")
public class WuziYuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WuziYuyueEntity() {

	}

	public WuziYuyueEntity(T t) {
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
     * 报名编号
     */
    @ColumnInfo(comment="报名编号",type="varchar(200)")
    @TableField(value = "wuzi_yuyue_uuid_number")

    private String wuziYuyueUuidNumber;


    /**
     * 物资
     */
    @ColumnInfo(comment="物资",type="int(11)")
    @TableField(value = "wuzi_id")

    private Integer wuziId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 申报理由
     */
    @ColumnInfo(comment="申报理由",type="longtext")
    @TableField(value = "wuzi_yuyue_text")

    private String wuziYuyueText;


    /**
     * 报名状态
     */
    @ColumnInfo(comment="报名状态",type="int(11)")
    @TableField(value = "wuzi_yuyue_yesno_types")

    private Integer wuziYuyueYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "wuzi_yuyue_yesno_text")

    private String wuziYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "wuzi_yuyue_shenhe_time")

    private Date wuziYuyueShenheTime;


    /**
     * 物资申报时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="物资申报时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
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
	 * 获取：报名编号
	 */
    public String getWuziYuyueUuidNumber() {
        return wuziYuyueUuidNumber;
    }
    /**
	 * 设置：报名编号
	 */

    public void setWuziYuyueUuidNumber(String wuziYuyueUuidNumber) {
        this.wuziYuyueUuidNumber = wuziYuyueUuidNumber;
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
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：申报理由
	 */
    public String getWuziYuyueText() {
        return wuziYuyueText;
    }
    /**
	 * 设置：申报理由
	 */

    public void setWuziYuyueText(String wuziYuyueText) {
        this.wuziYuyueText = wuziYuyueText;
    }
    /**
	 * 获取：报名状态
	 */
    public Integer getWuziYuyueYesnoTypes() {
        return wuziYuyueYesnoTypes;
    }
    /**
	 * 设置：报名状态
	 */

    public void setWuziYuyueYesnoTypes(Integer wuziYuyueYesnoTypes) {
        this.wuziYuyueYesnoTypes = wuziYuyueYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getWuziYuyueYesnoText() {
        return wuziYuyueYesnoText;
    }
    /**
	 * 设置：审核回复
	 */

    public void setWuziYuyueYesnoText(String wuziYuyueYesnoText) {
        this.wuziYuyueYesnoText = wuziYuyueYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getWuziYuyueShenheTime() {
        return wuziYuyueShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setWuziYuyueShenheTime(Date wuziYuyueShenheTime) {
        this.wuziYuyueShenheTime = wuziYuyueShenheTime;
    }
    /**
	 * 获取：物资申报时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：物资申报时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WuziYuyue{" +
            ", id=" + id +
            ", wuziYuyueUuidNumber=" + wuziYuyueUuidNumber +
            ", wuziId=" + wuziId +
            ", yonghuId=" + yonghuId +
            ", wuziYuyueText=" + wuziYuyueText +
            ", wuziYuyueYesnoTypes=" + wuziYuyueYesnoTypes +
            ", wuziYuyueYesnoText=" + wuziYuyueYesnoText +
            ", wuziYuyueShenheTime=" + DateUtil.convertString(wuziYuyueShenheTime,"yyyy-MM-dd") +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
