package com.example.dmdm.textdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by DMDM on 2019/2/8.
 */

public class FoldTextView1 extends RelativeLayout
{

    FoldTextViewHolder mFoldTextViewHolder;

    View myView;

    /**
     * 真实行数
     */
    int realLineCounts = 0;

    /**
     * 默认行数
     */
    int defaultLineCounts = 4;

    /**
     * 真实高度
     */
    int realHeight = 0;

    /**
     * 折叠后的高度
     */
    int foldHeight = 0;

    boolean isFirstLoad = true;

    /**
     * 当前是否展开
     */
    boolean isExp = false;

    /**
     * 展开TextView
     */
    final int TEXT_OPEN = 1;

    /**
     * 关闭TextView
     */
    final int TEXT_CLOSE = 2;

    private Context mContext;


    private int mMinLine = 2;

    private int mEmptyWidth = 150;//空白文本宽度

    private String mString;

    private String mLitterString;


//    Handler mHander = new Handler()
//    {
//        LayoutParams layoutParma;
//        public void handleMessage(android.os.Message msg)
//        {
//            int lines = (Integer) msg.obj;
//            switch (msg.what)
//            {
//                case TEXT_OPEN:	//打开，增加高度
//                    mFoldTextViewHolder.tvFold.setMaxLines(lines);
//                    break;
//                case TEXT_CLOSE://关闭，减少高度
//                    mFoldTextViewHolder.tvFold.setMaxLines(lines);
//                    break;
//            }
//        };
//    };


    public FoldTextView1(Context context)
    {
        super(context);
        mContext = context;
        init();
    }



    public FoldTextView1(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public FoldTextView1(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init()
    {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        myView = inflater.inflate(R.layout.layout_new1, null);

        this.addView(myView, layoutParams);

        mFoldTextViewHolder = new FoldTextViewHolder(this);

    }

    public void setText(final String text)
    {
        mString = text;
        mFoldTextViewHolder.tvFold.setText(text);

        ViewTreeObserver vto = mFoldTextViewHolder.tvFold.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                if (!isFirstLoad)
                {
                    return;
                }

                //获取真实行数
                realLineCounts = mFoldTextViewHolder.tvFold.getLineCount();
                realHeight = mFoldTextViewHolder.tvFold.getMeasuredHeight();

                //如果真实行数大于默认的显示行数，则默认将其折叠起来  isExp为false
                if (realLineCounts > defaultLineCounts)
                {
                    mFoldTextViewHolder.tvFold.setMaxLines(defaultLineCounts);
                    mLitterString = getSubString();
                    mFoldTextViewHolder.tvFold.setText(mLitterString);
                    mFoldTextViewHolder.tvFold.measure(0, 0);
                    foldHeight = mFoldTextViewHolder.tvFold.getMeasuredHeight();
                    mFoldTextViewHolder.ivFold.setVisibility(View.VISIBLE);


                    isExp = false;
                }
                //如果真实行数小于默认行数，则直接展示出来。isExp为true;
                else
                {
                    mFoldTextViewHolder.ivFold.setVisibility(View.GONE);
                    isExp = true;
                }

                isFirstLoad = false;

                ViewTreeObserver vto = mFoldTextViewHolder.tvFold.getViewTreeObserver();

                if(Build.VERSION.SDK_INT>=16)
                {
                    vto.removeOnGlobalLayoutListener(this);
                }else{
                    vto.removeGlobalOnLayoutListener(this);
                }
            }


        });
    }


    public String getSubString()
    {
        int availableTextWidth = defaultLineCounts * (getWidth() - getPaddingLeft() - getPaddingRight()) - mEmptyWidth;

        Paint paint = mFoldTextViewHolder.tvFold.getPaint();
        paint.setTextSize(mFoldTextViewHolder.tvFold.getTextSize());

        // 根据长度截取出剪裁后的文字
        String ellipsizeStr = (String) TextUtils.ellipsize(mString, (TextPaint) paint, availableTextWidth, TextUtils.TruncateAt.END);
        return ellipsizeStr;
    }


//    public String getSubString(TextView tv,String content,int maxLine)
//    {
//        if (mContext instanceof Activity)
//        {
//            float width = tv.getPaint().measureText(content);
//            Log.i("geziqiang", "getSubString: content.length " + content.length());
//            Log.i("geziqiang", "getSubString: width " + width);
//            float tvWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
//            Log.i("geziqiang", "getSubString: tvWidth " + tvWidth);
//            if (width/tvWidth >(2 + 0.8))
//            {
//                int r = (int)(content.length()/(width/tvWidth)/(2+0.7));
////                (int)(524/9.764/2.5)
//                Log.i("geziqiang", "getSubString: width/tvWidth  " + width/tvWidth);
//                String ss = content.substring(0,(int)((content.length()/(width/tvWidth)) * 2.7))+"...";
//                Log.i("geziqiang", "getSubString: " + ss);
//                return ss;
//            }
//        }
//        return null;
//    }

    public void startOpen()
    {
        mFoldTextViewHolder.tvFold.setMaxLines(Integer.MAX_VALUE);
        mFoldTextViewHolder.tvFold.setText(mString);
        mFoldTextViewHolder.ivFold.setScaleY(-1);
    }


    public void startClose()
    {
        mFoldTextViewHolder.tvFold.setMaxLines(defaultLineCounts);
        mFoldTextViewHolder.tvFold.setText(mLitterString);
        mFoldTextViewHolder.ivFold.setScaleY(1);
    }

    static class FoldTextViewHolder
    {
        TextView tvFold;
        ImageView ivFold;

        public FoldTextViewHolder(View v)
        {
            tvFold = (TextView) v.findViewById(R.id.tvFold);
            ivFold = (ImageView) v.findViewById(R.id.ivFold);
        }
    }
}
