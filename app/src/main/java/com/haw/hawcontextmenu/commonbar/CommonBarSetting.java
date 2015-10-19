package com.haw.hawcontextmenu.commonbar;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-9-30.
 */
public class CommonBarSetting implements Parcelable {
    private static final float TEXT_SIZE = 16f;
    private static final String TEXT_COLOR = "#222222";
    public static final int MENU_ANIM_SCALE = 1;
    public static final int MENU_ANIM_ANIMATOR = 9;


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

    /**
     * 菜单属性
     */
    private String menuTitle;
    private boolean hasMenu = false;
    private List<MenuObject> mMenuObjects;
    private int menuWidth;
    private int menuAnimation;


    public CommonBarSetting() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.leftText);
        dest.writeInt(this.leftImageId);
        dest.writeFloat(this.leftTextSize);
        dest.writeInt(this.leftTextColor);
        dest.writeByte(leftLayoutShow ? (byte) 1 : (byte) 0);
        dest.writeByte(leftImageShow ? (byte) 1 : (byte) 0);
        dest.writeString(this.rightText);
        dest.writeInt(this.rightImageId);
        dest.writeFloat(this.rightTextSize);
        dest.writeInt(this.rightTextColor);
        dest.writeByte(rightImageShow ? (byte) 1 : (byte) 0);
        dest.writeByte(rightLayoutShow ? (byte) 1 : (byte) 0);
        dest.writeString(this.middleText);
        dest.writeInt(this.middleTextColor);
        dest.writeFloat(this.middleTextSize);
        dest.writeByte(middleTextClickable ? (byte) 1 : (byte) 0);
        dest.writeString(this.menuTitle);
        dest.writeByte(hasMenu ? (byte) 1 : (byte) 0);
        dest.writeList(this.mMenuObjects);
        dest.writeInt(this.menuWidth);
        dest.writeInt(this.menuAnimation);
    }

    protected CommonBarSetting(Parcel in) {
        this.leftText = in.readString();
        this.leftImageId = in.readInt();
        this.leftTextSize = in.readFloat();
        this.leftTextColor = in.readInt();
        this.leftLayoutShow = in.readByte() != 0;
        this.leftImageShow = in.readByte() != 0;
        this.rightText = in.readString();
        this.rightImageId = in.readInt();
        this.rightTextSize = in.readFloat();
        this.rightTextColor = in.readInt();
        this.rightImageShow = in.readByte() != 0;
        this.rightLayoutShow = in.readByte() != 0;
        this.middleText = in.readString();
        this.middleTextColor = in.readInt();
        this.middleTextSize = in.readFloat();
        this.middleTextClickable = in.readByte() != 0;
        this.menuTitle = in.readString();
        this.hasMenu = in.readByte() != 0;
        this.mMenuObjects = new ArrayList<MenuObject>();
        in.readList(this.mMenuObjects, List.class.getClassLoader());
        this.menuWidth = in.readInt();
        this.menuAnimation = in.readInt();
    }

    public static final Creator<CommonBarSetting> CREATOR = new Creator<CommonBarSetting>() {
        public CommonBarSetting createFromParcel(Parcel source) {
            return new CommonBarSetting(source);
        }

        public CommonBarSetting[] newArray(int size) {
            return new CommonBarSetting[size];
        }
    };


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

    public int getMenuWidth() {
        return menuWidth;
    }

    public void setMenuWidth(int menuWidth) {
        this.menuWidth = menuWidth;
    }

    public int getMenuAnimation() {
        return menuAnimation;
    }

    public void setMenuAnimation(int menuAnimation) {
        this.menuAnimation = menuAnimation;
    }
}
