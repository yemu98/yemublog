package main.yemu.mapper;

import main.yemu.domain.Img;

public interface ImgMapper {
    Img getById(int id);
    void addImg(Img img);
}
