package com.idme.base;

import com.idme.network.FailureResponse;

/**
 * Created by pranav.dixit on 29/03/18.
 */

public interface BaseModelListener {

    /**
     * This method is called when model is not able to fetch data
     * @param failureResponse
     */
    void onFailure(FailureResponse failureResponse);
}
