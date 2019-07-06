package main.yemu.service;

import main.yemu.domain.Blog;

import java.util.List;

public interface BlogService {
    Blog getById(int id);
    List<Blog> getPage(int paegnum,int blognum);
    int getBlogCount();
    int getPageCount(int blognum);
    void delete(int id);
}
