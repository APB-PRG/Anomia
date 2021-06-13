package com.example.anomia.Model;

import android.view.View;

public class Community {

    private String id;
    private String name;
    private String description;
    private Integer nbr_follower;
    private String category1;
    private String category2;

    public Community(String id, String name, String description, String category1, String category2) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nbr_follower = nbr_follower;
        this.category1 = category1;
        this.category2 = category2;
    }

    public Community(String name, String description, String category1, String category2) {
        this.name = name;
        this.description = description;
        this.category1 = category1;
        this.category2 = category2;
    }

    public Community() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNbr_follower() {
        return nbr_follower;
    }

    public void setNbr_follower(Integer nbr_follower) {
        this.nbr_follower = nbr_follower;
    }

    public String getcategory1() {
        return category1;
    }

    public void setcategory1(String category1) {
        this.category1 = category1;
    }

    public String getcategory2() {
        return category2;
    }

    public void setcategory2(String category2) {
        this.category2 = category2;
    }
}
