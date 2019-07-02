package yemu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yemu.Common.Response;
import yemu.domain.Blog;
import yemu.domain.User;
import yemu.service.BlogService;
import yemu.service.DiscussService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.TransactionManager;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;
    @Resource
    private DiscussService discussService;


    @RequestMapping(value = "/getById.do",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response<Blog> getById(HttpSession session, Integer id){
        Blog blog=null;
        try{
            blog =blogService.getById(id);
            if (blog!=null){
                return Response.createRespBySuccess(blog);
            }
            return Response.createRespByErrorMsg("没有此博客或已被删除！");
        }catch (Exception e){
            e.printStackTrace();
            return Response.createRespByErrorMsg("参数错误！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById.do",produces = "application/json;charset=UTF-8",params = "id")
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
    @RequestMapping(value = "/getPage.do",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
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
    @RequestMapping(value = "/getPageCount.do",produces = "application/json;charset=UTF-8")
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
    @RequestMapping(value = "/getBlogCount.do",produces = "application/json;charset=UTF-8")
    public Object getBlogCount(){
        return JSONObject.toJSONString(blogService.getBlogCount());
    }

    @ResponseBody
    @RequestMapping(value = "/getRecent.do",produces = "application/json;charset=UTF-8")
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
