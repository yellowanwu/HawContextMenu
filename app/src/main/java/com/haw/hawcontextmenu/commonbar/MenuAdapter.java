package com.haw.hawcontextmenu.commonbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haw.hawcontextmenu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by haw on 2015-10-12.
 */
public class MenuAdapter {
    public static final int ANIMATION_DURATION_MILLIS = 150;
    private Context mContext;
    private LinearLayout mMenuWrapper;
    private List<MenuObject> mMenuObjects;
    private List<View> menuItemViews;
    private OnMenuItemClickListener mOnMenuItemClickListener;

    public MenuAdapter(Context context, LinearLayout menuWrapper, List<MenuObject> menuObjects) {
        this.mContext = context;
        this.mMenuWrapper = menuWrapper;
        this.mMenuObjects = menuObjects;
        setViews();
    }


    /**
     * Creating views and filling to wrappers
     */
    private void setViews() {
        menuItemViews = new ArrayList<>();
        for (int i = 0; i < mMenuObjects.size(); i++) {
            MenuObject menuObject = mMenuObjects.get(i);
            View view = this.getItemWrapper(mContext, menuObject, clickItem, i != mMenuObjects.size() - 1);
            menuItemViews.add(view);
            mMenuWrapper.addView(view);
        }
    }


    @SuppressLint("NewApi")
    public RelativeLayout getItemWrapper(Context context, MenuObject menuItem, View.OnClickListener onCLick,
                                         boolean showDivider) {
        RelativeLayout itemWrapper = new RelativeLayout(context);


        LinearLayout.LayoutParams itemWrapperLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        itemWrapper.setLayoutParams(itemWrapperLayoutParams);

        //        itemWrapper.setGravity(Gravity.CENTER);
        itemWrapper.setOnClickListener(onCLick);
        ImageView ico = this.getItemImageView(context, menuItem);
        TextView textView = this.getItemTextView(context, menuItem, ico);
        itemWrapper.addView(textView);
        itemWrapper.addView(ico);

        if (showDivider) {
            itemWrapper.addView(getDivider(context, menuItem));
        }

        if (menuItem.getBgColor() != 0) {
            itemWrapper.setBackgroundColor(menuItem.getBgColor());
        } else if (menuItem.getBgDrawable() != null) {
            itemWrapper.setBackground(menuItem.getBgDrawable());
        } else if (menuItem.getBgResource() != 0) {
            itemWrapper.setBackgroundResource(menuItem.getBgResource());
        } else {
            itemWrapper.setBackgroundColor(Color.TRANSPARENT);
        }
        return itemWrapper;
    }

    public TextView getItemTextView(Context context, MenuObject menuItem, ImageView imageView) {
        TextView itemTextView = new TextView(context);
        itemTextView.setId(generateViewId());
        RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
        textLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        itemTextView.setLayoutParams(textLayoutParams);
        //        itemTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.menu_text_size));
        itemTextView.setText(menuItem.getTitle());
        itemTextView.setPadding(0, 0, 0, 0);
        itemTextView.setGravity(Gravity.CENTER_VERTICAL);
        itemTextView.setTextAppearance(context, menuItem.getMenuTextAppearanceStyle() > 0
                ? menuItem.getMenuTextAppearanceStyle()
                : R.style.common_bar_menu_item_text_defaultStyle);
        return itemTextView;
    }


    public ImageView getItemImageView(Context context, MenuObject item) {
        ImageView imageView = new ImageView(context);
        imageView.setId(generateViewId());
        int width = context.getResources().getDimensionPixelSize(R.dimen.common_bar_menu_item_ico_width);
        int height = context.getResources().getDimensionPixelSize(R.dimen.common_bar_menu_item_ico_height);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, height);

        imageView.setLayoutParams(lp);
        imageView.setPadding((int) context.getResources().getDimension(R.dimen.common_bar_menu_item_ico_padding),
                (int) context.getResources().getDimension(R.dimen.common_bar_menu_item_ico_padding),
                (int) context.getResources().getDimension(R.dimen.common_bar_menu_item_ico_padding),
                (int) context.getResources().getDimension(R.dimen.common_bar_menu_item_ico_padding));
        imageView.setClickable(false);
        imageView.setFocusable(false);
        imageView.setBackgroundColor(Color.TRANSPARENT);

