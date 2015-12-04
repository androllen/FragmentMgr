package com.cc.fragmentmgr.view.aty;

import com.cc.fragmentmgr.R;

/**
 * 从这继承 没有titlebar
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
