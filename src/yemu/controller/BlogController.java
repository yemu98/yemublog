package yemu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yemu.domain.Blog;
import yemu.service.BlogService;
import yemu.service.DiscussService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.TransactionManager;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;
    @Resource
    private DiscussService discussService;

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
    @RequestMapping(value = "/deleteById",produces = "application/json;charset=UTF-8",params = "id")
    public Object deleteById(HttpServletRequest request){
        String flag="error";
        try {
            if (request.getParameter("id")!=null&&request.getParameter("id").trim()!="") {
                Integer id= Integer.valueOf(request.getParameter("id"));
                blogService.delete(id);//删除blog
                flag="success";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(flag);
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
    public Object getBlogCount(){
        return JSONObject.toJSONString(blogService.getBlogCount());
    }

    @ResponseBody
    @RequestMapping(value = "/getRecent",produces = "application/json;charset=UTF-8")
    public Object getRecent(HttpServletRequest request){
        List<Blog> blogs = null;
        try {
            if (request.getParameter("num")!=null&&request.getParameter("num").trim()!="") {
                Integer num = Integer.valueOf(request.getParameter("num"));
                blogs = blogService.getPage(1, num);
            }
            else {
                blogs = blogService.getPage(1, 10);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(blogs);
    }
}
