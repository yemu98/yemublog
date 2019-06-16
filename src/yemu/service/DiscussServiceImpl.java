package yemu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yemu.domain.Discuss;
import yemu.mapper.DiscussMapper;

import javax.annotation.Resource;
import java.util.List;

@Service("discussService")
public class DiscussServiceImpl implements DiscussService {
    @Resource
    private DiscussMapper discussMapper;

    @Override
    public Discuss getById(int id) {
        return discussMapper.getById(id);
    }

    @Override
    public List<Discuss> getByBlogId(int blogid) {
        return discussMapper.getByBlogId(blogid);
    }

    @Override
    public void addDiscuss(Discuss discusss) {
        discussMapper.addDiscuss(discusss);
    }

    @Override
    public int getDiscussCount() {
        return discussMapper.getDiscussCount();
    }

    @Override
    public List<Discuss> getAll() {
        return discussMapper.getAll();
    }
    @Transactional
    @Override
    public void deleteByBlog(int blog_id) {
        discussMapper.deleteByBlog(blog_id);
    }
    @Transactional
    @Override
    public void deleteById(int id) {
        discussMapper.deleteById(id);
    }
}
