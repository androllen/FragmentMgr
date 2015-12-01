package com.cc.fragmentmgr.fragment.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cc.fragmentmgr.R;

/**
 * Created by androllen on 2015/9/25.
 */
public class PtrDemoHomeFragment extends TitleBaseFragment implements View.OnClickListener{
    private static final String TAG = "PtrDemoHomeFragment";
    private Button mButton;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(this.getFrameLayoutId(), (ViewGroup)null);

        mButton=(Button)view.findViewById(R.id.btn_click);
        mButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        getContext().pushFragmentToBackStack(TabDemoFragment.class, null);
    }

    @Override
    protected int getFrameLayoutId() {
        return R.layout.fragment_cc;
    }

    @Override
    protected String getHeaderTitle() {
        return this.getString(R.string.hello_blank_fragment);
    }

    @Override
    protected boolean enableDefaultBack(){
        return false;
    }
}
