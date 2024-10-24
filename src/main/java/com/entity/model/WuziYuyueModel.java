package com.entity.model;

import com.entity.WuziYuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 物资申报
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WuziYuyueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名编号
     */
    private String wuziYuyueUuidNumber;


    /**
     * 物资
     */
    private Integer wuziId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 申报理由
     */
    private String wuziYuyueText;


    /**
     * 报名状态
     */
    private Integer wuziYuyueYesnoTypes;


    /**
     * 审核回复
     */
    private String wuziYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date wuziYuyueShenheTime;


    /**
     * 物资申报时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
