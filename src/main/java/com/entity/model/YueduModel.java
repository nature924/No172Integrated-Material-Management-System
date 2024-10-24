package com.entity.model;

import com.entity.YueduEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 月度用料
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YueduModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 物资
     */
    private Integer wuziId;


    /**
     * 月度用料数量
     */
    private Integer yueduShuliang;


    /**
     * 月度用料介绍
     */
    private String yueduContent;


    /**
     * 逻辑删除
     */
    private Integer yueduDelete;


    /**
     * 月度用料类型
     */
    private Integer yueduTypes;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
