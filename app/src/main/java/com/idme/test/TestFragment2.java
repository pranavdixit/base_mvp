package com.idme.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.idme.R;
import com.idme.base.BaseFragment;
import com.idme.common.AppLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by pranav.dixit on 04/04/18.
 */

public class TestFragment2 extends BaseFragment{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public boolean handleToolbarItemClick(MenuItem item) {
        AppLog.i("TestFragment2");
        return false;
    }

    @Override
    protected int getResId() {
        return R.layout.test_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar(toolbar,"Test fragment 2",new int []{R.id.done});
//        getBaseActivity().hideToolbar();
    }

    @OnClick(R.id.button)
    public void buttonPress(){
        getBaseActivity().addFragment(new TestFragment(),null);
    }
}
