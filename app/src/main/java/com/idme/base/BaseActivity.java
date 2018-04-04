package com.idme.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.idme.R;
import com.idme.common.Constants;
import com.idme.network.FailureResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pranav.dixit on 28/03/18.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @BindView(R.id.base_container)
    FrameLayout baseFrameLayout;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    BaseFragment currentFragment;

//    private int[] menuIds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        ButterKnife.bind(this);
        inflateView();
    }

    public void addFragment(BaseFragment fragment, String name) {
//        currentFragment = fragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(baseFrameLayout.getId(), fragment, name);
        ft.addToBackStack(name).commit();
    }

    public void replaceFragment(BaseFragment fragment, String name) {
//        currentFragment = fragment;
        try {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(baseFrameLayout.getId(), fragment, name);
            ft.addToBackStack(name).commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void initToolBar(String title, int[] menu) {
//        this.menuIds = menu;
//        if (title != null && !title.isEmpty())
//            toolbar.setTitle(title);
//        setSupportActionBar(toolbar);
//        invalidateOptionsMenu();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return true;
//
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (menu != null) {
//            for (int item : menuIds) {
//                MenuItem menuItem = menu.findItem(item);
//                menuItem.setVisible(true);
//            }
//        }
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (!currentFragment.handleToolbarItemClick(item))
//            switch (item.getItemId()) {
//                case R.id.home:
//                    AppLog.i("home is pressed");
//                    return true;
//                default:
//                    return super.onOptionsItemSelected(item);
//
//            }
//
//        return true;
//
//    }

//    public void hideToolbar() {
//        if (getSupportActionBar() != null)
//            getSupportActionBar().hide();
//    }
//
//    public void showToolbar() {
//        if (getSupportActionBar() != null)
//            getSupportActionBar().show();
//    }

    public abstract void inflateView();

    @Override
    public void showSnackbar(String msg) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showToastLong(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToastShort(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showError(FailureResponse failureResponse) {
        switch (failureResponse.getErrorMsg()){
            case Constants.ErrorCodes.NO_NETWORK:
                showSnackbar(failureResponse.getErrorMsg());
        }

    }
}
