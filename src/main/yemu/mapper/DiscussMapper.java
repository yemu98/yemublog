package main.yemu.mapper;

import main.yemu.domain.Discuss;

import java.util.List;

public interface DiscussMapper {
    Discuss getById(int id);
    List<Discuss> getByBlogId(int blogid);
    void addDiscuss(Discuss discuss);
    int getDiscussCount();
    List<Discuss> getAll();
    void deleteByBlog(int blogid);
    void deleteById(int id);
}
