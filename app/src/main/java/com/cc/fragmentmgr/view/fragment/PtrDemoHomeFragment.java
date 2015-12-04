package com.cc.fragmentmgr.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cc.fragmentmgr.R;
import com.cc.fragmentmgr.model.FragmentType;
import com.cc.fragmentmgr.view.aty.DemoActivity;

/**
 * Created by androllen on 2015/9/25.
 */
public class PtrDemoHomeFragment extends TitleBaseFragment implements View.OnClickListener{
    private static final String TAG = "PtrDemoHomeFragment";
    private Button mButton,mDownBtn;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(this.getFrameLayoutId(), null);

        mButton=(Button)view.findViewById(R.id.btn_click);
        mButton.setOnClickListener(this);

        mDownBtn=(Button)view.findViewById(R.id.btn_actity);
        mDownBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_click){
            getContext().pushFragmentToBackStack(TabDemoFragment.class, null, FragmentType.Replace);

        }else if(view.getId()==R.id.btn_actity){
            Intent intent = new Intent();
            intent.setClass(getContext(), DemoActivity.class);
            startActivity(intent);

        }

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
