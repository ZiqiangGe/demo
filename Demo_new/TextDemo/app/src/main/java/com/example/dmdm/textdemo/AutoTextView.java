package com.example.dmdm.textdemo;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by yujibo on 2019/2/12.
 */

public class AutoTextView extends TextView
{
    private static final String TAG = "AutoTextView";

    private int mEmptyWidth = 150;//空白文本宽度
    private int mMinLine = 2;
    private CharSequence mString;

    public AutoTextView(Context context) {
        this(context, null);
    }

    public AutoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AutoTextView);
//        int emptyWidth = (int) ta.getDimension(R.styleable.AutoTextView_atv_empty_width, 0);
//        ta.recycle();
//        if (emptyWidth > 0) {
//            mEmptyWidth = emptyWidth;
//        } else {
            mEmptyWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mEmptyWidth, context.getResources().getDisplayMetrics());
//        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;

    public void setAutoText(final CharSequence text)
    {
        Log.i("geziqiang", "setAutoText");
        mString = text;
        if (TextUtils.isEmpty(text)) {
            Log.i("geziqiang", "text is empty");
            return;
        }
        mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout()
            {
                //可显示文本区域的宽度
                Log.i("geziqiang", "onGlobalLayout: ");
                int availableTextWidth = mMinLine * (getWidth() - getPaddingLeft() - getPaddingRight()) - mEmptyWidth;
                Paint paint = getPaint();
                paint.setTextSize(getTextSize());

                // 根据长度截取出剪裁后的文字
                String ellipsizeStr = (String) TextUtils.ellipsize(mString, (TextPaint) paint, availableTextWidth, TextUtils.TruncateAt.END);
                setText(ellipsizeStr);
            }
        };
        this.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
    }

    public void removeListener()
    {
        if (mOnGlobalLayoutListener != null)
        {
//            this.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
        }
    }

    public void setAutoText(final CharSequence text, int line)
    {
        mMinLine = line;
        setAutoText(text);
    }



}
