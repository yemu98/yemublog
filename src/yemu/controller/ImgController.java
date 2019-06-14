package yemu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import yemu.domain.Img;
import yemu.java.Path;
import yemu.service.ImgService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

@Controller
@RequestMapping("/img")
public class ImgController {
    @Resource
    private ImgService imgService;
    @ResponseBody
    @RequestMapping(value = "/getImg",produces = "application/json;charset=UTF-8",params = "id")
    public Object getById(HttpServletRequest request){
        try{
//            String id=request.getParameter("id");
//            id=id.trim();
            Integer id= Integer.valueOf(request.getParameter("id"));
            if (id!=null){
                Img img=imgService.getById(id);
                return JSONObject.toJSONString(img);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @ResponseBody
    @RequestMapping(value = "/upload",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public Object upload(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()){
            try{
                String filename=file.getOriginalFilename();
                //生成uuid作为文件名称
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                //获得文件后缀名
                String contentType=file.getContentType();
                String suffixName=contentType.substring(contentType.indexOf("/")+1);

//                "/home/yemu/IdeaProjects"
                String imgpath= new Path().getImgPath();//文件保存路径

                String filepath=imgpath+ File.separator+uuid+"."+suffixName;//拼接完整文件保存地址
                File dir=new File(filepath);
                if (!dir.exists()){
                    dir.mkdirs();
                }
                file.transferTo(new File(filepath));
                Img img=new Img();
                img.setName(uuid);
                img.setAnnotation(filename);
                img.setUri(filepath);
                imgService.addImg(img);
                return JSONObject.toJSONString(img);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping("/showImg")
    public void showImg(HttpServletRequest request,HttpServletResponse response){//以流的方式返回图片
        try{
            Integer id= Integer.valueOf(request.getParameter("id"));
            Img img = imgService.getById(id);
            FileInputStream in;
            response.setContentType("application/octet-stream;charset=UTF-8");
            in=new FileInputStream(img.getUri());//获取文件真实地址
            int i=in.available();
            byte[] data=new byte[i];
            in.read(data);
            in.close();
            OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
