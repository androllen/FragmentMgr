package com.cc.fragmentmgr.app;

/**
 * Created by androllen on 2015/9/18.
 */
public interface ICCFragment {
    void onEnter(Object paramObject);

    void onLeave();

    void onBack();

    void onBackWithData(Object paramObject);

    boolean processBackPressed();
}
