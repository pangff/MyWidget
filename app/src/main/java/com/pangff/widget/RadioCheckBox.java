package com.pangff.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pangff on 15/3/30.
 */
public class RadioCheckBox extends View {

    private Paint mPaint;
    private int bgColor;//背景色
    private int markColor;//对勾色
    private int disabledColor;//

    boolean isChecked = false;
    boolean isDisabled = false;

    float width;
    float height;

    int hPadding = 0;
    int vPadding = 0;

    public RadioCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RadioCheckBox, defStyle, 0);
        bgColor = a.getColor(R.styleable.RadioCheckBox_circleColor, Color.RED);
        markColor = a.getColor(R.styleable.RadioCheckBox_markColor, Color.WHITE);
        disabledColor = Color.parseColor("#DADADA");
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        hPadding = this.getPaddingLeft();
        vPadding = this.getPaddingRight();

        width = canvas.getWidth()-hPadding*2;
        height = canvas.getHeight()-vPadding*2;


        if(isDisabled){
            mPaint.setColor(disabledColor);
        }else{
            mPaint.setColor(bgColor);
        }
        mPaint.setStrokeWidth(4);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        if(isChecked||isDisabled){
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }else{
            mPaint.setStyle(Paint.Style.STROKE);
        }
        canvas.drawCircle(hPadding+width/ 2, vPadding+height / 2, width / 2-4, mPaint);

        if(isChecked||isDisabled){
            mPaint.setColor(markColor);
            mPaint.setStrokeWidth(5);
            canvas.drawLine(hPadding+width * 0.22f, vPadding+height * 0.55f, hPadding+width * 3 / 7, vPadding+height* 0.75f, mPaint);
            canvas.drawLine(hPadding+width * 3 / 7, vPadding+height * 0.75f,hPadding+width * 0.77f, vPadding+height* 0.32f, mPaint);
        }

    }

    /**
     * 设置选中状态
     *
     * @param isChecked
     */
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        invalidate();
    }

    /**
     * 是否可以点击
     *
     * @param isDisabled
     */
    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setChecked(!isChecked);
                break;
        }
        return super.onTouchEvent(event);
    }
}
