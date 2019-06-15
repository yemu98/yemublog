package yemu.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

public class Discuss {
    @JSONField(ordinal = 1)
    private int id;
    @JSONField(ordinal = 2)
    private int blogid;
    @JSONField(ordinal = 3)
    private String name;
    @JSONField(ordinal = 4)
    private String content;
    @JSONField(ordinal = 5)
    private String email;
    @JSONField(ordinal = 6)
    private Timestamp create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlogid() {
        return blogid;
    }

    public void setBlogid(int blogid) {
        this.blogid = blogid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
