package yemu.service;

import org.springframework.stereotype.Service;
import yemu.domain.Img;
import yemu.mapper.ImgMapper;

import javax.annotation.Resource;

@Service("imgService")
public class ImgServiceImpl implements ImgService {
    @Resource
    private ImgMapper imgMapper;
    @Override
    public Img getById(int id) {
        return imgMapper.getById(id);
    }

    @Override
    public Img getByName(String name) {
        return null;
    }

    @Override
    public void addImg(Img img) {
        imgMapper.addImg(img);
    }
}
