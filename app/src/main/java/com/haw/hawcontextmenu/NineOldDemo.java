package com.haw.hawcontextmenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

public class NineOldDemo extends Activity implements View.OnClickListener {
    private static final String TAG = NineOldDemo.class.getSimpleName();

    private ImageView imageSample;
    private List<ImageView> colorImag = new ArrayList<ImageView>();
    int imgWidth;
    int imgHeight;
    private EditText input1;
    private EditText input2;
    private Button reset;
    private Button btn01;
    private Button btn02;
    private Button btn03;
    private Button btn04;
    private Button btn05;
    private Button btn06;
    private Button btn07;
    private Button btn08;
    private Button btn09;
    private Button btn10;
    private Button btn11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_old_demo);
        imageSample = (ImageView) this.findViewById(R.id.imageSample);
        initColorImage();
        reset = (Button) this.findViewById(R.id.reset);
        input1 = (EditText) this.findViewById(R.id.input1);
        input2 = (EditText) this.findViewById(R.id.input2);
        btn01 = (Button) this.findViewById(R.id.btn01);
        btn02 = (Button) this.findViewById(R.id.btn02);
        btn03 = (Button) this.findViewById(R.id.btn03);
        btn04 = (Button) this.findViewById(R.id.btn04);
        btn05 = (Button) this.findViewById(R.id.btn05);
        btn06 = (Button) this.findViewById(R.id.btn06);
        btn07 = (Button) this.findViewById(R.id.btn07);
        btn08 = (Button) this.findViewById(R.id.btn08);
        btn09 = (Button) this.findViewById(R.id.btn09);
        btn10 = (Button) this.findViewById(R.id.btn10);
        btn11 = (Button) this.findViewById(R.id.btn11);
        imageSample.setOnClickListener(this);
        reset.setOnClickListener(this);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btn04.setOnClickListener(this);
        btn05.setOnClickListener(this);
        btn06.setOnClickListener(this);
        btn07.setOnClickListener(this);
        btn08.setOnClickListener(this);
        btn09.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
    }

    private void initColorImage() {
        ImageView black = (ImageView) this.findViewById(R.id.imageBlack);
        ImageView red = (ImageView) this.findViewById(R.id.imageRed);
        ImageView blue = (ImageView) this.findViewById(R.id.imageBlue);
        ImageView grey = (ImageView) this.findViewById(R.id.imageGrey);
        ImageView yellow = (ImageView) this.findViewById(R.id.imageYellow);
        colorImag.add(red);
        colorImag.add(blue);
        colorImag.add(yellow);
        colorImag.add(black);
        colorImag.add(grey);
    }


    @Override
    public void onClick(View v) {
        imgWidth = imageSample.getMeasuredWidth();
        imgHeight = imageSample.getMeasuredHeight();
        switch (v.getId()) {
            case R.id.reset:
                this.resetColorImag();
//                Log.d(TAG, String.format("getPivotX=[%s]",ViewHelper.getPivotX(imageSample) + ""));
//                Log.d(TAG, String.format("getPivotY=[%s]", ViewHelper.getPivotY(imageSample) + ""));
                ViewHelper.setRotation(imageSample, 0);
                ViewHelper.setRotationY(imageSample, 0);
                ViewHelper.setRotationX(imageSample, 0);
                ViewHelper.setTranslationX(imageSample, 0);
                ViewHelper.setTranslationY(imageSample, 0);
                ViewHelper.setPivotX(imageSample, imgWidth / 2);
                ViewHelper.setPivotY(imageSample, imgHeight / 2);
                Log.d(TAG, ViewHelper.getPivotX(imageSample) + "");
                Log.d(TAG, ViewHelper.getPivotY(imageSample) + "");
                break;
            case R.id.btn01://haw:以图片中心的x轴进行平移
                Log.d(TAG, "setTranslationX");
                ViewHelper.setTranslationX(imageSample, getLeftValue());
                break;
            case R.id.btn02://haw:以图片中心的y轴进行旋转
                Log.d(TAG, "setTranslationY");
                ViewHelper.setTranslationY(imageSample, getLeftValue());
                break;
            case R.id.btn03://haw:以图片中心的x轴进行旋转
                Log.d(TAG, "setRotationX");
                ViewHelper.setRotationX(imageSample, getLeftValue());
                break;
            case R.id.btn04://haw:以图片中心的y轴进行旋转
                Log.d(TAG, "setRotationY");
                ViewHelper.setRotationY(imageSample, getLeftValue());
                break;
            case R.id.btn05://haw:以图片中心进行旋转
                Log.d(TAG, "setRotation");
                ViewHelper.setRotation(imageSample, getLeftValue());
                break;
            case R.id.btn06://haw:修改旋转的x轴支点，正数-向右
                Log.d(TAG, "setPivotX");
                ViewHelper.setPivotX(imageSample, getLeftValue());
                break;
            case R.id.btn07://haw:修改旋转的y轴支点，正数-向下
                Log.d(TAG, "setPivotY");
                ViewHelper.setPivotY(imageSample, getLeftValue());
                break;
            case R.id.btn08://haw:
                Log.d(TAG, "ofFloat");
                ObjectAnimator animator = ObjectAnimator.ofFloat(imageSample, "rotationX", getLeftValue(), getRightValue());
                AnimatorSet closeToBottom = new AnimatorSet();
                closeToBottom.play(animator);
                closeToBottom.setDuration(1500);
                closeToBottom.start();
                break;
            case R.id.btn09://haw:
                Log.d(TAG, "custom animation");
                shrinkAnimation();
                break;
            case R.id.btn10://haw:
                break;
            case R.id.btn11://haw:
                break;
            default:

        }
    }

    private void resetColorImag() {
        for (int i = 0; i < colorImag.size(); i++) {
            View view = colorImag.get(i);
            int imgWidth = view.getWidth();
            int imgHeight = view.getHeight();
            ViewHelper.setRotation(view, 0);
            ViewHelper.setRotationY(view, 0);
            ViewHelper.setRotationX(view, 0);
            ViewHelper.setPivotX(view, imgWidth / 2);
            ViewHelper.setPivotY(view, imgHeight / 2);
        }
    }

    private int onClickPostion = 2;

    private void shrinkAnimation() {
        onClickPostion = (int) getRightValue();
        List<Animator> closeToUpAnimatorList = new ArrayList<>();
        List<Animator> closeToBottomAnimatorList = new ArrayList<>();

        /**
         * haw:设置从顶部到点击位子的图像动画
         */
        for (int i = 0; i < onClickPostion; i++) {
            View view = colorImag.get(i);
            this.resetVerticalAnimation(view, false);
            Animator animator = ObjectAnimator.ofFloat(view, "rotationX", 0, 90);
            closeToBottomAnimatorList.add(animator);
        }
        AnimatorSet closeToBottom = new AnimatorSet();
        closeToBottom.playSequentially(closeToBottomAnimatorList);

        /**
         *haw:设置点击位子的图像动画
         */
        View onClickView = colorImag.get(onClickPostion);
        this.resetSideAnimation(onClickView);
        Animator closeToLeftSide = ObjectAnimator.ofFloat(onClickView, "rotationY", 0, 90);

        /**
         * haw:设置从底部到点击位子的图像动画
         */
        for (int i = colorImag.size() - 1; i > onClickPostion; i--) {
            View view = colorImag.get(i);
            this.resetVerticalAnimation(view, true);
            Animator animator = ObjectAnimator.ofFloat(view, "rotationX", 0, -90);
            closeToUpAnimatorList.add(animator);
        }
        AnimatorSet closeToUp = new AnimatorSet();
        closeToUp.playSequentially(closeToUpAnimatorList);


        /**
         * 综合所有动画，并执行
         */
        AnimatorSet fullAnimatorSet = new AnimatorSet();
        fullAnimatorSet.play(closeToUp).with(closeToBottom);
        //haw:如果顶部动画数量>=底部动画数量，则先执行完底部动画后在执行点击对象动画
        if(closeToBottomAnimatorList.size()>= closeToUpAnimatorList.size()){
            fullAnimatorSet.play(closeToBottom).before(closeToLeftSide);
        }else{
            fullAnimatorSet.play(closeToUp).before(closeToLeftSide);
        }

        fullAnimatorSet.setDuration((long) getLeftValue());
        fullAnimatorSet.start();
    }


    /**'
     * haw:重置竖向翻转的图像位置
     * @param view
     * @param toTop true-重置Y轴的描点为图像的顶部，false-重置Y轴的描点为图像的底部
     */
    private void resetVerticalAnimation(View view, boolean toTop) {
        int imgWidth = view.getWidth();
        int imgHeight = view.getHeight();
        //先讲对象位置设置还原，注意x,y的描点
        ViewHelper.setRotation(view, 0);
        ViewHelper.setPivotX(view, imgWidth / 2);
        ViewHelper.setPivotY(view, toTop ? 0 : imgHeight);
    }
    /**'
     * haw:重置横向翻转的图像位置
     * @param view
     */
    private void resetSideAnimation(View view) {
        int imgWidth = view.getWidth();
        int imgHeight = view.getHeight();
        //先讲对象位置设置还原，注意x,y的描点
        ViewHelper.setRotation(view, 0);
        ViewHelper.setPivotX(view, 0);//注意：由于图像在屏幕左边，所以X轴的锚点在0，因此图像向左翻转，反之即imgWidth
        ViewHelper.setPivotY(view, imgHeight/2);
    }

    public float getLeftValue() {
        float value = 0.0f;
        try {
            String valueStr = input1.getText().toString();
            value = Float.parseFloat(valueStr);
        } catch (Exception e) {
            Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
        }
        return value;
    }

    public float getRightValue() {
        float value = 0.0f;
        try {
            String valueStr = input2.getText().toString();
            value = Float.parseFloat(valueStr);
        } catch (Exception e) {
            Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
        }
        return value;
    }
}
