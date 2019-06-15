package yemu.service;

import org.springframework.stereotype.Service;
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
}
