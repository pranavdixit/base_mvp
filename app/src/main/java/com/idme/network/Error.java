package com.idme.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pranav.dixit on 29/03/18.
 */

public class Error {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("exception")
    @Expose
    private String exception;
    @SerializedName("data")
    @Expose
    private Object data;
    @SerializedName("messageId")
    @Expose
    private int messageId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
