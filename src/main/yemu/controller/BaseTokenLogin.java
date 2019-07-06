package main.yemu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import main.yemu.Common.Response;
import main.yemu.Common.TokenUtil;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/token")
@Controller
public class BaseTokenLogin {
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Response<String> login(String account,String password){
        String token= TokenUtil.sign(account,password);
        return Response.createRespBySuccess(token);
    }

    @RequestMapping(value = "/verfiy.do")
    @ResponseBody
    public Response<Boolean> verfiy(HttpServletRequest request){
        String token=request.getHeader("token");
        return Response.createRespBySuccess(TokenUtil.verify(token));
    }
}
