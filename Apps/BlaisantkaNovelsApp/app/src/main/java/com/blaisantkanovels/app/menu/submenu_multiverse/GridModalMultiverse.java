package com.blaisantkanovels.app.menu.submenu_multiverse;

public class GridModalMultiverse {

    private int imageView;
    private String textProject;
    private String anoPubli;
    private String autoria;

    public GridModalMultiverse(int imageView, String textProyect, String anoPubli, String ultiPubli) {
        this.imageView = imageView;
        this.textProject = textProyect;
        this.anoPubli = anoPubli;
        this.autoria = ultiPubli;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getTextProyect() {
        return textProject;
    }

    public void setTextProyect(String textProyect) {
        this.textProject = textProyect;
    }

    public String getAnoPubli() {
        return anoPubli;
    }

    public void setAnoPubli(String anoPubli) {
        this.anoPubli = anoPubli;
    }

    public String getAutoria() {
        return autoria;
    }

    public void setAutoria(String autoria) {
        this.autoria = autoria;
    }
}
