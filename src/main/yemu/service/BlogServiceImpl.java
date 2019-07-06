package main.yemu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import main.yemu.domain.Blog;
import main.yemu.mapper.BlogMapper;
import main.yemu.mapper.DiscussMapper;

import javax.annotation.Resource;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Resource
    private DiscussMapper discussMapper;
    @Override
    public Blog getById(int id) {
        return blogMapper.getById(id);
    }

    @Override
    public List<Blog> getPage(int pagenum,int blognum) {
        if (blognum<=0){
            blognum=10;
        }
        if (pagenum<=0){
            pagenum=1;
        }
        if (pagenum>getPageCount(blognum)){
            pagenum=getPageCount(blognum);
        }
        return blogMapper.getPage((pagenum-1)*blognum,blognum);
    }

    @Override
    public int getBlogCount() {
        return blogMapper.getBlogCount();
    }

    @Override
    public int getPageCount(int blognum) {
        int count=blogMapper.getBlogCount();
        if (count%blognum==0){
            return count/blognum;
        }else
        {
            return count/blognum+1;
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        blogMapper.delete(id);
        discussMapper.deleteByBlog(id);
    }

}
