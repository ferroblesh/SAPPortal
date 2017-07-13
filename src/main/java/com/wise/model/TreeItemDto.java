package com.wise.model;

import java.util.ArrayList;
import java.util.List;

public class TreeItemDto {
    private String task;
    private String text;
    private String url;
    private String iconCls;
    private Boolean expanded;
    private Boolean leaf;
    private String idgrurepo;
	private String idrepo;
	private String ubirepo;
	private String nombrerepo;
	private String activo; 

    private List<TreeItemDto> children = new ArrayList<TreeItemDto>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public List<TreeItemDto> getChildren() {
        return children;
    }
    
    public void addChild(TreeItemDto item) {
    	this.children.add(item);
    }

    @Override
    public String toString() {
        return "TreeItemDto{" +
                "task='" + task + '\'' +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", expanded=" + expanded +
                ", leaf=" + leaf +
                ", children=" + children +
                '}';
    }

	public String getIdgrurepo() {
		return idgrurepo;
	}

	public void setIdgrurepo(String idgrurepo) {
		this.idgrurepo = idgrurepo;
	}

	public String getIdrepo() {
		return idrepo;
	}

	public void setIdrepo(String idrepo) {
		this.idrepo = idrepo;
	}

	public String getUbirepo() {
		return ubirepo;
	}

	public void setUbirepo(String ubirepo) {
		this.ubirepo = ubirepo;
	}

	public String getNombrerepo() {
		return nombrerepo;
	}

	public void setNombrerepo(String nombrerepo) {
		this.nombrerepo = nombrerepo;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public void setChildren(List<TreeItemDto> children) {
		this.children = children;
	}
}
