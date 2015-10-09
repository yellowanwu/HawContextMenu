package com.haw.hawcontextmenu.commonbar;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bstek.dorado.stk.common.lang.utils.StringUtils;
import com.haw.hawcontextmenu.R;

/**
 * Created by haw on 2015-9-30.
 */
public class CommonBar {

    @android.support.annotation.IdRes
    public static final int LEFT_TEXT_VIEW_ID = 0x093001;
    @android.support.annotation.IdRes
    public static final int MIDDLE_TEXT_VIEW_ID = 0x093002;
    @android.support.annotation.IdRes
    public static final int RIGHT_TEXT_VIEW_ID = 0x093003;


    private Activity mActivity;
    private TextView leftTextView;
    private TextView middleTextView;
    private TextView rightTextView;

    private LinearLayout barLayout;
    private LinearLayout leftLayout;
    private LinearLayout middleLayout;
    private LinearLayout rightLayout;
    private FragmentManager fragmentManager;
    private DialogFragment mMenuDialogFragment;

    public CommonBar(Activity activity, int layoutId) {
        this.mActivity = activity;
        if (layoutId == -1) {
            barLayout = (LinearLayout) this.mActivity.findViewById(R.id.common_bar);
        } else {
            barLayout = (LinearLayout) this.mActivity.findViewById(layoutId);
        }
        leftLayout = (LinearLayout) this.barLayout.findViewById(R.id.left_layout);
        middleLayout = (LinearLayout) this.barLayout.findViewById(R.id.middle_layout);
        rightLayout = (LinearLayout) this.barLayout.findViewById(R.id.right_layout);
        fragmentManager = this.mActivity.getFragmentManager();//getSupportFragmentManager();
    }

    public CommonBar init(CommonBarSetting setting) {
        initLeftLayout(setting);
        initMiddleLayout(setting);
        initRightLayout(setting);
        //haw:初始化功能菜单栏
        initMenuFragment(setting);
        return this;
    }

    private void initMenuFragment(CommonBarSetting setting) {
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(setting);
    }


    private void initLeftLayout(CommonBarSetting setting) {
        if (this.mActivity instanceof CommonBarCustomLayout) {
            CommonBarCustomLayout customLayout = (CommonBarCustomLayout) this.mActivity;
            if (customLayout.customLeftLayout()) {
                return;
            }
        }
        initDefaultLeftLayout(setting);
    }


    private void initMiddleLayout(CommonBarSetting setting) {
        if (this.mActivity instanceof CommonBarCustomLayout) {
            CommonBarCustomLayout customLayout = (CommonBarCustomLayout) this.mActivity;
            if (customLayout.customMiddleLayout()) {
                return;
            }
        }
        initDefaultMiddleLayout(setting);
    }

    private void initRightLayout(CommonBarSetting setting) {
        if (this.mActivity instanceof CommonBarCustomLayout) {
            CommonBarCustomLayout customLayout = (CommonBarCustomLayout) this.mActivity;
            if (customLayout.customRightLayout()) {
                return;
            }
        }
        initDefaultRightLayout(setting);
    }

    private void initDefaultLeftLayout(CommonBarSetting setting) {
        //是否显示左边控件局域
        if (setting.isLeftLayoutShow()) {
            leftTextView = new TextView(mActivity);
            leftTextView.setId(LEFT_TEXT_VIEW_ID);
            leftTextView.setGravity(Gravity.CENTER);
            leftTextView.setOnClickListener((View.OnClickListener) mActivity);
            leftTextView.setBackgroundResource(R.drawable.common_bar_selector_bg);
            Drawable leftDrawable = null;
            //是否设置了左边控件图标
            if (setting.getLeftImageId() != -1) {
                leftDrawable = mActivity.getResources().getDrawable(setting.getLeftImageId());
            } else {
                leftDrawable = mActivity.getResources().getDrawable(R.drawable.common_bar_back_btn);
            }
            if (leftDrawable != null && setting.isLeftImageShow()) {
                leftTextView.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);
            }
            //设置左边文字的内容和颜色
            if (StringUtils.isNotEmpty(setting.getLeftText())) {
                leftTextView.setText(setting.getLeftText());
                leftTextView.setTextSize(setting.getLeftTextSize());
                leftTextView.setTextColor(setting.getLeftTextColor());
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            leftLayout.addView(leftTextView, layoutParams);
        }
    }


    private void initDefaultMiddleLayout(CommonBarSetting setting) {
        middleTextView = new TextView(mActivity);
        middleTextView.setId(MIDDLE_TEXT_VIEW_ID);
        middleTextView.setGravity(Gravity.CENTER);
        if (setting.isMiddleTextClickable()) {
            middleTextView.setOnClickListener((View.OnClickListener) mActivity);
        }
        //设置中间文字
        if (StringUtils.isNotEmpty(setting.getMiddleText())) {
            middleTextView.setText(setting.getMiddleText());
            middleTextView.setTextSize(setting.getMiddleTextSize());
            middleTextView.setTextColor(setting.getMiddleTextColor());
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1f;
        middleLayout.addView(middleTextView, layoutParams);
    }


    private void initDefaultRightLayout(CommonBarSetting setting) {
        //是否显示右边控件局域
        if (setting.isRightLayoutShow()) {
            rightTextView = new TextView(mActivity);
            rightTextView.setId(RIGHT_TEXT_VIEW_ID);
            rightTextView.setGravity(Gravity.CENTER);
            rightTextView.setOnClickListener((View.OnClickListener) mActivity);
            rightTextView.setBackgroundResource(R.drawable.common_bar_selector_bg);
            Drawable rightDrawable = null;
            //是否设置了右边控件图标
            if (setting.getLeftImageId() != -1) {
                rightDrawable = mActivity.getResources().getDrawable(setting.getRightImageId());
            } else {
                rightDrawable = mActivity.getResources().getDrawable(R.drawable.common_bar_more_btn);
            }
            if (rightDrawable != null && setting.isLeftImageShow()) {
                rightTextView.setCompoundDrawablesWithIntrinsicBounds(rightDrawable, null, null, null);
            }
            //设置右边文字
            if (StringUtils.isNotEmpty(setting.getLeftText())) {
                rightTextView.setText(setting.getLeftText());
                rightTextView.setTextSize(setting.getLeftTextSize());
                rightTextView.setTextColor(setting.getLeftTextColor());
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            rightLayout.addView(rightTextView, layoutParams);
        }
    }


    public void showMenu(){
        if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
            mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
        }
    }

    /**
     * haw:
     */
    public interface CommonBarCustomLayout {
        boolean customLeftLayout();

        boolean customMiddleLayout();

        boolean customRightLayout();
    }

}
