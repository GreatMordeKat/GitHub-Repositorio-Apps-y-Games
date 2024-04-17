package com.blairsaintklaus.daos;

public class Mensaje {

    private String text;
    private boolean sendByMe;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSendByMe() {
        return sendByMe;
    }

    public void setSendByMe(boolean sendByMe) {
        this.sendByMe = sendByMe;
    }
}
