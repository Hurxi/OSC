package com.example.myoschina;

/**
 * Created by 若希 on 2017/5/12.
 */

public class MessageEvent {
    public final String message;
    int type;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
