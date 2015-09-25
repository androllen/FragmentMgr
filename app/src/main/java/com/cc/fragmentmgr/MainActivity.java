package com.cc.fragmentmgr;

import android.os.Bundle;

import com.cc.fragmentmgr.fragment.ui.view.CCBaseActivity;
import com.cc.fragmentmgr.fragment.ui.view.PtrDemoHomeFragment;

public class MainActivity extends CCBaseActivity {

    @Override
    protected int getFragmentContainerId() {
        return R.id.id_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.pushFragmentToBackStack(PtrDemoHomeFragment.class,null);

    }

}
