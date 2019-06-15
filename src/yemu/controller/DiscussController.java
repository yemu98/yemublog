package yemu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yemu.domain.Discuss;
import yemu.service.DiscussService;

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
    public Object getById(HttpServletRequest request){
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
    public Object getByBlogId(HttpServletRequest request){
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
    public Object addDiscuss(HttpServletRequest request){
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


}
