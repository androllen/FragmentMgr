package com.cc.fragmentmgr.view.aty;

import android.os.Bundle;

import com.cc.fragmentmgr.R;

public class DemoActivity extends TitleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHeaderTitle(R.string.hello_world);
        setContentView(R.layout.aty_demo);
    }

}
