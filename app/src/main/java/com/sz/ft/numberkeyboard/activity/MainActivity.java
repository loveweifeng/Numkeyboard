package com.sz.ft.numberkeyboard.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sz.ft.numberkeyboard.R;
import com.sz.ft.numberkeyboard.view.NumberKeyBoardView;

public class MainActivity extends AppCompatActivity implements NumberKeyBoardView.OnKeyBoardClickListener {
    TextView mText;
    NumberKeyBoardView mNumberKeyBoardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText= (TextView) findViewById(R.id.mText);
        mNumberKeyBoardView= (NumberKeyBoardView) findViewById(R.id.mNumberKeyBoardView);
        mNumberKeyBoardView.setOnKeyBoardClickListener(this);
    }

    @Override
    public void scoreChanged(String score) {
        mText.setText(score);
    }
}
