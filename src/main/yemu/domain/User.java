package main.yemu.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

public class User {
    @JSONField(ordinal = 1)
    private Integer id;
    @JSONField(ordinal = 2)
    private String account;
    @JSONField(ordinal = 3)
    private String name;
    @JSONField(ordinal = 4)
    private String password;
    @JSONField(ordinal = 5)
    private Timestamp create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
//        如果不为空去除多余空格
        this.account = account==null?null:account.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name==null?null:name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password==null?null:password.trim();
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
