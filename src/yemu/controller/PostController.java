package yemu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yemu.domain.Blog;
import yemu.service.PostService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;

    @ResponseBody
    @RequestMapping(value = "/getById",produces = "application/json;charset=UTF-8")
    public Object getById(HttpServletRequest request){
        try{
            Integer id= Integer.valueOf(request.getParameter("id"));
            Blog blog =postService.getById(id);
            return JSONObject.toJSONString(blog);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString("error");
    }

    @ResponseBody
    @RequestMapping(value = "/postBlog",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public Object postBlog(HttpServletRequest request, HttpServletResponse response){
        try{
            JSONObject jsonObject= JSON.parseObject(request.getParameter("json"));
            Blog blog= JSON.toJavaObject(jsonObject,Blog.class);
            if (blog.getContent().length()>20){
                blog.setExcerpt(blog.getContent().substring(0,20));
            }
            else{
                blog.setExcerpt(blog.getContent());
            }
            
            postService.postBlog(blog);
            return JSONObject.toJSONString(blog);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString("error");
    }
}
