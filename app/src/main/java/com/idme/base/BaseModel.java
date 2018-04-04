package com.idme.base;

import com.idme.Idme;
import com.idme.R;
import com.idme.common.Constants;
import com.idme.network.CommonResponseHandler;
import com.idme.network.FailureResponse;

import java.lang.ref.SoftReference;

/**
 * Created by pranav.dixit on 28/03/18.
 */

public abstract class BaseModel <T extends BaseModelListener> implements CommonResponseHandler {

    protected SoftReference<T> lisenter;

    public BaseModel(T lisenter){
        this.lisenter = new SoftReference<>(lisenter);
    }

    public void attachListener(T lisenter){
        this.lisenter = new SoftReference<>(lisenter);
    }
    protected T getListner(){
        return lisenter.get();
    }

    public void detachListener(){
        lisenter = null;
    }
    public abstract void init(String url);


    @Override
    public void onNetworkError() {
        FailureResponse f = new FailureResponse(Constants.ErrorCodes.NO_NETWORK, Idme.getInstance().getString(R.string.app_name));
        getListner().onFailure(f);
    }
}
