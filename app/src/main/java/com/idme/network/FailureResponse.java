package com.idme.network;

/**
 * Created by pranav.dixit on 29/03/18.
 */

public class FailureResponse {

    private final String errorMsg;
    private final String errorCode;

    public FailureResponse(String errorMsg, String errorCode){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
