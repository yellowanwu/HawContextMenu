package com.haw.hawcontextmenu.commonbar;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.haw.hawcontextmenu.R;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.MenuFragmentStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comm_bar_menu, container, false);
        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        return rootView;

    }
}
