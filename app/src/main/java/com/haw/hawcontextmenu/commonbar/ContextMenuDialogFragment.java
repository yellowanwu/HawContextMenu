package com.haw.hawcontextmenu.commonbar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.haw.hawcontextmenu.R;

/**
 * Created by Administrator on 2015-10-8.
 */
public class ContextMenuDialogFragment extends DialogFragment {

    public static final String TAG = ContextMenuDialogFragment.class.getSimpleName();
    private static final String BUNDLE_MENU_PARAMS = "BUNDLE_MENU_PARAMS";
    private CommonBarSetting barSetting;
    private ImageView arrowImage;

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
        if (getArguments() != null) {
            barSetting = getArguments().getParcelable(BUNDLE_MENU_PARAMS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comm_bar_menu, container, false);
        if (arrowImage == null) {
            arrowImage = (ImageView) rootView.findViewById(R.id.comm_bar_menu_arrow);
            this.setArrorwMargin(0);
        }
//        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        rootView.setFitsSystemWindows(true);
//        ((ViewGroup) rootView).setClipToPadding(true);

//        final DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        final WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
//        layoutParams.width = getResources().getDimensionPixelOffset(R.dimen.dialog_four_items);
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        layoutParams.gravity = Gravity.RIGHT;
//        getDialog().getWindow().setAttributes(layoutParams);

        return rootView;

    }

    /**
     * 重新计算dialog的箭头位置
     *
     * @param paramInt
     */
    private void setArrorwMargin(int paramInt) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.arrowImage.getLayoutParams();
        marginLayoutParams.rightMargin = paramInt;
        this.arrowImage.setLayoutParams(marginLayoutParams);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        System.out.println("onCreateDialog...");
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setDialogPosition(dialog, 0, 0);
        return dialog;
    }

    /**
     * 设置dialog的宽高，并指定显示位置
     *
     * @param dialog
     * @param px
     * @param py
     */
    public void setDialogPosition(Dialog dialog, int px, int py) {
        final WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        int menuWidth = barSetting.getMenuWidth();
        if (menuWidth <= 0) {
            layoutParams.width = getResources().getDimensionPixelOffset(R.dimen.common_bar_menu_width);
        } else {
            layoutParams.width = menuWidth;
        }
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;//getResources().getDimensionPixelOffset(R.dimen.common_bar_menu_height);//WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        layoutParams.x = px;
        layoutParams.y = py;
        dialog.getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
//        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        final DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.d(TAG, String.format("dm.widthPixels=[%s]", dm.widthPixels));
        Log.d(TAG, String.format("dm.heightPixels=[%s]", dm.heightPixels));
        int[] arrowLocation = new int[2];
        this.arrowImage.getLocationOnScreen(arrowLocation);
        Log.d(TAG, String.format("arrowLocation.x=[%s]", arrowLocation[0]));
        Log.d(TAG, String.format("arrowLocation.y=[%s]", arrowLocation[1]));
        final WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        Log.d(TAG, String.format("dialog.x=[%s]", layoutParams.x));
        Log.d(TAG, String.format("dialog.y=[%s]", layoutParams.y));

    }
}
