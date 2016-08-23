package com.peng.tradeprogressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by PS on 2016/8/23.
 */
public class TradeProgressView extends View {

    private static final String TAG = TradeProgressView.class.getSimpleName();

    /**  成功的画笔  点  线 */
    private Paint mPaintSuccess;

    /**  失败的画笔  线 */
    private Paint mPaintFail;

    /**  成功的颜色   */
    private int mColorSuccess = R.color.red;

    /**  失败的颜色   */
    private int mColorFail = R.color.gray;

    /**  圆点半径 */
    private int mRadius = 15;

    /**  圆点半径为控件高度的 几分之几  */
    private int lineHeight = 4;

    /**   文字大小   */
    private int mTextSize = 30;

    private int mWidth;
    private int mHeight;

    private int mType;

    private String content1 = "";
    private String content2 = "";
    private String content3 = "";

    public TradeProgressView(Context context) {
        super(context);
        init();
    }

    public TradeProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TradeProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaintSuccess = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSuccess.setStyle(Paint.Style.FILL);
        mPaintSuccess.setTextAlign(Paint.Align.CENTER);
        mPaintSuccess.setColor(getResources().getColor(mColorSuccess));
        mPaintSuccess.setStrokeWidth(5);

        mPaintFail = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintFail.setStyle(Paint.Style.STROKE);
        mPaintFail.setTextAlign(Paint.Align.CENTER);
        mPaintFail.setColor(getResources().getColor(mColorFail));
        mPaintFail.setStrokeWidth(5);
    }

    /**
     *
     * @param type  0 -- 代表第一个点成功 1 -- 代表第二个点成功 2 -- 代表全部成功
     *
     * */
    public TradeProgressView setType(int type){
        mType = type;
        return this;
    }

    public TradeProgressView setColor(int successColor, int failColor){
        mColorSuccess = successColor;
        mColorFail = failColor;
        return this;
    }

    public TradeProgressView setTextSize(int size){
        mTextSize = size;
        return this;
    }

    public TradeProgressView setContent(String str1, String str2, String str3){
        content1 = str1;
        content2 = str2;
        content3 = str3;
        return this;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, " onDraw");
        //1.先在左边1/6处 画圆心 2.在圆心处--1/2处画线 3.在1/2处 画圆心 4.1/2 --- 5/6 画线 5.5/6处画圆
        if (mType == 0){
            drawPoint(0, true, canvas);
            drawPoint(1, false, canvas);
            drawPoint(2, false, canvas);

            drawLine(0, false, canvas);
            drawLine(1, false, canvas);
        } else if (mType == 1){
            drawPoint(0, true, canvas);
            drawPoint(1, true, canvas);
            drawPoint(2, false, canvas);

            drawLine(0, true, canvas);
            drawLine(1, false, canvas);
        } else if (mType == 2){
            drawPoint(0, true, canvas);
            drawPoint(1, true, canvas);
            drawPoint(2, true, canvas);

            drawLine(0, true, canvas);
            drawLine(1, true, canvas);
        }

        drawText(content1, 0, true, canvas);
        drawText(content2, 1, true, canvas);
        drawText(content3, 2, true, canvas);
    }

    private void drawPoint(int position, boolean flag, Canvas canvas){
        if (position == 0){
            if (flag){
                canvas.drawCircle(mWidth/6, mHeight/lineHeight, mRadius, mPaintSuccess);
            } else {
                canvas.drawCircle(mWidth/6, mHeight/lineHeight, mRadius, mPaintFail);
            }
        } else if (position == 1){
            if (flag){
                canvas.drawCircle(mWidth/2, mHeight/lineHeight, mRadius, mPaintSuccess);
            } else {
                canvas.drawCircle(mWidth/2, mHeight/lineHeight, mRadius, mPaintFail);
            }
        } else if (position == 2){
            if (flag){
                canvas.drawCircle(mWidth*5/6, mHeight/lineHeight, mRadius, mPaintSuccess);
            } else {
                canvas.drawCircle(mWidth*5/6, mHeight/lineHeight, mRadius, mPaintFail);
            }
        }
    }

    private void drawLine(int position, boolean flag, Canvas canvas){
        if (position == 0){
            if (flag){
                canvas.drawLine(mWidth/6 + mRadius, mHeight/lineHeight, mWidth/2 - mRadius, mHeight/lineHeight, mPaintSuccess);
            } else {
                canvas.drawLine(mWidth/6 + mRadius, mHeight/lineHeight, mWidth/2 - mRadius, mHeight/lineHeight, mPaintFail);
            }
        } else if (position == 1){
            if (flag){
                canvas.drawLine(mWidth/2 + mRadius, mHeight/lineHeight, mWidth*5/6 - mRadius, mHeight/lineHeight, mPaintSuccess);
            } else {
                canvas.drawLine(mWidth/2 + mRadius, mHeight/lineHeight, mWidth*5/6 - mRadius, mHeight/lineHeight, mPaintFail);
            }
        }
    }

    private void drawText(String content, int position, boolean flag, Canvas canvas){
        if (position == 0){
            if (flag){
                mPaintSuccess.setTextSize(mTextSize);
                canvas.drawText(content, mWidth/6, mHeight*3/lineHeight, mPaintSuccess);
            } else {
                mPaintFail.setTextSize(mTextSize);
                canvas.drawText(content, mWidth/6, mHeight*3/lineHeight, mPaintFail);
            }
        } else if (position == 1){
            if (flag){
                mPaintSuccess.setTextSize(mTextSize);
                canvas.drawText(content, mWidth/2, mHeight*3/lineHeight, mPaintSuccess);
            } else {
                mPaintFail.setTextSize(mTextSize);
                canvas.drawText(content, mWidth/2, mHeight*3/lineHeight, mPaintFail);
            }
        } else if (position == 2){
            if (flag){
                mPaintSuccess.setTextSize(mTextSize);
                canvas.drawText(content, mWidth*5/6, mHeight*3/lineHeight, mPaintSuccess);
            } else {
                mPaintFail.setTextSize(mTextSize);
                canvas.drawText(content, mWidth*5/6, mHeight*3/lineHeight, mPaintFail);
            }
        }
    }

}
