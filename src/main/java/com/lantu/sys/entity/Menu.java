package com.lantu.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author laocai
 * @since 2023-03-28
 */
@TableName("x_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer menuId;

    private String compoent;

    private String path;

    private String redirect;

    private String name;

    private String title;

    private String icon;

    private Integer parentId;

    private String isLeaf;

    private Boolean hidden;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public String getCompoent() {
        return compoent;
    }

    public void setCompoent(String compoent) {
        this.compoent = compoent;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }
    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "menuId=" + menuId +
            ", compoent=" + compoent +
            ", path=" + path +
            ", redirect=" + redirect +
            ", name=" + name +
            ", title=" + title +
            ", icon=" + icon +
            ", parentId=" + parentId +
            ", isLeaf=" + isLeaf +
            ", hidden=" + hidden +
        "}";
    }
}
