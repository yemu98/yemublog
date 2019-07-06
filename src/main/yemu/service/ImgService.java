package main.yemu.service;

import main.yemu.domain.Img;

public interface ImgService {
    Img getById(int id);
    Img getByName(String name);
    void addImg(Img img);
}
