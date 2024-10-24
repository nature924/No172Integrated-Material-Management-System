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
 * 出入库
 *
 * @author 
 * @email
 */
@TableName("wuzi_churu_inout")
public class WuziChuruInoutEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WuziChuruInoutEntity() {

	}

	public WuziChuruInoutEntity(T t) {
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
     * 出入库流水号
     */
    @ColumnInfo(comment="出入库流水号",type="varchar(200)")
    @TableField(value = "wuzi_churu_inout_uuid_number")

    private String wuziChuruInoutUuidNumber;


    /**
     * 出入库名称
     */
    @ColumnInfo(comment="出入库名称",type="varchar(200)")
    @TableField(value = "wuzi_churu_inout_name")

    private String wuziChuruInoutName;


    /**
     * 出入库类型
     */
    @ColumnInfo(comment="出入库类型",type="int(11)")
    @TableField(value = "wuzi_churu_inout_types")

    private Integer wuziChuruInoutTypes;


    /**
     * 备注
     */
    @ColumnInfo(comment="备注",type="longtext")
    @TableField(value = "wuzi_churu_inout_content")

    private String wuziChuruInoutContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="添加时间",type="timestamp")
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
	 * 获取：出入库流水号
	 */
    public String getWuziChuruInoutUuidNumber() {
        return wuziChuruInoutUuidNumber;
    }
    /**
	 * 设置：出入库流水号
	 */

    public void setWuziChuruInoutUuidNumber(String wuziChuruInoutUuidNumber) {
        this.wuziChuruInoutUuidNumber = wuziChuruInoutUuidNumber;
    }
    /**
	 * 获取：出入库名称
	 */
    public String getWuziChuruInoutName() {
        return wuziChuruInoutName;
    }
    /**
	 * 设置：出入库名称
	 */

    public void setWuziChuruInoutName(String wuziChuruInoutName) {
        this.wuziChuruInoutName = wuziChuruInoutName;
    }
    /**
	 * 获取：出入库类型
	 */
    public Integer getWuziChuruInoutTypes() {
        return wuziChuruInoutTypes;
    }
    /**
	 * 设置：出入库类型
	 */

    public void setWuziChuruInoutTypes(Integer wuziChuruInoutTypes) {
        this.wuziChuruInoutTypes = wuziChuruInoutTypes;
    }
    /**
	 * 获取：备注
	 */
    public String getWuziChuruInoutContent() {
        return wuziChuruInoutContent;
    }
    /**
	 * 设置：备注
	 */

    public void setWuziChuruInoutContent(String wuziChuruInoutContent) {
        this.wuziChuruInoutContent = wuziChuruInoutContent;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：添加时间
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
        return "WuziChuruInout{" +
            ", id=" + id +
            ", wuziChuruInoutUuidNumber=" + wuziChuruInoutUuidNumber +
            ", wuziChuruInoutName=" + wuziChuruInoutName +
            ", wuziChuruInoutTypes=" + wuziChuruInoutTypes +
            ", wuziChuruInoutContent=" + wuziChuruInoutContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
