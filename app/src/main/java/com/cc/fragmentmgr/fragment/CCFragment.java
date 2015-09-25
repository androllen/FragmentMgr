package com.cc.fragmentmgr.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.fragmentmgr.R;
import com.cc.fragmentmgr.fragment.app.ICCFragment;
import com.cc.fragmentmgr.fragment.life.LifeCycleComponent;
import com.cc.fragmentmgr.fragment.life.LifeCycleComponentManager;
import com.cc.fragmentmgr.tools.CCDebug;
import com.cc.fragmentmgr.tools.CLog;

public abstract class CCFragment extends Fragment implements ICCFragment {

    private static final boolean DEBUG = CCDebug.DEBUG_LIFE_CYCLE;
    protected Object mDataIn;
    private boolean mFirstResume;
    private LifeCycleComponentManager mComponentContainer;

    public CCFragment() {
        this.mFirstResume = true;

        this.mComponentContainer = new LifeCycleComponentManager();
    }
    protected abstract View createView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle);

    public CCFragmentActivity getContext() {
        return ((CCFragmentActivity)getActivity());
    }

    public void onEnter(Object data)
    {
        this.mDataIn = data;
        if (DEBUG)
            showStatus("onEnter");
    }

    public void onLeave()
    {
        if (DEBUG)
            showStatus("onLeave");

        this.mComponentContainer.onBecomesTotallyInvisible();
    }

    public void onBackWithData(Object data)
    {
        if (DEBUG)
            showStatus("onBackWithData");

        this.mComponentContainer.onBecomesVisibleFromTotallyInvisible();
    }

    public boolean processBackPressed()
    {
        return false;
    }

    public void onBack()
    {
        if (DEBUG)
            showStatus("onBack");

        this.mComponentContainer.onBecomesVisibleFromTotallyInvisible();
    }

    public void addComponent(LifeCycleComponent component)
    {
        this.mComponentContainer.addComponent(component);
    }

    //当Fragment与Activity发生关联时调用
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if (DEBUG)
            showStatus("onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG)
            showStatus("onCreate");
    }

    //创建该Fragment的视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (DEBUG)
            showStatus("onCreateView");
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    //当Activity的onCreate方法返回时调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        if (DEBUG)
            showStatus("onActivityCreated");
    }
    @Override
    public void onStart() {
        super.onStart();
        if (DEBUG)
            showStatus("onStart");
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!(this.mFirstResume))
            onBack();

        if (this.mFirstResume)
            this.mFirstResume = false;

        if (DEBUG)
            showStatus("onResume");
    }
    @Override
    public void onPause() {
        super.onPause();
        if (DEBUG)
            showStatus("onPause");
    }
    @Override
    public void onStop() {
        super.onStop();
        if (DEBUG)
            showStatus("onStop");

        onLeave();
    }
    //与onCreateView相对应，当该Fragment的视图被移除时调用
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        if (DEBUG)
            showStatus("onDestroyView");
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (DEBUG)
            showStatus("onDestroy");

        this.mComponentContainer.onDestroy();
    }
    //与onCreateView相对应，当该Fragment的视图被移除时调用
    @Override
    public void onDetach() {
        super.onDetach();
        if (DEBUG)
            showStatus("onDetach");
    }

    private void showStatus(String status) {
        String[] className = getClass().getName().split("\\.");
        CLog.d("cube-lifecycle", "%s %s", new Object[]{className[(className.length - 1)], status});
    }

}
