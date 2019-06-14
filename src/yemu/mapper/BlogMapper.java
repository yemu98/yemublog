package yemu.mapper;

import org.apache.ibatis.annotations.Param;
import yemu.domain.Blog;

import java.util.List;

public interface BlogMapper {
    Blog getById(int id);
    void addBlog(Blog blog);
    List<Blog> getPage(@Param("pagenum") int pagenum, @Param("blognum") int blognum);
    int getBlogCount();
}
