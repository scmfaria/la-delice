package br.com.ladelicedomain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

public class ResultContentArrayEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @Inject
    @SerializedName("content")
    List<MenuEntity> content;

    @Inject
    public ResultContentArrayEntity() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MenuEntity> getContent() {
        return content;
    }

    public void setContent(List<MenuEntity> content) {
        this.content = content;
    }
}
