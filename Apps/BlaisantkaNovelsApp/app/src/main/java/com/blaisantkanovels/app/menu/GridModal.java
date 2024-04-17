package com.blaisantkanovels.app.menu;

public class GridModal {

    private int imageView;
    private String textProject;
    private String anoPubli;
    private String ultiPubli;

    public GridModal (int imageView, String textProyect, String anoPubli, String ultiPubli) {
        this.imageView = imageView;
        this.textProject = textProyect;
        this.anoPubli = anoPubli;
        this.ultiPubli = ultiPubli;
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

    public String getUltiPubli() {
        return ultiPubli;
    }

    public void setUltiPubli(String ultiPubli) {
        this.ultiPubli = ultiPubli;
    }
}
