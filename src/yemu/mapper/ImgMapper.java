package yemu.mapper;

import yemu.domain.Img;

public interface ImgMapper {
    Img getById(int id);
    void addImg(Img img);
}
