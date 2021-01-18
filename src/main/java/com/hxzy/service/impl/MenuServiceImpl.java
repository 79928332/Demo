package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.hxzy.dto.MenuSearchDTO;
import com.hxzy.dto.PageSearch;
import com.hxzy.entity.Menu;
import com.hxzy.mapper.MenuMapper;
import com.hxzy.service.MenuService;
import com.hxzy.vo.MenuTreeTableVO;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MenuServiceImpl  extends BaseServiceImpl<Menu,Integer>  implements MenuService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private MenuMapper  menuMapper;

    @Autowired
    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
        super.setMyBatisBaseDao(menuMapper);
    }


    //重写查询方法，不需要分页
    @Override
    public Result search(PageSearch pageSearch) {

        //先判断redis中是否存在值(加锁)

        MenuSearchDTO menuSearchDTO = (MenuSearchDTO) pageSearch;
        String result = this.stringRedisTemplate.opsForValue().get(menuSearchDTO.getRedisKey());
        if (StringUtils.isNotBlank(result)) {
            List<MenuTreeTableVO> treeTableList = JSONArray.parseArray(result, MenuTreeTableVO.class);
            return Result.createResult_Data(ResultCode.OK, treeTableList);
        }


        //查询所有数据
        List<Menu> allMenu = this.menuMapper.search(pageSearch);
        //返回的结果
        List<MenuTreeTableVO> treeTableList = new ArrayList<MenuTreeTableVO>();

        // jdk8特性 (日期 LocalDate  LocalTime ，   lamdba表达式[定义方法  ，  集合增强])
        allMenu.stream().filter((p) -> (p.getId() == null || p.getUpId() == 0)).forEach((p) -> {
            MenuTreeTableVO root = new MenuTreeTableVO();
            //使用spring自带的工具，帮你解决这个问题
            BeanUtils.copyProperties(p, root);
            // 继续查询子节点
            loadChildren(root, allMenu);

            treeTableList.add(root);
        });


        //加锁把数据库值存放到redis中
        synchronized (this) {
            String jsonArray = JSONArray.toJSONString(treeTableList);
            //stringRedisTemplate.opsForValue().set("test", "100",60*10,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
            this.stringRedisTemplate.opsForValue().set(((MenuSearchDTO) pageSearch).getRedisKey(), jsonArray, ((MenuSearchDTO) pageSearch).getRedisExpireTime(), TimeUnit.SECONDS);
        }


        return Result.createResult_Data(ResultCode.OK, treeTableList);
    }


    /**
     *  查询子节点
     * @param parent 父节点
     * @param allMenu  所有数据库的值
     */
    private void loadChildren( MenuTreeTableVO   parent, List<Menu>  allMenu){
        List<MenuTreeTableVO>  children=new ArrayList<MenuTreeTableVO>();

        //计算哪些才算是 parent下面的子节点
        allMenu.stream().filter( (p) -> p.getUpId().intValue()== parent.getId().intValue()).forEach( (s) -> {
            MenuTreeTableVO   treeVO=new MenuTreeTableVO();
            //使用spring自带的工具，帮你解决这个问题
            BeanUtils.copyProperties(s,  treeVO );
            //再继续遍历treeVO的子节点 (递归)
            loadChildren( treeVO, allMenu);

            children.add(treeVO);
        });

        //有子节点
        if(children.size() >0){
              parent.setChildren( children);
        }

    }

    @Override
    public Result existsValue(MenuSearchDTO menuSearchDTO) {
        if(StringUtils.isNotBlank( menuSearchDTO.getName())){
            return existsName(menuSearchDTO);
        }else {
            return existsPath(menuSearchDTO);
        }
    }

    private  Result existsName(MenuSearchDTO menuSearchDTO){
        int count=0;
        //新增
        if(menuSearchDTO.getId()==0){
            count=this.menuMapper.existsValue(menuSearchDTO);
        }else{
            Menu menu=this.menuMapper.selectByPrimaryKey(menuSearchDTO.getId());
            if(menu!=null){
                if(!menu.getName() .equals(  menuSearchDTO.getName())){
                    count=this.menuMapper.existsValue(menuSearchDTO);
                }
            }
        }

        Result  result=null;
        //返回给前端  count==0 代表允许使用 ,否则已被占用
        if(count==0){
            result=Result.createResult(ResultCode.VALUE_IS_CAN_BE_USE);
        }else{
            result=Result.createResult(ResultCode.VALUE_IS_ALREADY_USE);
        }

        return result;
    }
    private  Result existsPath(MenuSearchDTO menuSearchDTO){
        int count=0;
        //新增
        if(menuSearchDTO.getId()==0){
            count=this.menuMapper.existsValue(menuSearchDTO);
        }else{
            Menu menu=this.menuMapper.selectByPrimaryKey(menuSearchDTO.getId());
            if(menu!=null){
                if(!menu.getPath() .equals(  menuSearchDTO.getPath())){
                    count=this.menuMapper.existsValue(menuSearchDTO);
                }
            }
        }

        Result  result=null;
        //返回给前端  count==0 代表允许使用 ,否则已被占用
        if(count==0){
            result=Result.createResult(ResultCode.VALUE_IS_CAN_BE_USE);
        }else{
            result=Result.createResult(ResultCode.VALUE_IS_ALREADY_USE);
        }

        return result;
    }
}
