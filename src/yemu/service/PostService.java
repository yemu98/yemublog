package yemu.service;

import yemu.domain.Blog;

public interface PostService {
    Blog getById(int id);
    void postBlog(Blog blog);
}
