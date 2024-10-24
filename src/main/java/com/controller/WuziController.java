
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
 * 物资
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/wuzi")
public class WuziController {
    private static final Logger logger = LoggerFactory.getLogger(WuziController.class);

    private static final String TABLE_NAME = "wuzi";

    @Autowired
    private WuziService wuziService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaozuorizhiService caozuorizhiService;//操作日志
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private WuziChuruInoutService wuziChuruInoutService;//出入库
    @Autowired
    private WuziChuruInoutListService wuziChuruInoutListService;//出入库详情
    @Autowired
    private WuziYuyueService wuziYuyueService;//物资申报
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
        params.put("wuziDeleteStart",1);params.put("wuziDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = wuziService.queryPage(params);

        //字典表数据转换
        List<WuziView> list =(List<WuziView>)page.getList();
        for(WuziView c:list){
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
        WuziEntity wuzi = wuziService.selectById(id);
        if(wuzi !=null){
            //entity转view
            WuziView view = new WuziView();
            BeanUtils.copyProperties( wuzi , view );//把实体数据重构到view中
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
    public R save(@RequestBody WuziEntity wuzi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,wuzi:{}",this.getClass().getName(),wuzi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<WuziEntity> queryWrapper = new EntityWrapper<WuziEntity>()
            .eq("wuzi_name", wuzi.getWuziName())
            .eq("wuzi_types", wuzi.getWuziTypes())
            .eq("wuzi_kucun_number", wuzi.getWuziKucunNumber())
            .eq("wuzi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WuziEntity wuziEntity = wuziService.selectOne(queryWrapper);
        if(wuziEntity==null){
            wuzi.setWuziDelete(1);
            wuzi.setInsertTime(new Date());
            wuzi.setCreateTime(new Date());
            wuziService.insert(wuzi);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",wuzi.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WuziEntity wuzi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,wuzi:{}",this.getClass().getName(),wuzi.toString());
        WuziEntity oldWuziEntity = wuziService.selectById(wuzi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(wuzi.getWuziPhoto()) || "null".equals(wuzi.getWuziPhoto())){
                wuzi.setWuziPhoto(null);
        }

            wuziService.updateById(wuzi);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(wuzi, oldWuziEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<WuziEntity> oldWuziList =wuziService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<WuziEntity> list = new ArrayList<>();
        for(Integer id:ids){
            WuziEntity wuziEntity = new WuziEntity();
            wuziEntity.setId(id);
            wuziEntity.setWuziDelete(2);
            list.add(wuziEntity);
        }
        if(list != null && list.size() >0){
            wuziService.updateBatchById(list);
        }

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldWuziList.toString());
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
            List<WuziEntity> wuziList = new ArrayList<>();//上传的东西
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
                            WuziEntity wuziEntity = new WuziEntity();
//                            wuziEntity.setWuziName(data.get(0));                    //物资名称 要改的
//                            wuziEntity.setWuziUuidNumber(data.get(0));                    //物资编号 要改的
//                            wuziEntity.setWuziPhoto("");//详情和图片
//                            wuziEntity.setWuziTypes(Integer.valueOf(data.get(0)));   //物资类型 要改的
//                            wuziEntity.setWuziKucunNumber(Integer.valueOf(data.get(0)));   //物资库存 要改的
//                            wuziEntity.setWuziContent("");//详情和图片
//                            wuziEntity.setWuziDelete(1);//逻辑删除字段
//                            wuziEntity.setInsertTime(date);//时间
//                            wuziEntity.setCreateTime(date);//时间
                            wuziList.add(wuziEntity);


                            //把要查询是否重复的字段放入map中
                                //物资编号
                                if(seachFields.containsKey("wuziUuidNumber")){
                                    List<String> wuziUuidNumber = seachFields.get("wuziUuidNumber");
                                    wuziUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> wuziUuidNumber = new ArrayList<>();
                                    wuziUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("wuziUuidNumber",wuziUuidNumber);
                                }
                        }

                        //查询是否重复
                         //物资编号
                        List<WuziEntity> wuziEntities_wuziUuidNumber = wuziService.selectList(new EntityWrapper<WuziEntity>().in("wuzi_uuid_number", seachFields.get("wuziUuidNumber")).eq("wuzi_delete", 1));
                        if(wuziEntities_wuziUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WuziEntity s:wuziEntities_wuziUuidNumber){
                                repeatFields.add(s.getWuziUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [物资编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        wuziService.insertBatch(wuziList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",wuziList.toString());
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

