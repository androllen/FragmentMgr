package com.cc.fragmentmgr.model;

import com.cc.fragmentmgr.view.fragment.CCFragment;

/**
 * Created by androllen on 2015/9/18.
 */
public class FragmentParam {
    public CCFragment from;
    public Class<?> cls;
    public Object data;
    public FragmentType state;
    public FragmentParam() {
    }
}
