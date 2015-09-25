package com.cc.fragmentmgr.fragment.ui.view;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cc.fragmentmgr.R;

/**
 * Created by androllen on 2015/9/25.
 */
public class TitleHeaderBar extends RelativeLayout {

    private ImageView mLeftReturnImageView;
    private TextView mCenterTitleTextView;
    private RelativeLayout mLeftViewContainer;
    private RelativeLayout mRightViewContainer;
    private RelativeLayout mCenterViewContainer;
    private String mTitle;


    public TitleHeaderBar(Context context) {
        this(context, null);
    }

    public TitleHeaderBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleHeaderBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        LayoutInflater.from(context).inflate(getHeaderViewLayoutId(), this);
        mLeftReturnImageView = (ImageView) findViewById(R.id.iv_title_bar_left);
        mCenterTitleTextView = (TextView) findViewById(R.id.tv_title_bar_center);

        mLeftViewContainer = (RelativeLayout) findViewById(R.id.ly_title_bar_left);
        mCenterViewContainer = (RelativeLayout) findViewById(R.id.ly_title_bar_center);
        mRightViewContainer = (RelativeLayout) findViewById(R.id.ly_title_bar_right);

    }

    public int getHeaderViewLayoutId() {
        return R.layout.fragmgr_base_header_bar_title;
    }

    public TextView getTitleTextView() {
        return mCenterTitleTextView;
    }

    public ImageView getTitleImageView() {
        return mLeftReturnImageView;
    }

    public void setTitle(String title) {
        mTitle = title;
        mCenterTitleTextView.setText(title);
    }

    public String getTitle() {
        return mTitle;
    }

    private LayoutParams makeLayoutParams(View view) {
        android.view.ViewGroup.LayoutParams lpOld = view.getLayoutParams();
        LayoutParams lp = null;
        if (lpOld == null) {
            lp = new LayoutParams(-2, -1);
        } else {
            lp = new LayoutParams(lpOld.width, lpOld.height);
        }

        return lp;
    }

    public void setCustomizedLeftView(View view) {
        this.mLeftReturnImageView.setVisibility(View.GONE);
        LayoutParams lp = this.makeLayoutParams(view);
        lp.addRule(15);
        lp.addRule(9);
        this.getLeftViewContainer().addView(view, lp);
    }

    public void setCustomizedLeftView(int layoutId) {
        View view = inflate(this.getContext(), layoutId, null);
        this.setCustomizedLeftView(view);
    }

    public void setCustomizedCenterView(View view) {
        this.mCenterTitleTextView.setVisibility(8);
        LayoutParams lp = this.makeLayoutParams(view);
        lp.addRule(13);
        this.getCenterViewContainer().addView(view, lp);
    }

    public void setCustomizedCenterView(int layoutId) {
        View view = inflate(this.getContext(), layoutId, null);
        this.setCustomizedCenterView(view);
    }

    public void setCustomizedRightView(View view) {
        LayoutParams lp = this.makeLayoutParams(view);
        lp.addRule(15);
        lp.addRule(11);
        this.getRightViewContainer().addView(view, lp);
    }
    public RelativeLayout getLeftViewContainer() {
        return mLeftViewContainer;
    }

    public RelativeLayout getCenterViewContainer() {
        return this.mCenterViewContainer;
    }

    public RelativeLayout getRightViewContainer() {
        return this.mRightViewContainer;
    }

    public void setLeftViewContainer(OnClickListener l){
        this.mLeftViewContainer.setOnClickListener(l);
    }

    public void setRightViewContainer(OnClickListener l){
        this.mRightViewContainer.setOnClickListener(l);
    }

    public void setCenterViewContainer(OnClickListener l){
        this.mCenterViewContainer.setOnClickListener(l);
    }
}
