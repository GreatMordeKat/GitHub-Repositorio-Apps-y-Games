package com.blaisantkanovels.app.piojos;


public class Suscripcion {

    private String token;
    private String suscrito;
    private String valorado;
    private String error;

    public String getValorado() {
        return valorado;
    }

    public void setValorado(String valorado) {
        this.valorado = valorado;
    }

    public Suscripcion (String token, String suscrito, String valorado) {
        this.token = token;
        this.suscrito = suscrito;
        this.valorado = valorado;
    }

    public Suscripcion (String token) {
        this.token = token;
    }

    public Suscripcion () {}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSuscrito() {
        return suscrito;
    }

    public void setSuscrito(String suscrito) {
        this.suscrito = suscrito;
    }
}
