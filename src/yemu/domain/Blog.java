package yemu.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.sql.Timestamp;
@JSONType(serialzeFeatures = {SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty})
public class Blog {
    @JSONField(ordinal = 1)
    private int id;
    @JSONField(ordinal = 2)
    private String author;
    @JSONField(ordinal = 3)
    private String title;
    @JSONField(ordinal = 4)
    private String content;
    @JSONField(ordinal = 5)
    private String excerpt;
    @JSONField(ordinal = 6)
    private String category;
    @JSONField(ordinal = 7)
    private String status;
    @JSONField(ordinal = 8)
    private Timestamp post_time;
    @JSONField(ordinal = 9)
    private Timestamp modified_time;
    @JSONField(ordinal = 10)
    private String post_link;
    @JSONField(ordinal = 11)
    private String post_link_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getPost_time() {
        return post_time;
    }

    public void setPost_time(Timestamp post_time) {
        this.post_time = post_time;
    }

    public Timestamp getModified_time() {
        return modified_time;
    }

    public void setModified_time(Timestamp modified_time) {
        this.modified_time = modified_time;
    }

    public String getPost_link() {
        return post_link;
    }

    public void setPost_link(String post_link) {
        this.post_link = post_link;
    }

    public String getPost_link_name() {
        return post_link_name;
    }

    public void setPost_link_name(String post_link_name) {
        this.post_link_name = post_link_name;
    }
}
