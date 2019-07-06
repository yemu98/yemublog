package main.yemu.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class Img {
    @JSONField(ordinal =1)
    private int id;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(ordinal = 3)
    private String uri;
    @JSONField(ordinal = 4)
    private String annotation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
