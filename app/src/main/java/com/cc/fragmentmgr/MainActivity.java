package com.cc.fragmentmgr;

import android.os.Bundle;

import com.cc.fragmentmgr.model.FragmentType;
import com.cc.fragmentmgr.view.aty.BaseActivity;
import com.cc.fragmentmgr.view.fragment.PtrDemoHomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int getFragmentContainerId() {
        return R.id.id_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.pushFragmentToBackStack(PtrDemoHomeFragment.class,null, FragmentType.Add);

    }

}
