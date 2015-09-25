package com.cc.fragmentmgr.fragment;

import android.os.Bundle;

import com.cc.fragmentmgr.fragment.life.IComponentContainer;
import com.cc.fragmentmgr.fragment.life.LifeCycleComponent;
import com.cc.fragmentmgr.fragment.life.LifeCycleComponentManager;
import com.cc.fragmentmgr.tools.CCDebug;
import com.cc.fragmentmgr.tools.CLog;

/**
 * Created by androllen on 2015/9/18.
 */
public class CCActivity extends CCFragmentActivity implements IComponentContainer {

    private LifeCycleComponentManager mComponentContainer;
    private static final boolean DEBUG = CCDebug.DEBUG_LIFE_CYCLE;

    public CCActivity() {
        mComponentContainer = new LifeCycleComponentManager();
    }

    @Override
    public void addComponent(LifeCycleComponent paramLifeCycleComponent) {
        this.mComponentContainer.addComponent(paramLifeCycleComponent);
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected String getCloseWarning() {
        return null;
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        this.mComponentContainer.onBecomesVisibleFromTotallyInvisible();
        if (DEBUG)
            showStatus("onRestart");
    }

    protected void onPause()
    {
        super.onPause();
        this.mComponentContainer.onBecomesPartiallyInvisible();
        if (DEBUG)
            showStatus("onPause");
    }

    protected void onResume()
    {
        super.onResume();
        this.mComponentContainer.onBecomesVisibleFromPartiallyInvisible();
        if (DEBUG)
            showStatus("onResume");
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (DEBUG)
            showStatus("onCreate");
    }

    protected void onStop()
    {
        super.onStop();
        this.mComponentContainer.onBecomesTotallyInvisible();
        if (DEBUG)
            showStatus("onStop");
    }

    protected void onDestroy()
    {
        super.onDestroy();
        this.mComponentContainer.onDestroy();
        if (DEBUG)
            showStatus("onDestroy");
    }


    private void showStatus(String status) {
        String[] className = getClass().getName().split("\\.");
        CLog.d("cube-lifecycle", String.format("%s %s", new Object[]{className[(className.length - 1)], status}));
    }
}
