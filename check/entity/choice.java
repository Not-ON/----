package com.yunteng.user.check.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class choice {
    private String choice;
    private String content;
    @JSONField(name="CHOICE",ordinal = 1)
    public String getChoice() {
        return choice;
    }
    @JSONField(name="CHOICE",ordinal = 1)
    public void setChoice(String choice) {
        this.choice = choice;
    }
    @JSONField(name="CONTENT",ordinal = 2)
    public String getContent() {
        return content;
    }
    @JSONField(name="CONTENT",ordinal = 2)
    public void setContent(String content) {
        this.content = content;
    }
}
