package com.fesine.auth.apacheshiro.model;

/**
 * @description: 权限实体
 * @author: Fesine
 * @createTime:2018/1/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/10
 */
public class Permission {
    private Integer pid;

    private String name;

    private String url;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
