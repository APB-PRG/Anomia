package com.example.anomia.Model;

public class Community {

    private String id;
    private String name;
    private String description;
    private Integer nbr_follower;

    public Community(String id, String name, String description, Integer nbr_follower) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nbr_follower = nbr_follower;
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
}
