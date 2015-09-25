package com.cc.fragmentmgr.fragment;

import com.cc.fragmentmgr.R;

/**
 * Created by androllen on 2015/9/18.
 */
public class BaseActivity extends CCActivity{
    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected String getCloseWarning() {
        return this.getString(R.string.cube_mints_exit_tip);
    }
}
