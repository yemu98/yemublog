package main.yemu.controller;

import com.alibaba.fastjson.JSONObject;
import main.yemu.Common.Response;
import main.yemu.domain.Blog;
import main.yemu.service.BlogService;
import main.yemu.service.DiscussService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;
    @Resource
    private DiscussService discussService;


    @RequestMapping(value = "/getById.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response<Blog> getById(HttpSession session, Integer id) {
        Blog blog = null;
        try {
            blog = blogService.getById(id);
            if (blog != null) {
                return Response.createRespBySuccess(blog);
            }
            return Response.createRespByErrorMsg("没有此博客或已被删除！");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.createRespByErrorMsg("参数错误！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById.do", produces = "application/json;charset=UTF-8")
    public Object deleteById(HttpSession session, Integer id) {
        try {
            blogService.delete(id);//删除blog
            return Response.createRespBySuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.createRespByErrorMsg("删除失败！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getPage.do", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Object getPage(HttpServletRequest request,
                          @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                          @RequestParam(value ="pageSize",defaultValue="10") int pageSize) {
        try {
            return Response.createRespBySuccess(blogService.getPage(pageNum,pageSize));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.createRespByErrorMsg("查询出错!");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getPageCount.do", produces = "application/json;charset=UTF-8")
    public Object getPageCount(HttpServletRequest request, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        try {
            return Response.createRespBySuccess(blogService.getPageCount(pageSize));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.createRespByErrorMsg("参数错误!");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBlogCount.do", produces = "application/json;charset=UTF-8")
    public Object getBlogCount() {
        return Response.createRespBySuccess(blogService.getBlogCount());
    }

    @ResponseBody
    @RequestMapping(value = "/getRecent.do", produces = "application/json;charset=UTF-8")
    public Response<List<Blog>> getRecent(HttpServletRequest request) {
        try {
            return Response.createRespBySuccess(blogService.getPage(1, 10));
        } catch (Exception e) {
            e.printStackTrace();
            return Response.createRespByErrorMsg("参数错误!");
        }
    }
}
