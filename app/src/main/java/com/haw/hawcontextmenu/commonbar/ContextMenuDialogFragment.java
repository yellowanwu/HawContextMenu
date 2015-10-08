package com.haw.hawcontextmenu.commonbar;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Administrator on 2015-10-8.
 */
public class ContextMenuDialogFragment extends DialogFragment {

    public static final String TAG = ContextMenuDialogFragment.class.getSimpleName();
    private static final String BUNDLE_MENU_PARAMS = "BUNDLE_MENU_PARAMS";

    public static ContextMenuDialogFragment newInstance(CommonBarSetting barSetting) {
        ContextMenuDialogFragment fragment = new ContextMenuDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_MENU_PARAMS, barSetting);
        fragment.setArguments(args);
        return fragment;
    }


}
