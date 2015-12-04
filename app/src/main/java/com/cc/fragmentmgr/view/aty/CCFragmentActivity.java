package com.cc.fragmentmgr.view.aty;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.cc.fragmentmgr.model.FragmentParam;
import com.cc.fragmentmgr.model.FragmentType;
import com.cc.fragmentmgr.view.fragment.CCFragment;
import com.cc.fragmentmgr.tools.CLog;

/**
 * Created by androllen on 2015/9/18.
 */
public abstract class CCFragmentActivity extends FragmentActivity {

    private static final String TAG = "CCFragmentActivity";

    public CCFragmentActivity() {

    }

    public static boolean DEBUG = true;
    protected CCFragment mCurrentFragment;
    private boolean mCloseWarned = false;


    protected abstract String getCloseWarning();

    protected abstract int getFragmentContainerId();

    public void pushFragmentToBackStack(Class<?> cls, Object data, FragmentType state) {
        FragmentParam param = new FragmentParam();
        param.cls = cls;
        param.data = data;
        param.state = state;
        goToThisFragment(param);
    }

    protected String getFragmentTag(FragmentParam param) {
        StringBuilder sb = new StringBuilder(param.cls.toString());
        return sb.toString();
    }

    private void goToThisFragment(FragmentParam param) {
        int containerId = getFragmentContainerId();
        Class cls = param.cls;
        if (cls == null)
            return;
        try {
            String fragmentTag = getFragmentTag(param);
            FragmentManager fm = getSupportFragmentManager();
            if (DEBUG)
                CLog.d("cube-fragment", "before operate, stack entry count: %s", new Object[]{Integer.valueOf(fm.getBackStackEntryCount())});

            CCFragment fragment = (CCFragment) fm.findFragmentByTag(fragmentTag);
            if (fragment == null)
                fragment = (CCFragment) cls.newInstance();

            //先释放当前的fragment
            if ((this.mCurrentFragment != null) && (this.mCurrentFragment != fragment)) {
                this.mCurrentFragment.onLeave();
            }
            //然后在把新的fragment加入
            fragment.onEnter(param.data);

            FragmentTransaction ft = fm.beginTransaction();
            if (fragment.isAdded()) {
                if (DEBUG) {
                    CLog.d("cube-fragment", "%s has been added, will be shown again.", new Object[]{fragmentTag});
                }

                ft.show(fragment);
            } else {
                if (DEBUG) {
                    CLog.d("cube-fragment", "%s is added.", new Object[]{fragmentTag});
                }
                FragmentType code = param.state;

                switch (code) {
                    case Add:
                        ft.add(containerId, fragment, fragmentTag);
                        break;
                    case Replace:
                        ft.replace(containerId, fragment, fragmentTag);
                        break;
                    case Remove:
                        ft.remove(fragment);
                        break;
                    case Hide:
                        ft.hide(fragment);
                        break;
                }
            }
            this.mCurrentFragment = fragment;

            ft.addToBackStack(fragmentTag);
            ft.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.mCloseWarned = false;
    }

    public void goToFragment(Class<?> cls, Object data) {
        if (cls == null)
            return;
        FragmentManager fm = getSupportFragmentManager();

        CCFragment fragment = (CCFragment) fm.findFragmentByTag(cls.toString());
        if (fragment != null) {
            this.mCurrentFragment = fragment;
            fragment.onBackWithData(data);
        }
        fm.popBackStackImmediate(cls.toString(), 0);
    }

    public void popTopFragment(Object data) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
        if ((tryToUpdateCurrentAfterPop()) && (this.mCurrentFragment != null))
            this.mCurrentFragment.onBackWithData(data);
    }

    public void popToRoot(Object data) {
        FragmentManager fm = getSupportFragmentManager();
        while (fm.getBackStackEntryCount() > 1)
            fm.popBackStackImmediate();

        popTopFragment(data);
    }

    protected boolean processBackPressed() {
        return false;
    }

    private Handler handler = new Handler();
    private Runnable closeApp = new Runnable() {
        @Override
        public void run() {
            mCloseWarned = false;
        }
    };

    public void onBackPressed() {
        if (processBackPressed()) {
            return;
        }

        boolean enableBackPressed = true;
        if (this.mCurrentFragment != null)
            enableBackPressed = !(this.mCurrentFragment.processBackPressed());

        if (enableBackPressed) {
            int cnt = getSupportFragmentManager().getBackStackEntryCount();
            if ((cnt <= 1) && (isTaskRoot())) {
                String closeWarningHint = getCloseWarning();
                if ((!(this.mCloseWarned)) && (!(TextUtils.isEmpty(closeWarningHint)))) {
                    Toast toast = Toast.makeText(this, closeWarningHint, Toast.LENGTH_SHORT);
                    toast.show();
                    this.mCloseWarned = true;
                    handler.postDelayed(closeApp, 2000);
                } else {
                    handler.removeCallbacks(closeApp);
                    doReturnBack();
                }
            } else {
                this.mCloseWarned = false;
                doReturnBack();
            }
        }
    }

    private boolean tryToUpdateCurrentAfterPop() {
        FragmentManager fm = getSupportFragmentManager();
        int cnt = fm.getBackStackEntryCount();
        if (cnt > 0) {
            String name = fm.getBackStackEntryAt(cnt - 1).getName();
            Fragment fragment = fm.findFragmentByTag(name);
            if ((fragment != null) && (fragment instanceof CCFragment))
                this.mCurrentFragment = ((CCFragment) fragment);

            return true;
        }
        return false;
    }

    protected void doReturnBack() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStackImmediate();
            if ((tryToUpdateCurrentAfterPop()) && (this.mCurrentFragment != null))
                this.mCurrentFragment.onBack();
        }
    }

    public void hideKeyboardForCurrentFocus() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        imm.toggleSoftInput(1, 0);
    }

    public void showKeyboardAtView(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 1);
    }

    public void forceShowKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(2, 0);
    }

    protected void exitFullScreen() {
        getWindow().clearFlags(1024);
        getWindow().addFlags(2048);
    }

}
