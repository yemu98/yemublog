package main.yemu.service;

import main.yemu.domain.Blog;

public interface PostService {
    Blog getById(int id);
    void postBlog(Blog blog);
}
