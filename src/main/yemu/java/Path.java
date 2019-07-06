package main.yemu.java;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Path {
    public String getImgPath() throws IOException {
        try {
            Properties properties = new Properties();
            InputStream in = getClass().getClassLoader().getResource("filepath.properties").openStream();
            properties.load(in);
            String imgPath = (String) properties.get("imgpath");
            File file=new File(imgPath);
            if (!file.exists()){
                file.mkdirs();
            }
            return imgPath;
        }catch (Exception e){
            return "";
        }
    }
}
