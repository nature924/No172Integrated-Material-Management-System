package com.entity.vo;

import com.entity.WuziChuruInoutEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 出入库
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("wuzi_churu_inout")
public class WuziChuruInoutVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 出入库流水号
     */

    @TableField(value = "wuzi_churu_inout_uuid_number")
    private String wuziChuruInoutUuidNumber;


    /**
     * 出入库名称
     */

    @TableField(value = "wuzi_churu_inout_name")
    private String wuziChuruInoutName;


    /**
     * 出入库类型
     */

    @TableField(value = "wuzi_churu_inout_types")
    private Integer wuziChuruInoutTypes;


    /**
     * 备注
     */

    @TableField(value = "wuzi_churu_inout_content")
    private String wuziChuruInoutContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：出入库流水号
	 */
    public String getWuziChuruInoutUuidNumber() {
        return wuziChuruInoutUuidNumber;
    }


    /**
	 * 获取：出入库流水号
	 */

    public void setWuziChuruInoutUuidNumber(String wuziChuruInoutUuidNumber) {
        this.wuziChuruInoutUuidNumber = wuziChuruInoutUuidNumber;
    }
    /**
	 * 设置：出入库名称
	 */
    public String getWuziChuruInoutName() {
        return wuziChuruInoutName;
    }


    /**
	 * 获取：出入库名称
	 */

    public void setWuziChuruInoutName(String wuziChuruInoutName) {
        this.wuziChuruInoutName = wuziChuruInoutName;
    }
    /**
	 * 设置：出入库类型
	 */
    public Integer getWuziChuruInoutTypes() {
        return wuziChuruInoutTypes;
    }


    /**
	 * 获取：出入库类型
	 */

    public void setWuziChuruInoutTypes(Integer wuziChuruInoutTypes) {
        this.wuziChuruInoutTypes = wuziChuruInoutTypes;
    }
    /**
	 * 设置：备注
	 */
    public String getWuziChuruInoutContent() {
        return wuziChuruInoutContent;
    }


    /**
	 * 获取：备注
	 */

    public void setWuziChuruInoutContent(String wuziChuruInoutContent) {
        this.wuziChuruInoutContent = wuziChuruInoutContent;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
