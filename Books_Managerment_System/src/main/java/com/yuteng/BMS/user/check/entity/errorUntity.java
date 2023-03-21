package com.yuteng.BMS.user.check.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class errorUntity {
    private String message;
    @JSONField(name="MESSAGE",ordinal = 1)
    public String getMessage() {
        return message;
    }
    @JSONField(name="MESSAGE",ordinal = 1)
    public void setMessage(String message) {
        this.message = message;
    }
}
