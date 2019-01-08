package br.com.ladelicedomain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageEntity implements Serializable{

    @SerializedName("codigo")
    private int code;

    @SerializedName("caminhoImagem")
    private String link;

    public ImageEntity() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
