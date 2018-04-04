package com.idme.base;

import com.idme.network.FailureResponse;

import java.lang.ref.SoftReference;

/**
 * Created by pranav.dixit on 28/03/18.
 */

public abstract class BasePresenter <T extends BaseView> implements BaseModelListener{

    private SoftReference<T> view;

    public BasePresenter(T view) {
        this.view = new SoftReference<T>(view);
        setModel();
    }

    protected void attachView(T view) {
        this.view = new SoftReference<T>(view);
        setModel();
    }

    public T getView() {
        if (view != null)
            return view.get();
        else
            return null;
    }

    public void detachView() {
        view = null;
        destroy();
    }

    @Override
    public void onFailure(FailureResponse failureResponse) {
        getView().hideProgressDialog();
        getView().showError(failureResponse);
    }

    public abstract void initView(String url);

    public abstract void setModel();

    public abstract void destroy();
}
