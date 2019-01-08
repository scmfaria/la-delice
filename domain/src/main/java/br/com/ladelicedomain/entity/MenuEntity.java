package br.com.ladelicedomain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

public class MenuEntity implements Serializable{

    @SerializedName("codigo")
    private int code;

    @SerializedName("titulo")
    private String titleMenu;

    @SerializedName("descricao")
    private String descriptionMenu;

    @SerializedName("imagemDescricao")
    private String imageDescription;

    @SerializedName("imagens")
    List<ImageEntity> imageEntities;

    @Inject
    public MenuEntity() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitleMenu() {
        return titleMenu;
    }

    public void setTitleMenu(String titleMenu) {
        this.titleMenu = titleMenu;
    }

    public String getDescriptionMenu() {
        return descriptionMenu;
    }

    public void setDescriptionMenu(String descriptionMenu) {
        this.descriptionMenu = descriptionMenu;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    @Inject
    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    @Inject
    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }
}
