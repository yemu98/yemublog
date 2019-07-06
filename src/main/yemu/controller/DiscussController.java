package main.yemu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import main.yemu.domain.Discuss;
import main.yemu.service.DiscussService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/discuss")
public class DiscussController {
    @Resource
    private DiscussService discussService;

    @ResponseBody
    @RequestMapping(value = "/getById",params = "id",produces = "application/json;charset=UTF-8")
    public Object getById(HttpServletRequest request){//用评论id获取
        Discuss discuss = null;
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            discuss=discussService.getById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(discuss);
    }

    @ResponseBody
    @RequestMapping(value = "/getById",params = "blogid",produces = "application/json;charset=UTF-8")
    public Object getByBlogId(HttpServletRequest request){//根据blogid获取
        List<Discuss> discusses = null;
        try {
            Integer blogid = Integer.valueOf(request.getParameter("blogid"));
            discusses=discussService.getByBlogId(blogid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(discusses);
    }

    @ResponseBody
    @RequestMapping(value = "/addDiscuss",produces = "application/json;charset=UTF-8")
    public Object addDiscuss(HttpServletRequest request){//添加评论
        try{
            String json=request.getParameter("discuss");
            JSONObject jsonObject=JSON.parseObject(json);
            Discuss discuss=JSON.toJavaObject(jsonObject,Discuss.class);
            discussService.addDiscuss(discuss);
            return JSONObject.toJSONString(discuss);
        }
        catch (Exception e){
        e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/getCount",produces = "application/json;charset=UTF-8")
    public Object getDiscussCount(){//获取评论总数
        Integer count=0;
        try {
            count=discussService.getDiscussCount();
        }
        catch (Exception e){
            e.printStackTrace();
            count=0;
        }
        return JSONObject.toJSONString(count);
    }

    @ResponseBody
    @RequestMapping(value = "/getAll",produces = "application/json;charset=UTF-8")
    public Object getDiscuss(){//获取所有评论
        List<Discuss> discusses = null;
        try {
            discusses=discussService.getAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(discusses);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById",produces = "application/json;charset=UTF-8",params = "id")
    public Object deleteById(HttpServletRequest request){
        String flag="error";
        try {
            if (request.getParameter("id")!=null&&request.getParameter("id").trim()!="") {
                Integer id= Integer.valueOf(request.getParameter("id"));
                discussService.deleteById(id);//删除评论
                flag="success";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(flag);
    }
}
