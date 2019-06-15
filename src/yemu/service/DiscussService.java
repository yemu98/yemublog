package yemu.service;

import yemu.domain.Discuss;

import java.util.List;

public interface DiscussService {
    Discuss getById(int id);
    List<Discuss> getByBlogId(int blogid);
    void addDiscuss(Discuss discusss);
}
