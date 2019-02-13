package com.example.dmdm.textdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by DMDM on 2019/2/8.
 */

public class FoldTextView2 extends RelativeLayout
{

    private static final String TAG = "FoldTextView2";

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
     * Icon状态 展开动画
     */
    RotateAnimation expIconAnimation;

    /**
     * Icon状态 折叠动画
     */
    RotateAnimation foldIconAnimation;

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

    String mString;

    int state;

    Handler mHander = new Handler()
    {
        LayoutParams layoutParma;
        public void handleMessage(android.os.Message msg)
        {
            int lines = (Integer) msg.obj;
            if (state == msg.what)
            {
                return;
            }
            switch (msg.what)
            {
                case TEXT_OPEN:	//打开，增加高度
                    Log.i(TAG, "handleMessage: OPEN");
//                    mFoldTextViewHolder.tvFold.setMaxLines(lines);
//                    mFoldTextViewHolder.tvNoFold.setVisibility(VISIBLE);
//                    mFoldTextViewHolder.tvFold.setVisibility(GONE);
                    mFoldTextViewHolder.tvFold.removeListener();
                    mFoldTextViewHolder.tvFold.setText(mString);
                    break;
                case TEXT_CLOSE://关闭，减少高度
                    Log.i(TAG, "handleMessage: Close");
                    Log.i("geziqiang", "handleMessage: Close");
                    mFoldTextViewHolder.tvFold.setAutoText(mString,3);
//                    mFoldTextViewHolder.tvFold.setMaxLines(lines);
//                    mFoldTextViewHolder.tvNoFold.setVisibility(GONE);
//                    mFoldTextViewHolder.tvFold.setVisibility(VISIBLE);
                    break;
            }
            state = msg.what;
        };
    };


    public FoldTextView2(Context context)
    {
        super(context);

        init();
    }



    public FoldTextView2(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public FoldTextView2(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        init();
    }

    private void init()
    {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        myView = inflater.inflate(R.layout.layout_new2, null);
        this.addView(myView, layoutParams);

        //设置小箭头展开时的旋转动画
        expIconAnimation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        expIconAnimation.setDuration(300);
        expIconAnimation.setFillAfter(true);

        //设置小箭头关闭时的旋转动画
        foldIconAnimation = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        foldIconAnimation.setDuration(300);
        foldIconAnimation.setFillAfter(true);

        mFoldTextViewHolder = new FoldTextViewHolder(this);

    }


    public void setText1(final String text)
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
                    Log.i(TAG, "onGlobalLayout: ----0----");
//                    mFoldTextViewHolder.tvFold.setMaxLines(defaultLineCounts);
                    mFoldTextViewHolder.tvFold.setAutoText(text,3);
//                    mFoldTextViewHolder.tvFold.removeListener();
                    mFoldTextViewHolder.tvFold.measure(0, 0);
                    foldHeight = mFoldTextViewHolder.tvFold.getMeasuredHeight();


                    mFoldTextViewHolder.tvFold.setOnClickListener(FoldOnclick);
                    mFoldTextViewHolder.ivFold.setOnClickListener(FoldOnclick);

                    mFoldTextViewHolder.tvNoFold.setVisibility(GONE);
                    mFoldTextViewHolder.tvFold.setVisibility(VISIBLE);
                    mFoldTextViewHolder.ivFold.setVisibility(View.VISIBLE);
                    isExp = false;

                }
                //如果真实行数小于默认行数，则直接展示出来。isExp为true;
                else
                {
                    Log.i(TAG, "onGlobalLayout: ----2----");
                    mFoldTextViewHolder.tvNoFold.setVisibility(GONE);
//                    mFoldTextViewHolder.tvFold.setVisibility(GONE);
                    mFoldTextViewHolder.ivFold.setVisibility(View.GONE);
                    isExp = true;
                }

                isFirstLoad = false;
            }
        });
    }



    public void setText(final String text)
    {
//        Log.i(TAG, "setText: " + text.length());
//        mFoldTextViewHolder.tvFold.setText(text);

        mFoldTextViewHolder.tvNoFold.setText(text);
        mFoldTextViewHolder.tvFold.setAutoText(text,3);

//        mFoldTextViewHolder.tvFold.setAutoText(text,3);
        ViewTreeObserver vto = mFoldTextViewHolder.tvNoFold.getViewTreeObserver();
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
                realLineCounts = mFoldTextViewHolder.tvNoFold.getLineCount();
                realHeight = mFoldTextViewHolder.tvNoFold.getMeasuredHeight();

                //如果真实行数大于默认的显示行数，则默认将其折叠起来  isExp为false
                if (realLineCounts > defaultLineCounts)
                {
                    Log.i(TAG, "onGlobalLayout: ----0----");
                    mFoldTextViewHolder.tvFold.setMaxLines(defaultLineCounts);
                    mFoldTextViewHolder.tvFold.measure(0, 0);
                    foldHeight = mFoldTextViewHolder.tvFold.getMeasuredHeight();


                    mFoldTextViewHolder.tvFold.setOnClickListener(FoldOnclick);
                    mFoldTextViewHolder.ivFold.setOnClickListener(FoldOnclick);

                    mFoldTextViewHolder.tvNoFold.setVisibility(GONE);
                    mFoldTextViewHolder.tvFold.setVisibility(VISIBLE);
                    mFoldTextViewHolder.ivFold.setVisibility(View.VISIBLE);
                    isExp = false;

                }
                //如果真实行数小于默认行数，则直接展示出来。isExp为true;
                else
                {
                    Log.i(TAG, "onGlobalLayout: ----2----");
                    mFoldTextViewHolder.tvNoFold.setVisibility(VISIBLE);
                    mFoldTextViewHolder.tvFold.setVisibility(GONE);
                    mFoldTextViewHolder.ivFold.setVisibility(View.GONE);
                    isExp = true;
                }

                isFirstLoad = false;
            }
        });
    }

    View.OnClickListener FoldOnclick = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if (isExp)
            {
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        int endcount = realLineCounts;
                        while(endcount-- > defaultLineCounts){
                            Message msg = Message.obtain();
                            msg.what = TEXT_CLOSE;
                            msg.obj = endcount;
                            mHander.sendMessage(msg);
                            try
                            {
                                Thread.sleep(20);
                            } catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();

                mFoldTextViewHolder.ivFold.setAnimation(foldIconAnimation);
                foldIconAnimation.startNow();

                isExp = false;
            } else
            {
                new Thread(new Runnable()
                {

                    @Override
                    public void run()
                    {
                        int startcount = defaultLineCounts;
                        while(startcount++ < realLineCounts){
                            Message msg = Message.obtain();
                            msg.what = TEXT_OPEN;
                            msg.obj = startcount;
                            mHander.sendMessage(msg);
                            try
                            {
                                Thread.sleep(20);
                            } catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                mFoldTextViewHolder.ivFold.setAnimation(expIconAnimation);
                expIconAnimation.startNow();
                isExp = true;
            }
        }
    };


    public static String getSubString(TextView tv,String content,String maxLine)
    {
        float width = tv.getPaint().measureText(content);
//        float tvWidth =
        return null;
    }


    static class FoldTextViewHolder
    {
        AutoTextView tvFold;
        TextView tvNoFold;
        ImageView ivFold;

        public FoldTextViewHolder(View v)
        {
            tvFold = (AutoTextView) v.findViewById(R.id.tvFold);
            tvNoFold = v.findViewById(R.id.tvNoFold);
            ivFold = (ImageView) v.findViewById(R.id.ivFold);
        }
    }
}