        if (item.getColor() != 0) {
            Drawable color = new ColorDrawable(item.getColor());
            imageView.setImageDrawable(color);
        } else if (item.getResource() != 0) {
            imageView.setImageResource(item.getResource());
        } else if (item.getBitmap() != null) {
            imageView.setImageBitmap(item.getBitmap());
        } else if (item.getDrawable() != null) {
            imageView.setImageDrawable(item.getDrawable());
        } else {
            imageView.setVisibility(View.GONE);
        }
        imageView.setScaleType(item.getScaleType());


        return imageView;
    }

    public View getDivider(Context context, MenuObject menuItem) {
        View dividerView = new View(context);
        RelativeLayout.LayoutParams viewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.divider_height));
        viewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dividerView.setLayoutParams(viewLayoutParams);
        dividerView.setClickable(true);
        int dividerColor = menuItem.getDividerColor() == Integer.MAX_VALUE ?
                context.getResources().getColor(R.color.divider_color) :
                menuItem.getDividerColor();
        dividerView.setBackgroundColor(dividerColor);
        return dividerView;
    }

    /**
     * @Desc: 当CommonBarSetting中menuAnimation==MENU_ANIM_ANIMATOR时
     * 在CommonBar调用showPopupMenu时将调用此方法
     * @Author Haw
     * 2015-10-15 23:39
     **/
    public void menuToggle() {
        if (!isAnimationRun()) {
            toggleIsMenuOpen();
//            setAnimationRun(false);
        }
    }

    /**
     * @Desc: 设置动画是否正在运行
     * @Author Haw
     * 2015-10-16 17:48
     **/
    public void toggleIsAnimationRun() {
        mIsAnimationRun = !mIsAnimationRun;
    }

    /**
     * @Desc: 设置菜单是否已展开
     * @Author Haw
     * 2015-10-16 17:48
     */
    public void toggleIsMenuOpen() {
        mIsMenuOpen = !mIsMenuOpen;
    }


    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * 动态生成View ID
     * API LEVEL 17 以上View.generateViewId()生成
     * API LEVEL 17 以下需要手动生成
     */
    public static int generateViewId() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF)
                    newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        } else {
            return View.generateViewId();
        }
    }


    private View.OnClickListener clickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewClicked(v);
        }
    };

    private boolean mIsMenuOpen = false;
    private boolean mIsAnimationRun = false;
    private View mClickedView;

    private void viewClicked(View v) {
        if (mIsMenuOpen && !mIsAnimationRun) {
            mClickedView = v;
            int childIndex = mMenuWrapper.indexOfChild(v);
            if (childIndex == -1) {
                return;
            }
            toggleIsAnimationRun();
            onClickedAnimation(v,childIndex);
//            toggleIsMenuOpen();
        }
    }

    /**
     * @Desc: 点击动画，如果有特定的点击动画，则子类重写该方法
     * @Author Haw
     * 2015-10-19 15:58
     **/
    public void onClickedAnimation(View clickedView, int onClickPosition) {
        mOnMenuItemClickListener.onMenuItemClick(clickedView, onClickPosition);
    }


    public List<View> getMenuItemViews() {
        return menuItemViews;
    }

    public boolean isAnimationRun() {
        return mIsAnimationRun;
    }

    public void setAnimationRun(boolean mIsAnimationRun) {
        this.mIsAnimationRun = mIsAnimationRun;
    }

    public boolean isMenuOpen() {
        return mIsMenuOpen;
    }

    public void setMenuOpen(boolean mIsMenuOpen) {
        this.mIsMenuOpen = mIsMenuOpen;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(OnMenuItemClickListener listener) {
        mOnMenuItemClickListener = listener;
    }

    public OnMenuItemClickListener getOnItemClickListener() {
        return this.mOnMenuItemClickListener;
    }

    public View getClickedView() {
        return mClickedView;
    }
}
