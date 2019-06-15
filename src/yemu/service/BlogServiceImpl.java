package yemu.service;

import org.springframework.stereotype.Service;
import yemu.domain.Blog;
import yemu.mapper.BlogMapper;

import javax.annotation.Resource;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;
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

}
