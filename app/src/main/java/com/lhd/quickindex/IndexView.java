package com.lhd.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lihuaidong on 2017/6/13 16:02.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class IndexView extends View
{
    private Paint paint;
    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private float itemViewWidth;
    private float itemViewHeight;

    public IndexView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(0);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemViewWidth = getMeasuredWidth();
        itemViewHeight = getMeasuredHeight() / words.length;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++)
        {
            if(wordIndex==i) {
                paint.setColor(Color.GREEN);
                paint.setTextSize(30);
            }else
            {
                paint.setColor(Color.WHITE);
                paint.setTextSize(20);
            }
            String text=words[i];
            Rect bounds=new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);
            float textWidth = bounds.width();
            float textHeight = bounds.height();
            float x=itemViewWidth/2-textWidth/2;
            float y = itemViewHeight/2+textHeight/2+i*itemViewHeight;
            canvas.drawText(text,x,y,paint);

        }
    }

    /**
     * 操作的下标
     */
    private int wordIndex=-1;
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int index= (int) (y/itemViewHeight);
                if(index!=wordIndex) {
                    wordIndex=index;
                    invalidate();
                    if(onIndexChangeListener!=null) {
                       onIndexChangeListener.onIndexChange(words[wordIndex]);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                wordIndex=-1;
                invalidate();
                break;
        }
        return true;

    }

    /**
     * 自定义监听器
     */
    interface OnIndexChangeListener
    {
        public void onIndexChange(String word);
    }
    private OnIndexChangeListener onIndexChangeListener;

    /**
     * set方法
     * @param onIndexChangeListener
     */
    public void setOnIndexChangeListener(OnIndexChangeListener onIndexChangeListener)
    {
        this.onIndexChangeListener = onIndexChangeListener;
    }
}
