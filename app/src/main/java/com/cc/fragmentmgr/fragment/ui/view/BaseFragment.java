package com.cc.fragmentmgr.fragment.ui.view;

import android.view.View;

import com.cc.fragmentmgr.fragment.CCFragment;

/**
 * Created by androllen on 2015/9/25.
 */
public abstract class BaseFragment extends CCFragment {


    protected abstract int getFrameLayoutId();
    protected abstract boolean enableDefaultBack();

}
