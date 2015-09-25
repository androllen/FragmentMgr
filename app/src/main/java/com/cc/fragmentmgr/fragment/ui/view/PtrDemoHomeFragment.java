package com.cc.fragmentmgr.fragment.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.fragmentmgr.R;

/**
 * Created by androllen on 2015/9/25.
 */
public class PtrDemoHomeFragment extends TitleBaseFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=super.createView(inflater, container, savedInstanceState);
        setHeaderTitle(this.getString(R.string.hello_blank_fragment));
        return view;
    }

    @Override
    protected int getFrameLayoutId() {
        return R.layout.fragment_cc;
    }

    @Override
    protected boolean enableDefaultBack(){
        return true;
    }
}
