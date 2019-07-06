package main.yemu.service;

import main.yemu.domain.Discuss;

import java.util.List;

public interface DiscussService {
    Discuss getById(int id);
    List<Discuss> getByBlogId(int blogid);
    void addDiscuss(Discuss discusss);
    int getDiscussCount();
    List<Discuss> getAll();
    void deleteByBlog(int blog_id);
    void deleteById(int id);
}
