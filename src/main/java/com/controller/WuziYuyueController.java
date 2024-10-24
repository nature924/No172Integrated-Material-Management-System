
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
 * 物资申报
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/wuziYuyue")
public class WuziYuyueController {
    private static final Logger logger = LoggerFactory.getLogger(WuziYuyueController.class);

    private static final String TABLE_NAME = "wuziYuyue";

    @Autowired
    private WuziYuyueService wuziYuyueService;


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
    private YonghuService yonghuService;//用户
    @Autowired
    private YueduService yueduService;//月度用料
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
        CommonUtil.checkMap(params);
        PageUtils page = wuziYuyueService.queryPage(params);

        //字典表数据转换
        List<WuziYuyueView> list =(List<WuziYuyueView>)page.getList();
        for(WuziYuyueView c:list){
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
        WuziYuyueEntity wuziYuyue = wuziYuyueService.selectById(id);
        if(wuziYuyue !=null){
            //entity转view
            WuziYuyueView view = new WuziYuyueView();
            BeanUtils.copyProperties( wuziYuyue , view );//把实体数据重构到view中
            //级联表 物资
            //级联表
            WuziEntity wuzi = wuziService.selectById(wuziYuyue.getWuziId());
            if(wuzi != null){
            BeanUtils.copyProperties( wuzi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setWuziId(wuzi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(wuziYuyue.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody WuziYuyueEntity wuziYuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,wuziYuyue:{}",this.getClass().getName(),wuziYuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            wuziYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<WuziYuyueEntity> queryWrapper = new EntityWrapper<WuziYuyueEntity>()
            .eq("wuzi_id", wuziYuyue.getWuziId())
            .eq("yonghu_id", wuziYuyue.getYonghuId())
            .in("wuzi_yuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WuziYuyueEntity wuziYuyueEntity = wuziYuyueService.selectOne(queryWrapper);
//        if(wuziYuyueEntity==null){
            wuziYuyue.setWuziYuyueYesnoTypes(1);
            wuziYuyue.setInsertTime(new Date());
            wuziYuyue.setCreateTime(new Date());
            wuziYuyueService.insert(wuziYuyue);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",wuziYuyue.toString());
            return R.ok();
//        }else {
//            if(wuziYuyueEntity.getWuziYuyueYesnoTypes()==1)
//                return R.error(511,"有相同的待审核的数据");
//            else if(wuziYuyueEntity.getWuziYuyueYesnoTypes()==2)
//                return R.error(511,"有相同的审核通过的数据");
//            else
//                return R.error(511,"表中有相同数据");
//        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WuziYuyueEntity wuziYuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,wuziYuyue:{}",this.getClass().getName(),wuziYuyue.toString());
        WuziYuyueEntity oldWuziYuyueEntity = wuziYuyueService.selectById(wuziYuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            wuziYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            wuziYuyueService.updateById(wuziYuyue);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(wuziYuyue, oldWuziYuyueEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody WuziYuyueEntity wuziYuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,wuziYuyueEntity:{}",this.getClass().getName(),wuziYuyueEntity.toString());

        WuziYuyueEntity oldWuziYuyue = wuziYuyueService.selectById(wuziYuyueEntity.getId());//查询原先数据

//        if(wuziYuyueEntity.getWuziYuyueYesnoTypes() == 2){//通过
//            wuziYuyueEntity.setWuziYuyueTypes();
//        }else if(wuziYuyueEntity.getWuziYuyueYesnoTypes() == 3){//拒绝
//            wuziYuyueEntity.setWuziYuyueTypes();
//        }
        wuziYuyueEntity.setWuziYuyueShenheTime(new Date());//审核时间
        wuziYuyueService.updateById(wuziYuyueEntity);//审核

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"审核数据","审核"+oldWuziYuyue+"数据,审核结果是"+(wuziYuyueEntity.getWuziYuyueYesnoTypes()==2?"通过":"拒绝"));
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<WuziYuyueEntity> oldWuziYuyueList =wuziYuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        wuziYuyueService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldWuziYuyueList.toString());
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
            List<WuziYuyueEntity> wuziYuyueList = new ArrayList<>();//上传的东西
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
                            WuziYuyueEntity wuziYuyueEntity = new WuziYuyueEntity();
//                            wuziYuyueEntity.setWuziYuyueUuidNumber(data.get(0));                    //报名编号 要改的
//                            wuziYuyueEntity.setWuziId(Integer.valueOf(data.get(0)));   //物资 要改的
//                            wuziYuyueEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            wuziYuyueEntity.setWuziYuyueText(data.get(0));                    //申报理由 要改的
//                            wuziYuyueEntity.setWuziYuyueYesnoTypes(Integer.valueOf(data.get(0)));   //报名状态 要改的
//                            wuziYuyueEntity.setWuziYuyueYesnoText(data.get(0));                    //审核回复 要改的
//                            wuziYuyueEntity.setWuziYuyueShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            wuziYuyueEntity.setInsertTime(date);//时间
//                            wuziYuyueEntity.setCreateTime(date);//时间
                            wuziYuyueList.add(wuziYuyueEntity);


                            //把要查询是否重复的字段放入map中
                                //报名编号
                                if(seachFields.containsKey("wuziYuyueUuidNumber")){
                                    List<String> wuziYuyueUuidNumber = seachFields.get("wuziYuyueUuidNumber");
                                    wuziYuyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> wuziYuyueUuidNumber = new ArrayList<>();
                                    wuziYuyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("wuziYuyueUuidNumber",wuziYuyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名编号
                        List<WuziYuyueEntity> wuziYuyueEntities_wuziYuyueUuidNumber = wuziYuyueService.selectList(new EntityWrapper<WuziYuyueEntity>().in("wuzi_yuyue_uuid_number", seachFields.get("wuziYuyueUuidNumber")));
                        if(wuziYuyueEntities_wuziYuyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WuziYuyueEntity s:wuziYuyueEntities_wuziYuyueUuidNumber){
                                repeatFields.add(s.getWuziYuyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        wuziYuyueService.insertBatch(wuziYuyueList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",wuziYuyueList.toString());
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

