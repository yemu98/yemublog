package main.yemu.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import main.yemu.Common.ConsUtil;
import main.yemu.Common.Response;
import main.yemu.domain.User;
import main.yemu.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/ShowUser")
    public String toIndex(HttpServletRequest request, Model model){
        int userId=Integer.parseInt(request.getParameter("id"));
        User user=this.userService.getUserById(userId);
        model.addAttribute("user",user);
        return "ShowUser";
    }
    @ResponseBody
    @RequestMapping(value = "/addUser",produces = "application/json;charset=UTF-8")
    public Object addUser(HttpServletRequest request){
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        if (userService.getUserByAccount(account)!=null){
            return JSONObject.toJSONString("添加失败！账号重复");
        }
        if (userService.getUserByName(name)!=null){
            return JSONObject.toJSONString("添加失败！名字重复");
        }
        if (password.trim().equals("")){
            return JSONObject.toJSONString("添加失败！密码为空");
        }
        User user=new User();
        user.setAccount(account);
        user.setName(name);
        user.setPassword(password);
        userService.addUser(user);
        return JSONObject.toJSONString("ok");
    }
    @ResponseBody
    @RequestMapping(value = "/getUser.do",produces = "application/json;charset=UTF-8")
    public Object getUserById(Integer id,String name,String account){//获取用户信息
        return userService.getUser(id,name,account);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserByName.do", produces = "application/json;charset=UTF-8")
    public Response<List<User>> getUserByName(String name) {//根据姓名获取支持模糊查询
        try{
            if (name==null||name.equals("")){
                return Response.createRespByErrorMsg("参数出错！");
            }
            List<User> users=this.userService.getUserByName(name);
            return Response.createRespBySuccess(users);
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.createRespByErrorMsg("服务器内部错误！");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login.do",produces = "application/json;charset=UTF-8")
    public Response<User> login(String account, String password, Boolean remember, HttpSession session,HttpServletResponse resq){//登录
        if (account==null||account.equals("")||password==null||password.equals("")){
            return Response.createRespByErrorMsg("参数错误！");
        }
        if (remember==null){
            remember=false;
        }
        Response response=userService.login(account,password);
        session.setAttribute(ConsUtil.CUR_USER,response.getData());//用session保存登录信息
        if (response.getStatus()==0){//保存登录状态
            if (remember==true){
                User user= (User) response.getData();

                Cookie cookie=new Cookie("JSESSIONID", session.getId());//持久化session
                cookie.setPath("/");
                cookie.setMaxAge(7*24*60*60);//一周过期
                resq.addCookie(cookie);
            }else {
                //清空cookie
                Cookie cookie=new Cookie("user",null);
                cookie.setPath("/");
                cookie.setMaxAge(0);//设置过期
                resq.addCookie(cookie);

            }
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/getCount",produces = "application/json;charset=UTF-8")
    public Object getCount(){//获取总数
        Integer count=0;
        try {
            count=userService.getCount();
        }
        catch (Exception e){
            e.printStackTrace();
            count=0;
        }
        return JSONObject.toJSONString(count);
    }

    @ResponseBody
    @RequestMapping(value = "/getAll",produces = "application/json;charset=UTF-8")
    public Object getAll(){//获取所有用户信息
        List<User> users = null;
        try {
            users=userService.getAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(users);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById",produces = "application/json;charset=UTF-8",params = "id")
    public Object deleteById(HttpServletRequest request){
        String flag="error";
        try {
            if (request.getParameter("id")!=null&&request.getParameter("id").trim()!="") {
                Integer id= Integer.valueOf(request.getParameter("id"));
                userService.delete(id);//删除用户
                flag="success";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(flag);
    }

    @ResponseBody
    @RequestMapping(value = "/update",produces = "application/json;charset=UTF-8",params = {"id","account","name"})
    public Object update(HttpServletRequest request){
        User user = null;
        try{
            Integer id= Integer.valueOf(request.getParameter("id"));
            String acount=request.getParameter("account");
            String name=request.getParameter("name");
            user=userService.getUserById(id);
            user.setName(name);
            user.setAccount(acount);
            userService.update(user);
            user=userService.getUserById(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(user);
    }

    @RequestMapping(value = "/getCurUser.do",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Response<User> getCurUser(HttpServletResponse response,HttpSession session){
        User user= (User) session.getAttribute(ConsUtil.CUR_USER);
        if (user==null){
            return Response.createRespByErrorMsg("请先登录!");
        }
        return Response.createRespBySuccess(user);
    }
}
