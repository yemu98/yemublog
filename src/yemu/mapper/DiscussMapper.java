package yemu.mapper;

import yemu.domain.Discuss;

import java.util.List;

public interface DiscussMapper {
    Discuss getById(int id);
    List<Discuss> getByBlogId(int blogid);
    void addDiscuss(Discuss discuss);
}
