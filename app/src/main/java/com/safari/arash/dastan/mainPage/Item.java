package com.safari.arash.dastan.mainPage;

public class Item {
    private String name;
    private String type;
    private String src;
    private String text;
    private String speaker;

    public Item(String name, String type, String src, String text, String direction) {
        this.name = name;
        this.type = type;
        this.src = src;
        this.text = text;
        this.speaker = direction;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSrc() {
        return src;
    }

    public String getText() {
        return text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
