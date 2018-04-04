package com.idme.base;

import com.idme.network.FailureResponse;

/**
 * Created by pranav.dixit on 28/03/18.
 */

public interface BaseView {

    void showSnackbar(String msg);

    void showToastLong(String msg);

    void showToastShort(String msg);

    void showProgressDialog();

    void hideProgressDialog();

    /**
     * perform UI action on data failure response
     * @param failureResponse
     */
    void showError(FailureResponse failureResponse);
}
