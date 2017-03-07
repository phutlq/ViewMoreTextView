package com.example.viewmoretextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by PhuTLQ on 3/6/2017.
 */

public class ViewMoreTextView extends TextView {

    private boolean isUnderlineText = true;
    private boolean isViewMore = true;
    private int maxLine;
    private String viewMoreText;
    private String viewLessText;
    private int viewMoreTextColor = Color.RED;
    private int highlightColor = Color.TRANSPARENT;


    public ViewMoreTextView(Context context) {
        this(context,null);
    }

    public ViewMoreTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewMoreTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ViewMoreTextView, defStyleAttr, 0);
        this.isUnderlineText = attributes.getBoolean(
                R.styleable.ViewMoreTextView_vmt_isUnderlineText, isUnderlineText);
        this.maxLine = attributes.getInteger(R.styleable.ViewMoreTextView_vmt_maxLine,2);
        this.viewMoreTextColor = attributes.getColor(R.styleable.ViewMoreTextView_vmt_viewMoreTextColor,viewMoreTextColor);
        this.highlightColor = attributes.getColor(R.styleable.ViewMoreTextView_vmt_highlightColor,highlightColor);
        this.viewLessText = attributes.getString(R.styleable.ViewMoreTextView_vmt_viewLessText);
        this.viewMoreText = attributes.getString(R.styleable.ViewMoreTextView_vmt_viewMoreText);
        if (viewMoreText == null && viewLessText != null) {
            this.viewLessText = attributes.getString(R.styleable.ViewMoreTextView_vmt_viewLessText);
            this.viewMoreText = "Read More";
        } else if(viewLessText == null && viewMoreText != null) {
            this.viewLessText = "Read Less";
            this.viewMoreText = attributes.getString(R.styleable.ViewMoreTextView_vmt_viewMoreText);
        } else if (viewMoreText == null && viewLessText == null) {
            this.viewMoreText = "Read More";
            this.viewLessText = "Read Less";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewMoreTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void makeTextViewResizable() {

        if (ViewMoreTextView.this.getTag() == null) {
            ViewMoreTextView.this.setTag(ViewMoreTextView.this.getText());
        }
        ViewTreeObserver vto = ViewMoreTextView.this.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = ViewMoreTextView.this.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = ViewMoreTextView.this.getLayout().getLineEnd(0);
                    String text = ViewMoreTextView.this.getText().subSequence(0, lineEndIndex - viewMoreText.length() + 1) + "..." + viewMoreText;
                    ViewMoreTextView.this.setText(text);
                    ViewMoreTextView.this.setMovementMethod(LinkMovementMethod.getInstance());
                    ViewMoreTextView.this.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(ViewMoreTextView.this.getText().toString()),maxLine, viewMoreText,
                                    viewLessText,isViewMore), TextView.BufferType.SPANNABLE);
                }
                else if (maxLine > 0 && ViewMoreTextView.this.getLineCount() >= maxLine) {
                    int lineEndIndex = ViewMoreTextView.this.getLayout().getLineEnd(maxLine - 1);
                    String text = ViewMoreTextView.this.getText().subSequence(0, lineEndIndex - viewMoreText.length() + 1) + "..." + viewMoreText;
                    ViewMoreTextView.this.setText(text);
                    ViewMoreTextView.this.setMovementMethod(LinkMovementMethod.getInstance());
                    ViewMoreTextView.this.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(ViewMoreTextView.this.getText().toString()),maxLine, viewMoreText,
                                    viewLessText,isViewMore), TextView.BufferType.SPANNABLE);
                }
                else {
                    int lineEndIndex = ViewMoreTextView.this.getLayout().getLineEnd(ViewMoreTextView.this.getLayout().getLineCount() - 1);
                    String text = ViewMoreTextView.this.getText().subSequence(0, lineEndIndex) + "..." + viewMoreText;
                    ViewMoreTextView.this.setText(text);
                    ViewMoreTextView.this.setMovementMethod(LinkMovementMethod.getInstance());
                    ViewMoreTextView.this.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(ViewMoreTextView.this.getText().toString()),maxLine, viewMoreText,
                                    viewLessText,isViewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    public void makeTextViewUnResizable() {

        if (ViewMoreTextView.this.getTag() == null) {
            ViewMoreTextView.this.setTag(ViewMoreTextView.this.getText());
        }
        ViewTreeObserver vto = ViewMoreTextView.this.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = ViewMoreTextView.this.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (-maxLine == 0) {
                    int lineEndIndex = ViewMoreTextView.this.getLayout().getLineEnd(0);
                    String text = ViewMoreTextView.this.getText().subSequence(0, lineEndIndex - viewLessText.length() + 1) + "..." + viewMoreText;
                    ViewMoreTextView.this.setText(text);
                    ViewMoreTextView.this.setMovementMethod(LinkMovementMethod.getInstance());
                    ViewMoreTextView.this.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(ViewMoreTextView.this.getText().toString()),-maxLine, viewLessText,
                                    viewMoreText,false), TextView.BufferType.SPANNABLE);
                }
                else if (-maxLine > 0 && ViewMoreTextView.this.getLineCount() >= -maxLine) {
                    int lineEndIndex = ViewMoreTextView.this.getLayout().getLineEnd(-maxLine - 1);
                    String text = ViewMoreTextView.this.getText().subSequence(0, lineEndIndex - viewLessText.length() + 1) + "..." + viewLessText;
                    ViewMoreTextView.this.setText(text);
                    ViewMoreTextView.this.setMovementMethod(LinkMovementMethod.getInstance());
                    ViewMoreTextView.this.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(ViewMoreTextView.this.getText().toString()),-maxLine, viewLessText,
                                    viewMoreText,false), TextView.BufferType.SPANNABLE);
                }
                else {
                    int lineEndIndex = ViewMoreTextView.this.getLayout().getLineEnd(ViewMoreTextView.this.getLayout().getLineCount() - 1);
                    String text = ViewMoreTextView.this.getText().subSequence(0, lineEndIndex) + "..." + viewLessText;
                    ViewMoreTextView.this.setText(text);
                    ViewMoreTextView.this.setMovementMethod(LinkMovementMethod.getInstance());
                    ViewMoreTextView.this.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(ViewMoreTextView.this.getText().toString()),-maxLine, viewLessText,
                                    viewMoreText,false), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    public SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned,
                                                                            final int maxLine, final String viewMoreText, final String viewLessText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);
        ForegroundColorSpan foreColour = new ForegroundColorSpan(viewMoreTextColor);

        if (str.contains(viewMoreText)) {
            ssb.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        ViewMoreTextView.this.setLayoutParams(ViewMoreTextView.this.getLayoutParams());
                        ViewMoreTextView.this.setText(ViewMoreTextView.this.getTag().toString(), TextView.BufferType.SPANNABLE);
                        ViewMoreTextView.this.invalidate();
                        makeTextViewUnResizable();
                    } else {
                        ViewMoreTextView.this.setLayoutParams(ViewMoreTextView.this.getLayoutParams());
                        ViewMoreTextView.this.setText(ViewMoreTextView.this.getTag().toString(), TextView.BufferType.SPANNABLE);
                        ViewMoreTextView.this.invalidate();
                        makeTextViewResizable();
                    }
                }
            }, str.indexOf(viewMoreText), str.indexOf(viewMoreText) + viewMoreText.length(), 0);

            ssb.setSpan(foreColour, str.indexOf(viewMoreText), str.indexOf(viewMoreText)+ viewMoreText.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (!isUnderlineText) {
                ssb.setSpan(new UnderlineSpan() {
                    public void updateDrawState(TextPaint tp) {
                        tp.setUnderlineText(false);

                    }
                }, str.indexOf(viewMoreText), str.indexOf(viewMoreText) + viewMoreText.length(), 0);
            }
            ViewMoreTextView.this.setHighlightColor(highlightColor);
        }
        return ssb;

    }
}
