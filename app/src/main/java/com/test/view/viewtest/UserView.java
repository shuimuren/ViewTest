package com.test.view.viewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


public class UserView extends View {

    private String unspecified;
    private String exactly;
    private String atMost;
    private int defaultSize;

    public UserView(Context context) {
        super(context);
    }

    public UserView(Context context, AttributeSet attr) {
        super(context, attr);
        TypedArray array = context.obtainStyledAttributes(attr, R.styleable.user_view);
//        array.
        int count = attr.getAttributeCount();
        int a = array.getColor(R.styleable.user_view_color, Color.parseColor("#FF5566"));
        // array.getBoolean()
        // array.getColor()
        // array.getString()
        // array.getDimension()
        // array.getFloat()
        // array.getInteger()
        // array.getFraction()

        //使用完回收
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);
        if (width < height) {
            height = width;
        } else {
            width = height;
        }
        setMeasuredDimension(width, height);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int widthMode = MeasureSpec.getMode(measureSpec);
        int widthSize = MeasureSpec.getSize(measureSpec);
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED: //没有指定大小 设为默认值
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST: //如果测量模式是最大值为Size
                mySize = widthSize;
                break;
            case MeasureSpec.EXACTLY: //如果固定大小，不去改变它
                mySize = widthSize;
                break;
        }
        return mySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r = getMeasuredWidth() / 2;
        //圆心的横坐标为当前view左边起始位置+半径
        int centerX = getLeft() + r;
        //圆心的纵坐标为当前的view顶部起始位置+半径
        int centerY = getTop() + r;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(centerX, centerY, r, paint);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
