package com.haw.hawcontextmenu.commonbar;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

    private LinearLayout menuItemsLayout;
    private MenuAdapter menuAdapter;
    private PopupWindow mPopupWindow;

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
//        initMenuFragment(setting);
        //haw:初始化mPopupWindow功能栏
        initPopupMenu(setting);
        //haw:初始化Menu适配器
        initDropDownMenuAdapter(setting);
        return this;
    }

    private void initPopupMenu(CommonBarSetting setting) {
        View popView = this.mActivity.getLayoutInflater().inflate(R.layout.comm_bar_menu, null);
        menuItemsLayout = (LinearLayout) popView.findViewById(R.id.comm_bar_menu_items_layout);
        this.mPopupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        //设置宽度
        int menuWidth = setting.getMenuWidth();
        if (menuWidth <= 0) {
            this.mPopupWindow.setWidth(this.mActivity.getResources().getDimensionPixelOffset(R.dimen.common_bar_menu_width));
        } else {
            this.mPopupWindow.setWidth(menuWidth);
        }
        // 设置popupWindow获取焦点，则返回键可以消失窗口
        this.mPopupWindow.setFocusable(true);
        // 设置PopupWindow外部可点击
        this.mPopupWindow.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable bg = new ColorDrawable(0000000000);
        this.mPopupWindow.setBackgroundDrawable(bg);
        this.mPopupWindow.setAnimationStyle(R.style.common_bar_menu_animation);
        this.mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1f);
            }
        });
    }


    private void initDropDownMenuAdapter(CommonBarSetting setting) {
        menuAdapter = new MenuAdapter(this.mActivity, menuItemsLayout, setting.getMenuObjects());
        if(this.mActivity instanceof  OnMenuItemClickListener){
            menuAdapter.setOnItemClickListener((OnMenuItemClickListener) this.mActivity);
        }
//        menuAdapter.setAnimationDuration(mMenuParams.getAnimationDuration());
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.mActivity.getWindow().setAttributes(lp);
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
                leftDrawable = mActivity.getResources().getDrawable(R.mipmap.common_bar_back_btn);//R.drawable.common_bar_back_btn
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
//            rightTextView.setBackgroundResource(R.drawable.common_bar_selector_bg);
            Drawable rightDrawable = null;
            //是否设置了右边控件图标
            if (setting.getLeftImageId() != -1) {
                rightDrawable = mActivity.getResources().getDrawable(setting.getRightImageId());
            } else {
                rightDrawable = mActivity.getResources().getDrawable(R.mipmap.common_bar_more_btn);//R.drawable.common_bar_more_btn
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

    /**
     * 显示惨菜单栏
     *
     * @param view
     */
    public void showPopupMenu(View view) {
        this.setBackgroundAlpha(0.8f);
        mPopupWindow.showAsDropDown(view);
    }
    /**
     * 隐藏惨菜单栏
     *
     * @param view
     */
    public void hidePopupMenu() {
        mPopupWindow.dismiss();
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
