package com.entity.vo;

import com.entity.YueduEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 月度用料
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yuedu")
public class YueduVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 物资
     */

    @TableField(value = "wuzi_id")
    private Integer wuziId;


    /**
     * 月度用料数量
     */

    @TableField(value = "yuedu_shuliang")
    private Integer yueduShuliang;


    /**
     * 月度用料介绍
     */

    @TableField(value = "yuedu_content")
    private String yueduContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "yuedu_delete")
    private Integer yueduDelete;


    /**
     * 月度用料类型
     */

    @TableField(value = "yuedu_types")
    private Integer yueduTypes;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：月度用料数量
	 */
    public Integer getYueduShuliang() {
        return yueduShuliang;
    }


    /**
	 * 获取：月度用料数量
	 */

    public void setYueduShuliang(Integer yueduShuliang) {
        this.yueduShuliang = yueduShuliang;
    }
    /**
	 * 设置：月度用料介绍
	 */
    public String getYueduContent() {
        return yueduContent;
    }


    /**
	 * 获取：月度用料介绍
	 */

    public void setYueduContent(String yueduContent) {
        this.yueduContent = yueduContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getYueduDelete() {
        return yueduDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setYueduDelete(Integer yueduDelete) {
        this.yueduDelete = yueduDelete;
    }
    /**
	 * 设置：月度用料类型
	 */
    public Integer getYueduTypes() {
        return yueduTypes;
    }


    /**
	 * 获取：月度用料类型
	 */

    public void setYueduTypes(Integer yueduTypes) {
        this.yueduTypes = yueduTypes;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
