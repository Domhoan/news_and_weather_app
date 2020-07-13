package com.example.news_and_weather_app;

public class Item {
    private String title;
    private String image;
    private String pubDate;
    private String link;

    public Item(String title , String image , String pubDate , String link) {
        this.title = title;
        this.image = image;
        this.pubDate = pubDate;
        this.link = link;
    }

    public Item() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
