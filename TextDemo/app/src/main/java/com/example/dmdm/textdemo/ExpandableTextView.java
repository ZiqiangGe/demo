package com.example.dmdm.textdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DMDM on 2019/2/5.
 */

public class ExpandableTextView extends TextView {

    private static final String TAG = "ExpandableTextView";


    private static final int DEFAULT_TRIM_LENGTH = 300;
    private static final String ELLIPSIS = "...";

    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
        typedArray.recycle();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trim = !trim;
                setText();
                requestFocusFromTouch();
            }
        });
    }

    private void setText() {
        super.setText(getDisplayableText(), bufferType);
    }

    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        trimmedText = getTrimmedText(text);
        bufferType = type;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence text) {

        if (originalText != null && originalText.length() > trimLength) {
            Log.i(TAG, "getTrimmedText: originalText " + originalText.length());
            Log.i(TAG, "getTrimmedText: originalText " + originalText);
            String ss = originalText.toString();
            String aa = ss.substring(0,trimLength) + ELLIPSIS;
            return aa;
//            return new SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS);
        } else {
            return originalText.toString();
        }
    }

    public CharSequence getOriginalText() {
        return originalText;
    }

    public void setTrimLength(int trimLength) {
        this.trimLength = trimLength;
        trimmedText = getTrimmedText(originalText);
        setText();
    }

    public int getTrimLength() {
        return trimLength;
    }
}
