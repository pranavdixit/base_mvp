package com.idme.network;

import com.google.gson.Gson;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pranav.dixit on 29/03/18.
 */

public abstract class NetworkResponse<T> implements Callback<T> {
    private CommonResponseHandler handler;

    public NetworkResponse(CommonResponseHandler handler) {
        this.handler = handler;
    }

    public abstract void onSuccess(T body);

    protected void error(Throwable t) {
        if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
            handler.onNetworkError();
        }
        onError(t);
    }

    public abstract void onFailure(int code, FailureResponse failureResponse);

    public abstract void onError(Throwable t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else {
            onFailure(response.code(), getFailureErrorBody(call.request().url().url().getFile(), response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        error(t);
    }

    protected final FailureResponse getFailureErrorBody(String url, ResponseBody responseBody) {
        FailureResponse failureResponse;
        ErrorBody errorBody = new ErrorBody();
        try {
            Gson gson = new Gson();
            errorBody = gson.fromJson(responseBody.string(), ErrorBody.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(errorBody!=null && errorBody.getError() !=null)
            failureResponse = new FailureResponse(String.valueOf(errorBody.getError().getCode()),errorBody.getError().getMessage());
        else
            failureResponse = new FailureResponse("","");
        return failureResponse;
    }
}
