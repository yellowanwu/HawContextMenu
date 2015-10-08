package com.haw.hawcontextmenu.commonbar;

import android.graphics.Color;

import com.haw.hawcontextmenu.MenuObject;

import java.util.List;

/**
 * Created by Administrator on 2015-9-30.
 */
public class CommonBarSetting {
    private static final float TEXT_SIZE = 16f;
    private static final String TEXT_COLOR = "#222222";


    private String leftText;
    private int leftImageId = -1;
    private float leftTextSize = TEXT_SIZE;
    private int leftTextColor = Color.parseColor(TEXT_COLOR);
    private boolean leftLayoutShow = true;
    private boolean leftImageShow = true;


    private String rightText;
    private int rightImageId = -1;
    private float rightTextSize = TEXT_SIZE;
    private int rightTextColor = Color.parseColor(TEXT_COLOR);
    private boolean rightImageShow = true;
    private boolean rightLayoutShow = true;


    private String middleText;
    private int middleTextColor = Color.parseColor(TEXT_COLOR);
    private float middleTextSize = TEXT_SIZE + 2f;
    private boolean middleTextClickable = false;

    private String menuTitle;
    private boolean hasMenu = false;
    private List<MenuObject> mMenuObjects;


    public boolean isMiddleTextClickable() {
        return middleTextClickable;
    }

    public void setMiddleTextClickable(boolean middleTextClickable) {
        this.middleTextClickable = middleTextClickable;
    }

    public String getMiddleText() {
        return middleText;
    }

    public void setMiddleText(String middleText) {
        this.middleText = middleText;
    }

    public int getMiddleTextColor() {
        return middleTextColor;
    }

    public void setMiddleTextColor(int middleTextColor) {
        this.middleTextColor = middleTextColor;
    }

    public float getMiddleTextSize() {
        return middleTextSize;
    }

    public void setMiddleTextSize(float middleTextSize) {
        this.middleTextSize = middleTextSize;
    }

    public boolean isLeftLayoutShow() {
        return leftLayoutShow;
    }

    public void setLeftLayoutShow(boolean leftLayoutShow) {
        this.leftLayoutShow = leftLayoutShow;
    }

    public boolean isLeftImageShow() {
        return leftImageShow;
    }

    public void setLeftImageShow(boolean leftImageShow) {
        this.leftImageShow = leftImageShow;
    }

    public boolean isRightLayoutShow() {
        return rightLayoutShow;
    }

    public void setRightLayoutShow(boolean rightLayoutShow) {
        this.rightLayoutShow = rightLayoutShow;
    }

    public boolean isRightImageShow() {
        return rightImageShow;
    }

    public void setRightImageShow(boolean rightImageShow) {
        this.rightImageShow = rightImageShow;
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public int getLeftImageId() {
        return leftImageId;
    }

    public void setLeftImageId(int leftImageId) {
        this.leftImageId = leftImageId;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public int getRightImageId() {
        return rightImageId;
    }

    public void setRightImageId(int rightImageId) {
        this.rightImageId = rightImageId;
    }

    public float getLeftTextSize() {
        return leftTextSize;
    }

    public void setLeftTextSize(float leftTextSize) {
        this.leftTextSize = leftTextSize;
    }

    public float getRightTextSize() {
        return rightTextSize;
    }

    public void setRightTextSize(float rightTextSize) {
        this.rightTextSize = rightTextSize;
    }

    public int getLeftTextColor() {
        return leftTextColor;
    }

    public void setLeftTextColor(int leftTextColor) {
        this.leftTextColor = leftTextColor;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
    }


    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public boolean isHasMenu() {
        return hasMenu;
    }

    public void setHasMenu(boolean hasMenu) {
        this.hasMenu = hasMenu;
    }

    public List<MenuObject> getMenuObjects() {
        return mMenuObjects;
    }

    public void setMenuObjects(List<MenuObject> mMenuObjects) {
        this.mMenuObjects = mMenuObjects;
    }
}
