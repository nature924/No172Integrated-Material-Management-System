
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 月度用料
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yuedu")
public class YueduController {
    private static final Logger logger = LoggerFactory.getLogger(YueduController.class);

    private static final String TABLE_NAME = "yuedu";

    @Autowired
    private YueduService yueduService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaozuorizhiService caozuorizhiService;//操作日志
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private WuziService wuziService;//物资
    @Autowired
    private WuziChuruInoutService wuziChuruInoutService;//出入库
    @Autowired
    private WuziChuruInoutListService wuziChuruInoutListService;//出入库详情
    @Autowired
    private WuziYuyueService wuziYuyueService;//物资申报
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("yueduDeleteStart",1);params.put("yueduDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = yueduService.queryPage(params);

        //字典表数据转换
        List<YueduView> list =(List<YueduView>)page.getList();
        for(YueduView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"列表查询",list.toString());
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YueduEntity yuedu = yueduService.selectById(id);
        if(yuedu !=null){
            //entity转view
            YueduView view = new YueduView();
            BeanUtils.copyProperties( yuedu , view );//把实体数据重构到view中
            //级联表 物资
            //级联表
            WuziEntity wuzi = wuziService.selectById(yuedu.getWuziId());
            if(wuzi != null){
            BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setWuziId(wuzi.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"单条数据查看",view.toString());
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YueduEntity yuedu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yuedu:{}",this.getClass().getName(),yuedu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YueduEntity> queryWrapper = new EntityWrapper<YueduEntity>()
            .eq("wuzi_id", yuedu.getWuziId())
            .eq("yuedu_shuliang", yuedu.getYueduShuliang())
            .eq("yuedu_types", yuedu.getYueduTypes())
            .eq("yuedu_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YueduEntity yueduEntity = yueduService.selectOne(queryWrapper);
        if(yueduEntity==null){
            yuedu.setYueduDelete(1);
            yuedu.setInsertTime(new Date());
            yuedu.setCreateTime(new Date());
            yueduService.insert(yuedu);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",yuedu.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YueduEntity yuedu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yuedu:{}",this.getClass().getName(),yuedu.toString());
        YueduEntity oldYueduEntity = yueduService.selectById(yuedu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            yueduService.updateById(yuedu);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(yuedu, oldYueduEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YueduEntity> oldYueduList =yueduService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<YueduEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YueduEntity yueduEntity = new YueduEntity();
            yueduEntity.setId(id);
            yueduEntity.setYueduDelete(2);
            list.add(yueduEntity);
        }
        if(list != null && list.size() >0){
            yueduService.updateBatchById(list);
        }

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldYueduList.toString());
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<YueduEntity> yueduList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YueduEntity yueduEntity = new YueduEntity();
//                            yueduEntity.setWuziId(Integer.valueOf(data.get(0)));   //物资 要改的
//                            yueduEntity.setYueduShuliang(Integer.valueOf(data.get(0)));   //月度用料数量 要改的
//                            yueduEntity.setYueduContent("");//详情和图片
//                            yueduEntity.setYueduDelete(1);//逻辑删除字段
//                            yueduEntity.setYueduTypes(Integer.valueOf(data.get(0)));   //月度用料类型 要改的
//                            yueduEntity.setInsertTime(date);//时间
//                            yueduEntity.setCreateTime(date);//时间
                            yueduList.add(yueduEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        yueduService.insertBatch(yueduList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",yueduList.toString());
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




}

