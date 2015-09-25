package com.cc.fragmentmgr.fragment.ui.view;

import com.cc.fragmentmgr.R;
import com.cc.fragmentmgr.fragment.CCActivity;

/**
 * Created by androllen on 2015/9/25.
 */
public class CCBaseActivity  extends CCActivity{

    public CCBaseActivity(){

    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected String getCloseWarning() {
        return this.getString(R.string.cube_mints_exit_tip);
    }

}
