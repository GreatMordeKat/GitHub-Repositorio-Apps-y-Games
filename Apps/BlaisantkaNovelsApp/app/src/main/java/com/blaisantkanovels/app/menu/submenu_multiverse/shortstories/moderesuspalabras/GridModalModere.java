package com.blaisantkanovels.app.menu.submenu_multiverse.shortstories.moderesuspalabras;


public class GridModalModere {

    private int imageView;
    private String text;
    private String author;
    private String publicated;

    public String getPublicated() {
        return publicated;
    }

    public void setPublicated(String publicated) {
        this.publicated = publicated;
    }

    public GridModalModere(int imageView, String text, String author, String publicado) {
        this.imageView = imageView;
        this.text = text;
        this.author = author;
        publicated = publicado;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(String text) { this.author = author; }

    public String getAuthor() { return author; }
}
