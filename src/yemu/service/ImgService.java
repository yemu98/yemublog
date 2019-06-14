package yemu.service;

import yemu.domain.Img;

public interface ImgService {
    Img getById(int id);
    Img getByName(String name);
    void addImg(Img img);
}
