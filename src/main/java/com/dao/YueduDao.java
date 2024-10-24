package com.dao;

import com.entity.YueduEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YueduView;

/**
 * 月度用料 Dao 接口
 *
 * @author 
 */
public interface YueduDao extends BaseMapper<YueduEntity> {

   List<YueduView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
