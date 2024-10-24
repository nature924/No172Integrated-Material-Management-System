package com.entity.vo;

import com.entity.WuziYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 物资申报
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("wuzi_yuyue")
public class WuziYuyueVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名编号
     */

    @TableField(value = "wuzi_yuyue_uuid_number")
    private String wuziYuyueUuidNumber;


    /**
     * 物资
     */

    @TableField(value = "wuzi_id")
    private Integer wuziId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 申报理由
     */

    @TableField(value = "wuzi_yuyue_text")
    private String wuziYuyueText;


    /**
     * 报名状态
     */

    @TableField(value = "wuzi_yuyue_yesno_types")
    private Integer wuziYuyueYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "wuzi_yuyue_yesno_text")
    private String wuziYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "wuzi_yuyue_shenhe_time")
    private Date wuziYuyueShenheTime;


    /**
     * 物资申报时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：报名编号
	 */
    public String getWuziYuyueUuidNumber() {
        return wuziYuyueUuidNumber;
    }


    /**
	 * 获取：报名编号
	 */

    public void setWuziYuyueUuidNumber(String wuziYuyueUuidNumber) {
        this.wuziYuyueUuidNumber = wuziYuyueUuidNumber;
    }
    /**
	 * 设置：物资
	 */
    public Integer getWuziId() {
        return wuziId;
    }


    /**
	 * 获取：物资
	 */

    public void setWuziId(Integer wuziId) {
        this.wuziId = wuziId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：申报理由
	 */
    public String getWuziYuyueText() {
        return wuziYuyueText;
    }


    /**
	 * 获取：申报理由
	 */

    public void setWuziYuyueText(String wuziYuyueText) {
        this.wuziYuyueText = wuziYuyueText;
    }
    /**
	 * 设置：报名状态
	 */
    public Integer getWuziYuyueYesnoTypes() {
        return wuziYuyueYesnoTypes;
    }


    /**
	 * 获取：报名状态
	 */

    public void setWuziYuyueYesnoTypes(Integer wuziYuyueYesnoTypes) {
        this.wuziYuyueYesnoTypes = wuziYuyueYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getWuziYuyueYesnoText() {
        return wuziYuyueYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setWuziYuyueYesnoText(String wuziYuyueYesnoText) {
        this.wuziYuyueYesnoText = wuziYuyueYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getWuziYuyueShenheTime() {
        return wuziYuyueShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setWuziYuyueShenheTime(Date wuziYuyueShenheTime) {
        this.wuziYuyueShenheTime = wuziYuyueShenheTime;
    }
    /**
	 * 设置：物资申报时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：物资申报时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
