package com.haw.hawcontextmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.haw.hawcontextmenu.commonbar.CommonBar;
import com.haw.hawcontextmenu.commonbar.CommonBarSetting;

/**
 * Created by Administrator on 2015-9-29.
 */
public class MenuDemo1 extends Activity implements View.OnClickListener  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu_demo01);
        CommonBarSetting barSetting = new CommonBarSetting();
        barSetting.setMiddleText("待办任务");
        CommonBar commonBar = new CommonBar(this, -1);
        commonBar.init(barSetting);
    }

    @Override
    public void onClick(View v) {

    }
}
