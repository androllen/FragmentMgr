package com.cc.fragmentmgr.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cc.fragmentmgr.R;
import com.cc.fragmentmgr.ui.TitleHeaderBar;

/**
 * Created by androllen on 2015/9/25.
 */
public abstract class TitleBaseFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "TitleBaseFragment";

    protected abstract int getFrameLayoutId();
    protected TitleHeaderBar mTitleHeaderBar;
    protected LinearLayout mContentContainer;

    public TitleBaseFragment() {

    }

    public View onCreateView(LayoutInflater Inflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        ViewGroup view = (ViewGroup) Inflater.inflate(this.getLayoutId(), (ViewGroup)null);
        LinearLayout contentContainer = (LinearLayout) view.findViewById(R.id.cube_mints_content_frame_content);

        this.mTitleHeaderBar = (TitleHeaderBar) view.findViewById(R.id.cube_mints_content_frame_title_header);
        if (enableDefaultBack()) {
            mTitleHeaderBar.setLeftViewContainer(this);
        } else {
            this.mTitleHeaderBar.getLeftViewContainer().setVisibility(View.INVISIBLE);
        }

        this.mTitleHeaderBar.setTitle(getHeaderTitle());

        mContentContainer = contentContainer;
        View contentView = this.createView(Inflater, view, paramBundle);
        contentView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        contentContainer.addView(contentView);

        return view;
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    private void onBackPressed() {
        this.getContext().onBackPressed();
    }

    @Override
    protected boolean enableDefaultBack() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_content_frame_with_title_header;
    }

    private TitleHeaderBar getTitleHeaderBar() {
        return mTitleHeaderBar;
    }
}
