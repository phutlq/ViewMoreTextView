package com.example.phutlq.readmoreexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.viewmoretextview.ViewMoreTextView;

public class MainActivity extends AppCompatActivity {

    ViewMoreTextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (ViewMoreTextView) findViewById(R.id.activity_main_textview);
        mText.setText("Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú Trần Lê Quang Phú");
        mText.makeTextViewResizable();
    }
}
