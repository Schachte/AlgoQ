package com.algoq.algoq.models;

public class POTDResources {
    private String name;
    private String link;

    public POTDResources(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public POTDResources() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
