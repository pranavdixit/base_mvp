package com.idme.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.idme.R;
import com.idme.common.AppLog;
import com.idme.network.FailureResponse;

import butterknife.ButterKnife;

/**
 * Created by pranav.dixit on 28/03/18.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment implements BaseView{

    BaseActivity baseActivity;
    private int[] menuIds;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity){
            baseActivity = (BaseActivity) context;
        }else{
            try {
                throw new Exception("Activity should extend base Activity");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        getBaseActivity().showToolbar();
        setHasOptionsMenu(true);
    }

    public abstract boolean handleToolbarItemClick(MenuItem item);

    public void initToolBar(Toolbar toolbar, String title, int[] menu) {
        this.menuIds = menu;
        if (title != null && !title.isEmpty())
            toolbar.setTitle(title);
        getBaseActivity().setSupportActionBar(toolbar);
        getBaseActivity().invalidateOptionsMenu();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return true;
//
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(hasOptionsMenu()){
            inflater.inflate(R.menu.menu_main, menu);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.setGroupVisible(R.id.gp_menu, false);
        if (menuIds == null) {
            return;
        }
//        menu.clear();
        if (menu != null) {
            for (int item : menuIds) {
                MenuItem menuItem = menu.findItem(item);
                menuItem.setVisible(true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    AppLog.i("home is pressed");
                    return true;
                case R.id.done:
                    AppLog.i("done is pressed");
                    return true;
                default:
                    return super.onOptionsItemSelected(item);

            }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getResId(),null);
        ButterKnife.bind(this,view);
        return view;
    }

    protected abstract int getResId();

    protected BaseActivity getBaseActivity(){
        return baseActivity;
    }

    @Override
    public void showSnackbar(String msg) {
        baseActivity.showSnackbar(msg);
    }

    @Override
    public void showToastLong(String msg) {
        baseActivity.showToastLong(msg);
    }

    @Override
    public void showToastShort(String msg) {
        baseActivity.showToastShort(msg);
    }

    @Override
    public void showProgressDialog() {
        baseActivity.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        baseActivity.hideProgressDialog();
    }

    @Override
    public void showError(FailureResponse failureResponse) {
        baseActivity.showError(failureResponse);
    }
}
