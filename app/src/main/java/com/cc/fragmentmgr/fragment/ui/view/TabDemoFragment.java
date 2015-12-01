package com.cc.fragmentmgr.fragment.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cc.fragmentmgr.R;

/**
 * Created by androllen on 15/12/1.
 */
public class TabDemoFragment extends TitleBaseFragment implements View.OnClickListener{
    private static final String TAG = "TabDemoFragment";
    private Button mButton;
    public TabDemoFragment() {
    }

    @Override
    protected View createView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(getFrameLayoutId(),(ViewGroup)null);
        mButton=(Button)view.findViewById(R.id.btn_click);
        mButton.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View view) {
        //Toast.makeText(getContext(), "tab", Toast.LENGTH_SHORT).show();
        getContext().onBackPressed();
    }
    @Override
    protected int getFrameLayoutId() {
        return R.layout.fragment_cc;
    }

    @Override
    protected String getHeaderTitle() {
        return this.getString(R.string.hello_world);
    }
}
