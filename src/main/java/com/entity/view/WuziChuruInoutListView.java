package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.WuziChuruInoutListEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 出入库详情
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("wuzi_churu_inout_list")
public class WuziChuruInoutListView extends WuziChuruInoutListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 物资
		/**
		* 物资名称
		*/

		@ColumnInfo(comment="物资名称",type="varchar(200)")
		private String wuziName;
		/**
		* 物资编号
		*/

		@ColumnInfo(comment="物资编号",type="varchar(200)")
		private String wuziUuidNumber;
		/**
		* 物资照片
		*/

		@ColumnInfo(comment="物资照片",type="varchar(200)")
		private String wuziPhoto;
		/**
		* 物资类型
		*/
		@ColumnInfo(comment="物资类型",type="int(11)")
		private Integer wuziTypes;
			/**
			* 物资类型的值
			*/
			@ColumnInfo(comment="物资类型的字典表值",type="varchar(200)")
			private String wuziValue;
		/**
		* 物资库存
		*/

		@ColumnInfo(comment="物资库存",type="int(11)")
		private Integer wuziKucunNumber;
		/**
		* 物资介绍
		*/

		@ColumnInfo(comment="物资介绍",type="longtext")
		private String wuziContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer wuziDelete;
	//级联表 出入库
		/**
		* 出入库流水号
		*/

		@ColumnInfo(comment="出入库流水号",type="varchar(200)")
		private String wuziChuruInoutUuidNumber;
		/**
		* 出入库名称
		*/

		@ColumnInfo(comment="出入库名称",type="varchar(200)")
		private String wuziChuruInoutName;
		/**
		* 出入库类型
		*/
		@ColumnInfo(comment="出入库类型",type="int(11)")
		private Integer wuziChuruInoutTypes;
			/**
			* 出入库类型的值
			*/
			@ColumnInfo(comment="出入库类型的字典表值",type="varchar(200)")
			private String wuziChuruInoutValue;
		/**
		* 备注
		*/

		@ColumnInfo(comment="备注",type="longtext")
		private String wuziChuruInoutContent;



	public WuziChuruInoutListView() {

	}

	public WuziChuruInoutListView(WuziChuruInoutListEntity wuziChuruInoutListEntity) {
		try {
			BeanUtils.copyProperties(this, wuziChuruInoutListEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 物资

		/**
		* 获取： 物资名称
		*/
		public String getWuziName() {
			return wuziName;
		}
		/**
		* 设置： 物资名称
		*/
		public void setWuziName(String wuziName) {
			this.wuziName = wuziName;
		}

		/**
		* 获取： 物资编号
		*/
		public String getWuziUuidNumber() {
			return wuziUuidNumber;
		}
		/**
		* 设置： 物资编号
		*/
		public void setWuziUuidNumber(String wuziUuidNumber) {
			this.wuziUuidNumber = wuziUuidNumber;
		}

		/**
		* 获取： 物资照片
		*/
		public String getWuziPhoto() {
			return wuziPhoto;
		}
		/**
		* 设置： 物资照片
		*/
		public void setWuziPhoto(String wuziPhoto) {
			this.wuziPhoto = wuziPhoto;
		}
		/**
		* 获取： 物资类型
		*/
		public Integer getWuziTypes() {
			return wuziTypes;
		}
		/**
		* 设置： 物资类型
		*/
		public void setWuziTypes(Integer wuziTypes) {
			this.wuziTypes = wuziTypes;
		}


			/**
			* 获取： 物资类型的值
			*/
			public String getWuziValue() {
				return wuziValue;
			}
			/**
			* 设置： 物资类型的值
			*/
			public void setWuziValue(String wuziValue) {
				this.wuziValue = wuziValue;
			}

		/**
		* 获取： 物资库存
		*/
		public Integer getWuziKucunNumber() {
			return wuziKucunNumber;
		}
		/**
		* 设置： 物资库存
		*/
		public void setWuziKucunNumber(Integer wuziKucunNumber) {
			this.wuziKucunNumber = wuziKucunNumber;
		}

		/**
		* 获取： 物资介绍
		*/
		public String getWuziContent() {
			return wuziContent;
		}
		/**
		* 设置： 物资介绍
		*/
		public void setWuziContent(String wuziContent) {
			this.wuziContent = wuziContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getWuziDelete() {
			return wuziDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setWuziDelete(Integer wuziDelete) {
			this.wuziDelete = wuziDelete;
		}
	//级联表的get和set 出入库

		/**
		* 获取： 出入库流水号
		*/
		public String getWuziChuruInoutUuidNumber() {
			return wuziChuruInoutUuidNumber;
		}
		/**
		* 设置： 出入库流水号
		*/
		public void setWuziChuruInoutUuidNumber(String wuziChuruInoutUuidNumber) {
			this.wuziChuruInoutUuidNumber = wuziChuruInoutUuidNumber;
		}

		/**
		* 获取： 出入库名称
		*/
		public String getWuziChuruInoutName() {
			return wuziChuruInoutName;
		}
		/**
		* 设置： 出入库名称
		*/
		public void setWuziChuruInoutName(String wuziChuruInoutName) {
			this.wuziChuruInoutName = wuziChuruInoutName;
		}
		/**
		* 获取： 出入库类型
		*/
		public Integer getWuziChuruInoutTypes() {
			return wuziChuruInoutTypes;
		}
		/**
		* 设置： 出入库类型
		*/
		public void setWuziChuruInoutTypes(Integer wuziChuruInoutTypes) {
			this.wuziChuruInoutTypes = wuziChuruInoutTypes;
		}


			/**
			* 获取： 出入库类型的值
			*/
			public String getWuziChuruInoutValue() {
				return wuziChuruInoutValue;
			}
			/**
			* 设置： 出入库类型的值
			*/
			public void setWuziChuruInoutValue(String wuziChuruInoutValue) {
				this.wuziChuruInoutValue = wuziChuruInoutValue;
			}

		/**
		* 获取： 备注
		*/
		public String getWuziChuruInoutContent() {
			return wuziChuruInoutContent;
		}
		/**
		* 设置： 备注
		*/
		public void setWuziChuruInoutContent(String wuziChuruInoutContent) {
			this.wuziChuruInoutContent = wuziChuruInoutContent;
		}


	@Override
	public String toString() {
		return "WuziChuruInoutListView{" +
			", wuziChuruInoutUuidNumber=" + wuziChuruInoutUuidNumber +
			", wuziChuruInoutName=" + wuziChuruInoutName +
			", wuziChuruInoutContent=" + wuziChuruInoutContent +
			", wuziName=" + wuziName +
			", wuziUuidNumber=" + wuziUuidNumber +
			", wuziPhoto=" + wuziPhoto +
			", wuziKucunNumber=" + wuziKucunNumber +
			", wuziContent=" + wuziContent +
			", wuziDelete=" + wuziDelete +
			"} " + super.toString();
	}
}
