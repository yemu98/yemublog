package main.yemu.service;

import org.springframework.stereotype.Service;
import main.yemu.domain.Blog;
import main.yemu.mapper.BlogMapper;

import javax.annotation.Resource;

@Service("postService")
public class PostServiceImpl implements PostService {
    @Resource
    private BlogMapper blogMapper;

    @Override
    public Blog getById(int id) {
        return blogMapper.getById(id);
    }

    @Override
    public void postBlog(Blog blog) {
        try{
            System.out.println(blog.getAuthor());
            blogMapper.addBlog(blog);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
