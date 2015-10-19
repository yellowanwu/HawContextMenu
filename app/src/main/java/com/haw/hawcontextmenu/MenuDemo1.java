package com.haw.hawcontextmenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.haw.hawcontextmenu.commonbar.CommonBar;
import com.haw.hawcontextmenu.commonbar.CommonBarSetting;
import com.haw.hawcontextmenu.commonbar.MenuObject;
import com.haw.hawcontextmenu.commonbar.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-9-29.
 */
public class MenuDemo1 extends Activity implements View.OnClickListener, OnMenuItemClickListener {

    private CommonBar commonBar;
    private List<MenuObject> menuObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu_demo01);
        CommonBarSetting barSetting = new CommonBarSetting();
        barSetting.setMiddleText("待办任务");
        barSetting.setMenuObjects(getMenuObjects());
        barSetting.setMenuWidth(dip2px(this, 120f));
        barSetting.setMenuAnimation(CommonBarSetting.MENU_ANIM_SCALE);
        commonBar = new CommonBar(this, -1);
        commonBar.initView(barSetting).initMenuAdapter();
    }

    private List<MenuObject> getMenuObjects() {
        menuObjects = new ArrayList<>();

        MenuObject add = new MenuObject("新增");
        add.setResource(R.mipmap.icn_1);
        add.setBgColor(Color.parseColor("#FFFFFF"));
        MenuObject upd = new MenuObject("修改");
        upd.setResource(R.mipmap.icn_2);
        upd.setBgColor(Color.parseColor("#FFFFFF"));
        MenuObject del = new MenuObject("删除");
        del.setResource(R.mipmap.icn_3);
        del.setBgColor(Color.parseColor("#FFFFFF"));
        MenuObject agree = new MenuObject("批准");
        agree.setResource(R.mipmap.icn_4);
        agree.setBgColor(Color.parseColor("#FFFFFF"));
        MenuObject disagree = new MenuObject("驳回");
        disagree.setResource(R.mipmap.icn_5);
        disagree.setBgColor(Color.parseColor("#FFFFFF"));
        menuObjects.add(add);
        menuObjects.add(upd);
        menuObjects.add(del);
        menuObjects.add(agree);
        menuObjects.add(disagree);

        return menuObjects;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case CommonBar.LEFT_TEXT_VIEW_ID:
                this.finish();
                break;
            case CommonBar.RIGHT_TEXT_VIEW_ID:
                commonBar.showPopupMenu(v);
                break;
            default:
                break;


        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, menuObjects.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        commonBar.hidePopupMenu();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
