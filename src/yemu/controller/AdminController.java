package yemu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping()
    public void toIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"/html/admin/index.html");
//        return "/html/admin/index.html";
    }
}

