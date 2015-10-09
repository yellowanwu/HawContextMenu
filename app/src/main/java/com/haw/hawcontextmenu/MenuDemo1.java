package com.haw.hawcontextmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.haw.hawcontextmenu.commonbar.CommonBar;
import com.haw.hawcontextmenu.commonbar.CommonBarSetting;
import com.haw.hawcontextmenu.commonbar.MenuObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-9-29.
 */
public class MenuDemo1 extends Activity implements View.OnClickListener {

    CommonBar commonBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu_demo01);
        CommonBarSetting barSetting = new CommonBarSetting();
        barSetting.setMiddleText("待办任务");
        barSetting.setMenuObjects(getMenuObjects());


        commonBar = new CommonBar(this, -1);
        commonBar.init(barSetting);
    }

    private List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject agree = new MenuObject("同意");
        agree.setResource(R.mipmap.face_agree_02);
        MenuObject disagree = new MenuObject("不同意");
        disagree.setResource(R.mipmap.face_disagree_02);

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
                commonBar.showMenu();
                break;
            default:
            break;


        }
    }
}
