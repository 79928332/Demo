package com.hxzy.controller;
import com.hxzy.dto.BaseRepoDTO;
import com.hxzy.entity.BaseRepo;
import com.hxzy.service.BaseRepoService;
import com.hxzy.vo.Result;
import com.hxzy.vo.ResultCode;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;
import java.util.Date;

@RequestMapping(value = "/api/baserepo")
@Controller
public class BaseRepoController {
    @Autowired
    private BaseRepoService baseRepoService;
    @ResponseBody
    @GetMapping(value = "/search")
    public Result search(BaseRepoDTO baseRepoDTO){
        return  this.baseRepoService.search(baseRepoDTO);
    }
   @ResponseBody
    @PostMapping(value = "/insert")
    public  Result insert( @Valid BaseRepo baseRepo){
        Result result=null;
        baseRepo.setUpdatime(new Date());
       boolean isSuccessed=this.baseRepoService.insert(baseRepo);
       if(isSuccessed){
           result= Result.createResult(ResultCode.DB_SAVE_OK);
       }else{
           result= Result.createResult(ResultCode.DB_SAVE_ERROR);
       }
       return result;
   }

    /**
     * 修改操作
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/update")
    public Result update( @Valid BaseRepo baseRepo){
        Result result=null;
        //当前登录用户名
       baseRepo.setUpdater("admin");
       baseRepo.setUpdatime(new Date());
        boolean isSuccessed=this.baseRepoService.updateByPrimaryKeySelective(baseRepo);
        if(isSuccessed){
            result= Result.createResult(ResultCode.DB_SAVE_OK);
        }else{
            result= Result.createResult(ResultCode.DB_SAVE_ERROR);
        }
        return result;
    }
    @ResponseBody
    @GetMapping(value = "/exist")
    public Result existValue(BaseRepoDTO baseRepoDTO){
        return this.baseRepoService.existsValue(baseRepoDTO);
    }

    @ResponseBody
    @GetMapping(value = "/searchallname")
    public Result searchAllName(){
        return this.baseRepoService.searchAllName();
    }
}
