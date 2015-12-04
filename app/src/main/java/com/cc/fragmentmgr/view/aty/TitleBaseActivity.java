package com.cc.fragmentmgr.view.aty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cc.fragmentmgr.R;
import com.cc.fragmentmgr.ui.TitleHeaderBar;

/**
 * Created by androllen on 15/12/3.
 */
public class TitleBaseActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "TitleBaseActivity";

    public TitleBaseActivity() {
    }

    protected TitleHeaderBar mTitleHeaderBar;
    protected LinearLayout mContentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    protected int getFrameLayoutId() {
        return R.layout.base_content_frame_with_title_header;
    }

    protected TitleHeaderBar getTitleHeaderBar() {
        return (TitleHeaderBar) findViewById(R.id.cube_mints_content_frame_title_header);
    }

    protected LinearLayout getContentContainer() {
        return (LinearLayout) findViewById(R.id.cube_mints_content_frame_content);
    }

    protected void initViews() {
        super.setContentView(getFrameLayoutId());

        mTitleHeaderBar = getTitleHeaderBar();
        mContentContainer = getContentContainer();

        if (enableDefaultBack()) {
            mTitleHeaderBar.setLeftViewContainer(this);
        } else {
            mTitleHeaderBar.getLeftViewContainer().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (!processBackPressed()) {
            doReturnBack();
        }
    }

    protected boolean enableDefaultBack() {
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContentContainer.addView(view);
    }

    public void setContentViewSupper(int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected void setHeaderTitle(int id) {
        mTitleHeaderBar.getTitleTextView().setText(id);
    }

    protected void setHeaderTitle(String title) {
        mTitleHeaderBar.setTitle(title);
    }
}
