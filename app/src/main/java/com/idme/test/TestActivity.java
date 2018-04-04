package com.idme.test;

import com.idme.base.BaseActivity;

/**
 * Created by pranav.dixit on 03/04/18.
 */

public class TestActivity extends BaseActivity {

    @Override
    public void inflateView() {
        addFragment(new TestFragment(),TestFragment.class.getName());
    }
}
