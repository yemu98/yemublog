package yemu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yemu.domain.Blog;
import yemu.service.BlogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    @ResponseBody
    @RequestMapping(value = "/getById",produces = "application/json;charset=UTF-8")
    public Object getById(HttpServletRequest request){
        Blog blog=null;
        try{
            Integer id= Integer.valueOf(request.getParameter("id"));
            blog =blogService.getById(id);
            return JSONObject.toJSONString(blog);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString("error");
    }

    @ResponseBody
    @RequestMapping(value = "/getPage",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public Object getPage(HttpServletRequest request){
        List<Blog> blogs = null;
        try {
            Integer pagenum = Integer.valueOf(request.getParameter("pagenum"));
            Integer blognum = Integer.valueOf(request.getParameter("blognum"));
            blogs = blogService.getPage(pagenum, blognum);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(blogs);
    }

    @ResponseBody
    @RequestMapping(value = "/getPageCount",produces = "application/json;charset=UTF-8")
    public Object getPageCount(HttpServletRequest request){
        Integer blognum; //一页几个
        try{
            blognum=Integer.valueOf(request.getParameter("blognum"));
            if ((blognum == null || blognum == 0)) {
                blognum=10;
            }
        }catch (Exception e){
            e.printStackTrace();
            blognum=10;
        }
        return JSONObject.toJSONString(blogService.getPageCount(blognum));
    }

    @ResponseBody
    @RequestMapping(value = "/getBlogCount",produces = "application/json;charset=UTF-8")
    public Object getBlogCount(HttpServletRequest request){
        return JSONObject.toJSONString(blogService.getBlogCount());
    }
}
